package org.atricore.idbus.kernel.main.provisioning.spi.request;

/**
 * @author <a href=mailto:sgonzalez@atricore.org>Sebastian Gonzalez Oyuela</a>
 */
public class FindSecurityTokensByExpiresOnBeforeRequest extends AbstractProvisioningRequest {

    private long expiresOnBefore;

    public long getExpiresOnBefore() {
        return expiresOnBefore;
    }

    public void setExpiresOnBefore(long expiresOnBefore) {
        this.expiresOnBefore = expiresOnBefore;
    }
}
