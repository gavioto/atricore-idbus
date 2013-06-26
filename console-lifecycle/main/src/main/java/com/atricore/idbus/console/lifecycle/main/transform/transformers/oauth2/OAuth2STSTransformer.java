package com.atricore.idbus.console.lifecycle.main.transform.transformers.oauth2;

import com.atricore.idbus.console.lifecycle.main.domain.metadata.*;
import com.atricore.idbus.console.lifecycle.main.exception.TransformException;
import com.atricore.idbus.console.lifecycle.main.transform.TransformEvent;
import com.atricore.idbus.console.lifecycle.main.transform.transformers.AbstractTransformer;
import com.atricore.idbus.console.lifecycle.support.springmetadata.model.Bean;
import com.atricore.idbus.console.lifecycle.support.springmetadata.model.Beans;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.atricore.idbus.capabilities.oauth2.common.AESTokenEncrypter;
import org.atricore.idbus.capabilities.oauth2.common.HMACTokenSigner;
import org.atricore.idbus.capabilities.oauth2.main.OAuth2IdPMediator;
import org.atricore.idbus.capabilities.oauth2.main.emitter.OAuth2AccessTokenEmitter;
import org.atricore.idbus.kernel.main.mediation.provider.IdentityProviderImpl;

import java.util.Collection;

import static com.atricore.idbus.console.lifecycle.main.transform.transformers.util.ProxyUtil.isIdPProxyRequired;
import static com.atricore.idbus.console.lifecycle.main.transform.transformers.util.ProxyUtil.isOAuth2IdPProxyRequired;
import static com.atricore.idbus.console.lifecycle.support.springmetadata.util.BeanUtils.*;
import static com.atricore.idbus.console.lifecycle.support.springmetadata.util.BeanUtils.newBean;

/**
 * OAuth 2.0 Emitter for WS-Trust service
 *
 * @author <a href=mailto:sgonzalez@atricore.org>Sebastian Gonzalez Oyuela</a>
 */
public class OAuth2STSTransformer extends AbstractTransformer {

    private static final Log logger = LogFactory.getLog(com.atricore.idbus.console.lifecycle.main.transform.transformers.sso.STSTransformer.class);

    @Override
    public boolean accept(TransformEvent event) {
        // Enable OAuth 2.0 STS for local IdPs with OAuth 2.0 support enabled
        if (event.getData() instanceof IdentityProvider &&
                !((IdentityProvider)event.getData()).isRemote() &&
                ((IdentityProvider)event.getData()).isOauth2Enabled()) {

            if (logger.isTraceEnabled())
                logger.trace("Required OAuth2 STS components for local IdP " + ((IdentityProvider)event.getData()).getName());

            return true;
        }

        // Enable OAuth 2.0 STS for remote SAML 2.0 IdPs without OAuth 2.0 support
        if (event.getData() instanceof ServiceProviderChannel) {
            FederatedConnection fc = (FederatedConnection) event.getContext().getParentNode();
            boolean proxy = isOAuth2IdPProxyRequired(fc);

            if (proxy)
                if (logger.isTraceEnabled())
                    logger.trace("Required OAuth2 STS components for proxied IdP between " + fc.getRoleA().getName() + ":" + fc.getRoleB().getName());

            return proxy;
        }

        return false;
    }

    @Override
    public Object after(TransformEvent event) throws TransformException {
        boolean isProxy = false;

        FederatedProvider provider = null;
        OAuth2Resource resource = null;
        if (event.getData() instanceof FederatedProvider) {
            provider = (FederatedProvider) event.getData();
            isProxy = false;
            if (logger.isTraceEnabled())
                logger.trace("Creating OAuth2 STS components for local IdP " + ((IdentityProvider)event.getData()).getName());

        } else if (isOAuth2IdPProxyRequired((FederatedConnection) event.getContext().getParentNode())) {
            // Since this is a proxy, it must be an internal SAML 2.0 SP
            ServiceProviderChannel spChannel = (ServiceProviderChannel) event.getData();

            FederatedConnection fc = (FederatedConnection) event.getContext().getParentNode();

            if (logger.isTraceEnabled())
                logger.trace("Creating OAuth2 STS components for proxied IdP between " + fc.getRoleA().getName() + ":" + fc.getRoleB().getName());

            isProxy = true;

            if (fc.getRoleA() instanceof ExternalSaml2IdentityProvider && fc.getRoleA().isRemote()) {
                provider = fc.getRoleA();
                resource = (OAuth2Resource) ((InternalSaml2ServiceProvider)fc.getRoleB()).getServiceConnection().getResource();
            } else if (fc.getRoleB() instanceof ExternalSaml2IdentityProvider && fc.getRoleB().isRemote()) {
                resource = (OAuth2Resource) ((InternalSaml2ServiceProvider)fc.getRoleA()).getServiceConnection().getResource();
                provider = fc.getRoleB();
            } else {
                // ERROR !
                logger.error("External IdP not supported in federated connection " + fc.getName() +
                        ", available providers A: " + fc.getRoleA().getName() + ", B:" + fc.getRoleB().getName());
                throw new TransformException("External IdP type not found in federated connection " + fc.getName());
            }
        } else {
            logger.error("Accepted invalid node : " + event.getData());
        }

        Beans idpBeans = isProxy ? (Beans) event.getContext().get("idpProxyBeans") : (Beans) event.getContext().get("idpBeans");

        IdentityProvider localIdp = null;
        if (provider instanceof IdentityProvider) {
            localIdp = (IdentityProvider) provider;
        }

        if (logger.isTraceEnabled())
            logger.trace("Generating OAUTH2 STS Beans for IdP " + provider.getName());


        // ----------------------------------------
        // Get IDP Bean
        // ----------------------------------------
        Bean idpBean = null;
        Collection<Bean> b = getBeansOfType(idpBeans, IdentityProviderImpl.class.getName());
        if (b.size() != 1) {
            throw new TransformException("Invalid IdP definition count : " + b.size());
        }
        idpBean = b.iterator().next();

        // ----------------------------------------
        // STS, must be already created
        // ----------------------------------------
        Bean sts = getBean(idpBeans, idpBean.getName() + "-sts");

        // ----------------------------------------
        // Emitters
        // ----------------------------------------
        Bean oauth2StsEmitter = newBean(idpBeans,
                idpBean.getName() + "-oauth2-accesstoken-emitter",
                OAuth2AccessTokenEmitter.class.getName());
        setPropertyValue(oauth2StsEmitter, "id", oauth2StsEmitter.getName());

        // identityPlanRegistry
        setPropertyRef(oauth2StsEmitter, "identityPlanRegistry", "identity-plans-registry");

        Collection<Bean> mediators = getBeansOfType(idpBeans, OAuth2IdPMediator.class.getName());

        if (mediators.size() != 1)
            throw new TransformException("Too many/few mediators defined " + mediators.size());

        Bean mediatorBean = mediators.iterator().next();

        Bean idMgr = getBean(idpBeans, idpBean.getName() + "-identity-manager");

        // Inject identity Manager
        if (idMgr != null) {
            setPropertyRef(oauth2StsEmitter, "identityManager", idpBean.getName() + "-identity-manager");
        }

        // If we have a local IdP, we use the OAuth 2.0 key, otherwise we take the OAuth 2.0 shared secret from the resource
        // (It means we're proxying an external IdP)
        String oauth2Key = localIdp != null ? localIdp.getOauth2Key()  : resource.getSecret();

        /* Configure AES encryption */
        Bean aesEncrypter = newAnonymousBean(AESTokenEncrypter.class);
        setPropertyValue(aesEncrypter, "base64key", oauth2Key);
        setPropertyBean(oauth2StsEmitter, "tokenEncrypter", aesEncrypter);

        /* Configure H-MAC signature support */
        Bean hmacSigner = newAnonymousBean(HMACTokenSigner.class);
        setPropertyValue(hmacSigner, "key", oauth2Key);
        setPropertyBean(oauth2StsEmitter, "tokenSigner", hmacSigner);
        setPropertyValue(oauth2StsEmitter, "emitWhenNotTargeted", "true");

        // Add emitter to STS : the emitter MUST be the first in the list (or run before SAML2) // TODO : Mange dependencies ?
        insertPropertyBean(sts, "emitters", oauth2StsEmitter);

        return null;
    }


}