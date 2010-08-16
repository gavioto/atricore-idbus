package com.atricore.idbus.console.lifecycle.main.transform.transformers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.atricore.idbus.capabilities.josso.main.JossoMediator;
import org.atricore.idbus.capabilities.josso.main.JossoService;
import org.atricore.idbus.capabilities.josso.main.binding.JossoBinding;
import org.atricore.idbus.capabilities.josso.main.binding.JossoBindingFactory;
import org.atricore.idbus.capabilities.josso.main.binding.logging.JossoLogMessageBuilder;
import com.atricore.idbus.console.lifecycle.main.domain.metadata.BindingProvider;
import com.atricore.idbus.console.lifecycle.main.domain.metadata.Channel;
import com.atricore.idbus.console.lifecycle.main.domain.metadata.ProviderRole;
import com.atricore.idbus.console.lifecycle.main.exception.TransformException;
import com.atricore.idbus.console.lifecycle.main.transform.IdProjectModule;
import com.atricore.idbus.console.lifecycle.main.transform.IdProjectResource;
import com.atricore.idbus.console.lifecycle.main.transform.TransformEvent;
import com.atricore.idbus.console.lifecycle.support.springmetadata.model.*;
import org.atricore.idbus.capabilities.samlr2.main.SamlR2CircleOfTrustManager;
import org.atricore.idbus.capabilities.samlr2.support.binding.SamlR2Binding;
import org.atricore.idbus.capabilities.samlr2.support.metadata.SAMLR2MetadataConstants;
import org.atricore.idbus.kernel.main.federation.metadata.CircleOfTrustImpl;
import org.atricore.idbus.kernel.main.mediation.binding.BindingChannelImpl;
import org.atricore.idbus.kernel.main.mediation.camel.component.logging.CamelLogMessageBuilder;
import org.atricore.idbus.kernel.main.mediation.camel.component.logging.HttpLogMessageBuilder;
import org.atricore.idbus.kernel.main.mediation.camel.logging.DefaultMediationLogger;
import org.atricore.idbus.kernel.main.mediation.endpoint.IdentityMediationEndpointImpl;
import org.atricore.idbus.kernel.main.mediation.osgi.OsgiIdentityMediationUnit;
import org.atricore.idbus.kernel.main.mediation.provider.BindingProviderImpl;

import java.util.*;
import java.util.List;

import static com.atricore.idbus.console.lifecycle.support.springmetadata.util.BeanUtils.*;
import static com.atricore.idbus.console.lifecycle.support.springmetadata.util.BeanUtils.newBean;
import static com.atricore.idbus.console.lifecycle.support.springmetadata.util.BeanUtils.setPropertyValue;

/**
 * @author <a href="mailto:sgonzalez@atricore.org">Sebastian Gonzalez Oyuela</a>
 * @version $Id$
 */
public class BPTransformer extends AbstractTransformer {
    private static final Log logger = LogFactory.getLog(IdPTransformer.class);

    @Override
    public boolean accept(TransformEvent event) {
        return event.getData() instanceof BindingProvider;
    }

    @Override
    public void before(TransformEvent event) throws TransformException {

        BindingProvider provider = (BindingProvider) event.getData();

        Date now = new Date();

        Beans bpBeans = new Beans();

        Description descr = new Description();
        descr.getContent().add(provider.getName() + " : BP Configuration generated by Atricore Identity Bus Server on " + now.toGMTString());
        descr.getContent().add(provider.getDescription());

        bpBeans.setDescription(descr);

        Beans baseBeans = (Beans) event.getContext().get("beans");
        
        // Publish root element so that other transformers can use it.
        event.getContext().put("bpBeans", bpBeans);
        
        if (logger.isDebugEnabled())
            logger.debug("Generating BP " + provider.getName() + " configuration model");

        Bean bp = newBean(bpBeans, normalizeBeanName(provider.getName()),
                BindingProviderImpl.class.getName());

        // Name
        setPropertyValue(bp, "name", bp.getName());

        // Role
        if (!provider.getRole().equals(ProviderRole.Binding)) {
            logger.warn("Provider "+provider.getId()+" is not defined as BP, forcing role! ");
        }
        setPropertyValue(bp, "role", "{urn:org:atricore:idbus:josso:metadata}JOSSOAgentDescriptor");

        // unitContainer
        setPropertyRef(bp, "unitContainer", event.getContext().getCurrentModule().getId() + "-container");

        // COT Manager
        Collection<Bean> cotMgrs = getBeansOfType(baseBeans, SamlR2CircleOfTrustManager.class.getName());
        if (cotMgrs.size() == 1) {
            Bean cotMgr = cotMgrs.iterator().next();
            setPropertyRef(bp, "cotManager", cotMgr.getName());
        }

        // State Manager
        setPropertyRef(bp, "stateManager", event.getContext().getCurrentModule().getId() + "-state-manager");

        // MBean
        Bean mBean = newBean(bpBeans, bp.getName() + "-mbean",
                "org.atricore.idbus.capabilities.samlr2.management.internal.BindingProviderMBeanImpl");
        setPropertyRef(mBean, "bindingProvider", bp.getName());

        // MBean Exporter
        Bean mBeanExporter = newBean(bpBeans, bp.getName() + "-mbean-exporter", "org.springframework.jmx.export.MBeanExporter");
        setPropertyRef(mBeanExporter, "server", "mBeanServer");

        Bean mBeanEntryKeyBean = newBean(bpBeans, mBean.getName() + "-key", String.class);
        setConstructorArg(mBeanEntryKeyBean, "java.lang.String", "org.atricore.idbus." +
                event.getContext().getCurrentModule().getId() + ":type=BindingProvider,name=" + bp.getName());

        Entry mBeanEntry = new Entry();
        mBeanEntry.setKeyRef(mBeanEntryKeyBean.getName());
        mBeanEntry.setValueRef(mBean.getName());

        addEntryToMap(mBeanExporter, "beans", mBeanEntry);
        
        // mbean assembler
        /*Bean mBeanAssembler = newAnonymousBean("org.springframework.jmx.export.assembler.MethodNameBasedMBeanInfoAssembler");

        List<Prop> props = new ArrayList<Prop>();

        Prop prop = new Prop();
        prop.setKey("org.atricore.idbus." + event.getContext().getCurrentModule().getId() +
                ":type=BindingProvider,name=" + bp.getName());
        prop.getContent().add("listStatesAsTable,listStateEntriesAsTable");
        props.add(prop);

        setPropertyValue(mBeanAssembler, "methodMappings", props);

        setPropertyBean(mBeanExporter, "assembler", mBeanAssembler);*/

        // Binding Channel
        if (provider.getBindingChannel() != null) {
            Channel bindingChannel = provider.getBindingChannel();
            Bean bc = newBean(bpBeans, normalizeBeanName(bindingChannel.getName()),
                    "org.atricore.idbus.kernel.main.mediation.binding.BindingChannelImpl");
            setPropertyValue(bc, "name", bc.getName());
            setPropertyValue(bc, "description", bindingChannel.getDescription());
            setPropertyRef(bc, "provider", bp.getName());
            setPropertyValue(bc, "location", resolveLocationUrl(provider.getBindingChannel().getLocation()));

            setPropertyRef(bc, "unitContainer", event.getContext().getCurrentModule().getId() + "-container");
            setPropertyRef(bc, "identityMediator", bp.getName() + "-binding-mediator");

            // endpoints
            List<Bean> endpoints = new ArrayList<Bean>();

            Bean sloArtifact = newAnonymousBean(IdentityMediationEndpointImpl.class);
            sloArtifact.setName(bp.getName() + "-binding-ssop-slo-artifact");
            setPropertyValue(sloArtifact, "name", sloArtifact.getName());
            setPropertyValue(sloArtifact, "type", "{urn:org:atricore:idbus:sso:metadata}SingleLogoutService");
            setPropertyValue(sloArtifact, "binding", SamlR2Binding.SSO_ARTIFACT.getValue());
            setPropertyValue(sloArtifact, "location", "/SSO/SLO/ARTIFACT");
            endpoints.add(sloArtifact);

            Bean acsArtifact = newAnonymousBean(IdentityMediationEndpointImpl.class);
            acsArtifact.setName(bp.getName() + "-binding-ssop-acs-artifact");
            setPropertyValue(acsArtifact, "name", acsArtifact.getName());
            setPropertyValue(acsArtifact, "type", SAMLR2MetadataConstants.SPBindingAssertionConsumerService_QNAME.toString());
            setPropertyValue(acsArtifact, "binding", SamlR2Binding.SSO_ARTIFACT.getValue());
            setPropertyValue(acsArtifact, "location", "/SSO/ACS/ARTIFACT");
            endpoints.add(acsArtifact);

            Bean ssoRedirect = newAnonymousBean(IdentityMediationEndpointImpl.class);
            ssoRedirect.setName(bp.getName() + "-binding-josso-sso-redir");
            setPropertyValue(ssoRedirect, "name", ssoRedirect.getName());
            setPropertyValue(ssoRedirect, "type", JossoService.SingleSignOnService.toString());
            setPropertyValue(ssoRedirect, "binding", SamlR2Binding.SS0_REDIRECT.getValue());
            setPropertyValue(ssoRedirect, "location", "/JOSSO/SSO/REDIR");
            endpoints.add(ssoRedirect);

            Bean sloRedirect = newAnonymousBean(IdentityMediationEndpointImpl.class);
            sloRedirect.setName(bp.getName() + "-binding-josso-slo-redir");
            setPropertyValue(sloRedirect, "name", sloRedirect.getName());
            setPropertyValue(sloRedirect, "type", JossoService.SingleLogoutService.toString());
            setPropertyValue(sloRedirect, "binding", SamlR2Binding.SS0_REDIRECT.getValue());
            setPropertyValue(sloRedirect, "location", "/JOSSO/SLO/REDIR");
            endpoints.add(sloRedirect);

            Bean ssoIdmSoap = newAnonymousBean(IdentityMediationEndpointImpl.class);
            ssoIdmSoap.setName(bp.getName() + "-binding-josso-ssoidm-soap");
            setPropertyValue(ssoIdmSoap, "name", ssoIdmSoap.getName());
            setPropertyValue(ssoIdmSoap, "type", JossoService.IdentityManager.toString());
            setPropertyValue(ssoIdmSoap, "binding", JossoBinding.JOSSO_SOAP.getValue());
            setPropertyValue(ssoIdmSoap, "location", "/JOSSO/SSOIdentityManager/SOAP");
            endpoints.add(ssoIdmSoap);
            
            Bean ssoIdpSoap = newAnonymousBean(IdentityMediationEndpointImpl.class);
            ssoIdpSoap.setName(bp.getName() + "-binding-josso-ssoidp-soap");
            setPropertyValue(ssoIdpSoap, "name", ssoIdpSoap.getName());
            setPropertyValue(ssoIdpSoap, "type", JossoService.IdentityProvider.toString());
            setPropertyValue(ssoIdpSoap, "binding", JossoBinding.JOSSO_SOAP.getValue());
            setPropertyValue(ssoIdpSoap, "location", "/JOSSO/SSOIdentityProvider/SOAP");
            endpoints.add(ssoIdpSoap);

            Bean ssoSmSoap = newAnonymousBean(IdentityMediationEndpointImpl.class);
            ssoSmSoap.setName(bp.getName() + "-binding-josso-ssosm-soap");
            setPropertyValue(ssoSmSoap, "name", ssoSmSoap.getName());
            setPropertyValue(ssoSmSoap, "type", JossoService.SessionManager.toString());
            setPropertyValue(ssoSmSoap, "binding", JossoBinding.JOSSO_SOAP.getValue());
            setPropertyValue(ssoSmSoap, "location", "/JOSSO/SSOSessionManager/SOAP");
            endpoints.add(ssoSmSoap);
            
            setPropertyAsBeans(bc, "endpoints", endpoints);

            setPropertyRef(bp, "bindingChannel", bc.getName());
        }
        
        // binding-mediator
        Bean bindingMediator = newBean(bpBeans, bp.getName() + "-binding-mediator", JossoMediator.class);
        setPropertyValue(bindingMediator, "logMessages", true);
        setPropertyBean(bindingMediator, "bindingFactory", newAnonymousBean(JossoBindingFactory.class));

        // artifactQueueManager
        setPropertyRef(bindingMediator, "artifactQueueManager", event.getContext().getCurrentModule().getId() + "-aqm");

        setPropertyValue(bindingMediator, "errorUrl", resolveLocationBaseUrl(provider.getBindingChannel().getLocation()) + "/idbus-ui/error.do");

        // logger
        List<Bean> bpLogBuilders = new ArrayList<Bean>();
        bpLogBuilders.add(newAnonymousBean(JossoLogMessageBuilder.class));
        bpLogBuilders.add(newAnonymousBean(CamelLogMessageBuilder.class));
        bpLogBuilders.add(newAnonymousBean(HttpLogMessageBuilder.class));

        Bean bpLogger = newAnonymousBean(DefaultMediationLogger.class.getName());
        bpLogger.setName(bp.getName() + "-mediation-logger");
        setPropertyValue(bpLogger, "category", "idbus.mediation.wire." + bp.getName());
        setPropertyAsBeans(bpLogger, "messageBuilders", bpLogBuilders);
        setPropertyBean(bindingMediator, "logger", bpLogger);
    }

    @Override
    public Object after(TransformEvent event) throws TransformException {

        BindingProvider provider = (BindingProvider) event.getData();
        IdProjectModule module = event.getContext().getCurrentModule();
        Beans baseBeans = (Beans) event.getContext().get("beans");
        Beans bpBeans = (Beans) event.getContext().get("bpBeans");
        
        Bean bpBean = getBeansOfType(bpBeans, BindingProviderImpl.class.getName()).iterator().next();
        
        // Wire provider to COT
        Collection<Bean> cots = getBeansOfType(baseBeans, CircleOfTrustImpl.class.getName());
        if (cots.size() == 1) {
            Bean cot = cots.iterator().next();
            addPropertyBeansAsRefsToSet(cot, "providers", bpBean);
            /*
            String dependsOn = cot.getDependsOn();
            if (dependsOn == null || dependsOn.equals("")) {
                cot.setDependsOn(bpBean.getName());
            } else {
                cot.setDependsOn(dependsOn + "," + bpBean.getName());
            }
            */
        }

        // Mediation Unit
        Collection<Bean> mus = getBeansOfType(baseBeans, OsgiIdentityMediationUnit.class.getName());
        if (mus.size() == 1) {
            Bean mu = mus.iterator().next();
            Collection<Bean> bindingChannels = getBeansOfType(bpBeans, BindingChannelImpl.class.getName());
            for (Bean b : bindingChannels) {
                addPropertyBeansAsRefs(mu, "channels", b);
            }
        } else {
            throw new TransformException("One and only one Identity Mediation Unit is expected, found " + mus.size());
        }

        IdProjectResource<Beans> rBeans =  new IdProjectResource<Beans>(idGen.generateId(), bpBean.getName(), bpBean.getName(), "spring-beans", bpBeans);
        rBeans.setClassifier("jaxb");
        rBeans.setNameSpace(bpBean.getName());

        module.addResource(rBeans);

        return rBeans;
    }
}