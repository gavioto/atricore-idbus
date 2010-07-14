package com.atricore.idbus.console.services.spi.request;

import com.atricore.idbus.console.services.dto.ResourceDTO;
import org.atricore.idbus.capabilities.management.main.spi.request.AbstractManagementRequest;

public class AddResourceRequest extends AbstractManagementRequest {

    private ResourceDTO resource;

    public ResourceDTO getResource() {
        return resource;
    }

    public void setResource(ResourceDTO resource) {
        this.resource = resource;
    }
}