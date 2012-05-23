package com.atricore.idbus.console.lifecycle.main.domain.metadata;

import com.atricore.idbus.console.lifecycle.main.transform.annotations.ReEntrant;

// This work very much like connections, make elemets re-entrant
@ReEntrant
public class JOSSO2Resource extends ServiceResource {

    private static final long serialVersionUID = -17370316345222606L;

    private Location partnerAppLocation;

    public Location getPartnerAppLocation() {
        return partnerAppLocation;
    }

    public void setPartnerAppLocation(Location partnerAppLocation) {
        this.partnerAppLocation = partnerAppLocation;
    }
}
