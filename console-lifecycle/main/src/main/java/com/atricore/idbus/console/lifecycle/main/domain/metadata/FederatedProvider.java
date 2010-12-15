package com.atricore.idbus.console.lifecycle.main.domain.metadata;

import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href=mailto:sgonzalez@atricore.org>Sebastian Gonzalez Oyuela</a>
 */
public abstract class FederatedProvider extends Provider {

    private static final long serialVersionUID = 1096573395672061313L;

    private Set<FederatedConnection> federatedConnectionsA;

    private Set<FederatedConnection> federatedConnectionsB;

    public Set<FederatedConnection> getFederatedConnectionsA() {
        if(federatedConnectionsA == null){
            federatedConnectionsA = new HashSet<FederatedConnection>();
        }
        return federatedConnectionsA;
    }

    public void setFederatedConnectionsA(Set<FederatedConnection> federatedConnectionsA) {
        this.federatedConnectionsA = federatedConnectionsA;
    }

    public Set<FederatedConnection> getFederatedConnectionsB() {
        if(federatedConnectionsB == null){
            federatedConnectionsB = new HashSet<FederatedConnection>();
        }
        return federatedConnectionsB;
    }

    public void setFederatedConnectionsB(Set<FederatedConnection> federatedConnectionsB) {
        this.federatedConnectionsB = federatedConnectionsB;
    }
}
