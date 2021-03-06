package org.atricore.idbus.kernel.main.provisioning.spi.response;

import org.atricore.idbus.kernel.main.authn.SecurityToken;

/**
 * @author <a href=mailto:sgonzalez@atricore.org>Sebastian Gonzalez Oyuela</a>
 */
public class FindSecurityTokensByIssueInstantBeforeResponse extends AbstractProvisioningResponse {

    private SecurityToken[] securityTokens;

    public SecurityToken[] getSecurityTokens() {
        return securityTokens;
    }

    public void setSecurityTokens(SecurityToken[] securityTokens) {
        this.securityTokens = securityTokens;
    }
}
