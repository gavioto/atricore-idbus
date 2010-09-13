/*
 * Atricore IDBus
 *
 *   Copyright 2009, Atricore Inc.
 *
 *   This is free software; you can redistribute it and/or modify it
 *   under the terms of the GNU Lesser General Public License as
 *   published by the Free Software Foundation; either version 2.1 of
 *   the License, or (at your option) any later version.
 *
 *   This software is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *   Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public
 *   License along with this software; if not, write to the Free
 *   Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 *   02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package com.atricore.idbus.console.services.impl;

import com.atricore.idbus.console.services.spi.IdentityApplianceManagementAjaxService;
import com.atricore.idbus.console.services.spi.IdentityServerException;
import com.atricore.idbus.console.services.spi.request.*;
import com.atricore.idbus.console.services.spi.response.*;
import com.atricore.idbus.console.lifecycle.main.domain.IdentityAppliance;
import com.atricore.idbus.console.services.dto.*;
import com.atricore.idbus.console.lifecycle.main.spi.IdentityApplianceManagementService;
import org.dozer.DozerBeanMapper;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: Dejan Maric
 */
public class IdentityApplianceManagementAjaxServiceImpl implements IdentityApplianceManagementAjaxService {

    private IdentityApplianceManagementService idApplianceManagementService;
    private DozerBeanMapper dozerMapper;

    public BuildIdentityApplianceResponse buildIdentityAppliance(BuildIdentityApplianceRequest req) throws IdentityServerException {
        com.atricore.idbus.console.lifecycle.main.spi.request.BuildIdentityApplianceRequest beReq =
                dozerMapper.map(req, com.atricore.idbus.console.lifecycle.main.spi.request.BuildIdentityApplianceRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.BuildIdentityApplianceResponse beRes = null;
        try {
            beRes = idApplianceManagementService.buildIdentityAppliance(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, BuildIdentityApplianceResponse.class);
    }
    
    public DeployIdentityApplianceResponse deployIdentityAppliance(DeployIdentityApplianceRequest req) throws IdentityServerException {
        com.atricore.idbus.console.lifecycle.main.spi.request.DeployIdentityApplianceRequest beReq =
                dozerMapper.map(req, com.atricore.idbus.console.lifecycle.main.spi.request.DeployIdentityApplianceRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.DeployIdentityApplianceResponse beRes = null;
        try {
            beRes = idApplianceManagementService.deployIdentityAppliance(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, DeployIdentityApplianceResponse.class);
    }

    public UndeployIdentityApplianceResponse undeployIdentityAppliance(UndeployIdentityApplianceRequest req) throws IdentityServerException {
        com.atricore.idbus.console.lifecycle.main.spi.request.UndeployIdentityApplianceRequest beReq =
                dozerMapper.map(req, com.atricore.idbus.console.lifecycle.main.spi.request.UndeployIdentityApplianceRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.UndeployIdentityApplianceResponse beRes = null;
        try {
            beRes = idApplianceManagementService.undeployIdentityAppliance(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, UndeployIdentityApplianceResponse.class);
    }

    public StartIdentityApplianceResponse startIdentityAppliance(StartIdentityApplianceRequest req) throws IdentityServerException {
        com.atricore.idbus.console.lifecycle.main.spi.request.StartIdentityApplianceRequest beReq =
                dozerMapper.map(req, com.atricore.idbus.console.lifecycle.main.spi.request.StartIdentityApplianceRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.StartIdentityApplianceResponse beRes = null;
        try {
            beRes = idApplianceManagementService.startIdentityAppliance(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, StartIdentityApplianceResponse.class);
    }

    public StopIdentityApplianceResponse stopIdentityAppliance(StopIdentityApplianceRequest req) throws IdentityServerException {
        com.atricore.idbus.console.lifecycle.main.spi.request.StopIdentityApplianceRequest beReq =
                dozerMapper.map(req, com.atricore.idbus.console.lifecycle.main.spi.request.StopIdentityApplianceRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.StopIdentityApplianceResponse beRes = null;
        try {
            beRes = idApplianceManagementService.stopIdentityAppliance(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, StopIdentityApplianceResponse.class);
    }

    public DisposeIdentityApplianceResponse disposeIdentityAppliance(DisposeIdentityApplianceRequest req) throws IdentityServerException {
        com.atricore.idbus.console.lifecycle.main.spi.request.DisposeIdentityApplianceRequest beReq =
                dozerMapper.map(req, com.atricore.idbus.console.lifecycle.main.spi.request.DisposeIdentityApplianceRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.DisposeIdentityApplianceResponse beRes = null;
        try {
            beRes = idApplianceManagementService.disposeIdentityAppliance(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, DisposeIdentityApplianceResponse.class);
    }

    public ImportIdentityApplianceResponse importIdentityAppliance(ImportIdentityApplianceRequest req) throws IdentityServerException {
        com.atricore.idbus.console.lifecycle.main.spi.request.ImportIdentityApplianceRequest beReq =
                dozerMapper.map(req,  com.atricore.idbus.console.lifecycle.main.spi.request.ImportIdentityApplianceRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.ImportIdentityApplianceResponse beRes = null;
        try {
            beRes = idApplianceManagementService.importIdentityAppliance(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, ImportIdentityApplianceResponse.class);
    }

    public ExportIdentityApplianceResponse ExportIdentityAppliance(ExportIdentityApplianceRequest req) throws IdentityServerException {
        com.atricore.idbus.console.lifecycle.main.spi.request.ExportIdentityApplianceRequest beReq =
                dozerMapper.map(req,  com.atricore.idbus.console.lifecycle.main.spi.request.ExportIdentityApplianceRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.ExportIdentityApplianceResponse beRes = null;
        try {
            beRes = idApplianceManagementService.exportIdentityAppliance(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, ExportIdentityApplianceResponse.class);
    }

    public ManageIdentityApplianceLifeCycleResponse manageIdentityApplianceLifeCycle(ManageIdentityApplianceLifeCycleRequest req) throws IdentityServerException {
        com.atricore.idbus.console.lifecycle.main.spi.request.ManageIdentityApplianceLifeCycleRequest beReq =
                dozerMapper.map(req,  com.atricore.idbus.console.lifecycle.main.spi.request.ManageIdentityApplianceLifeCycleRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.ManageIdentityApplianceLifeCycleResponse beRes = null;
        try {
            beRes = idApplianceManagementService.manageIdentityApplianceLifeCycle(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, ManageIdentityApplianceLifeCycleResponse.class);
    }

    public ActivateExecEnvResponse activateExecEnv(ActivateExecEnvRequest req) throws IdentityServerException {
//        throw new UnsupportedOperationException("NOT IMPLEMENTED");
        com.atricore.idbus.console.lifecycle.main.spi.request.ActivateExecEnvRequest beReq =
                dozerMapper.map(req,  com.atricore.idbus.console.lifecycle.main.spi.request.ActivateExecEnvRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.ActivateExecEnvResponse beRes = null;
        try {
            beRes = idApplianceManagementService.activateExecEnv(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, ActivateExecEnvResponse.class);
    }

    public CreateSimpleSsoResponse createSimpleSso(CreateSimpleSsoRequest req)
            throws IdentityServerException {

        IdentityApplianceDefinitionDTO iad = req.getIdentityApplianceDefinition();

        iad.getProviders().add(createIdentityProvider(iad));

        // create josso activations
        // providers that are currently in providers list are service providers
        for (ProviderDTO sp : iad.getProviders()) {
            if (sp.getRole().equals(ProviderRoleDTO.SSOServiceProvider)) {
                createJOSSOActivation(iad, (ServiceProviderDTO)sp);
            }
        }

        //TODO set Locations for all objects
        //TODO add bindings and profiles to channels

        IdentityApplianceDTO idAppliance = new IdentityApplianceDTO();
        idAppliance.setIdApplianceDefinition(iad);
        idAppliance.setState(IdentityApplianceStateDTO.PROJECTED.toString());

        //here we'll set STORE to NULL, and later we'll fetch it from DB and update the appliance
        Long storeId = null;
        if (iad.getKeystore() != null) {
            storeId = iad.getKeystore().getStore().getId();
            iad.getKeystore().setStore(null);
        }

        com.atricore.idbus.console.lifecycle.main.spi.request.AddIdentityApplianceRequest addIdApplianceReq =
                new com.atricore.idbus.console.lifecycle.main.spi.request.AddIdentityApplianceRequest();
        addIdApplianceReq.setIdentityAppliance(dozerMapper.map(idAppliance, IdentityAppliance.class));
        com.atricore.idbus.console.lifecycle.main.spi.response.AddIdentityApplianceResponse beRes = null;

        try {
            beRes = idApplianceManagementService.addIdentityAppliance(addIdApplianceReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        AddIdentityApplianceResponse res = dozerMapper.map(beRes, AddIdentityApplianceResponse.class);
        idAppliance = res.getAppliance();

        for (ProviderDTO p : idAppliance.getIdApplianceDefinition().getProviders()) {
            if (p instanceof ServiceProviderDTO) {
                populateServiceProvider((ServiceProviderDTO)p, iad);
            }
        }

        IdentityAppliance foundAppliance = prepareApplianceForUpdate(idAppliance);
        idAppliance = this.updateAppliance(foundAppliance).getAppliance();

        if (storeId != null) {
            //lookup store
            LookupResourceByIdRequest lookupStoreReq = new LookupResourceByIdRequest();
            lookupStoreReq.setResourceId(new Long(storeId).toString());

            com.atricore.idbus.console.lifecycle.main.spi.request.LookupResourceByIdRequest beLookupStoreReq =
                    dozerMapper.map(lookupStoreReq, com.atricore.idbus.console.lifecycle.main.spi.request.LookupResourceByIdRequest.class);

            com.atricore.idbus.console.lifecycle.main.spi.response.LookupResourceByIdResponse beLookupStoreRes = null;

            try {
                beLookupStoreRes = idApplianceManagementService.lookupResourceById(beLookupStoreReq);
            } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
                throw new IdentityServerException(e);
            }

            foundAppliance = prepareApplianceForUpdate(idAppliance);
            foundAppliance.getIdApplianceDefinition().getKeystore().setStore(beLookupStoreRes.getResource());
            idAppliance = this.updateAppliance(foundAppliance).getAppliance();
        }

        CreateSimpleSsoResponse response = new CreateSimpleSsoResponse();
        response.setAppliance(idAppliance);
        return response;
    }

    public AddIdentityApplianceResponse addIdentityAppliance(AddIdentityApplianceRequest req) throws IdentityServerException {
        com.atricore.idbus.console.lifecycle.main.spi.request.AddIdentityApplianceRequest beReq =
                dozerMapper.map(req,  com.atricore.idbus.console.lifecycle.main.spi.request.AddIdentityApplianceRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.AddIdentityApplianceResponse beRes = null;
        try {
            beRes = idApplianceManagementService.addIdentityAppliance(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, AddIdentityApplianceResponse.class);
    }

    public LookupIdentityApplianceByIdResponse lookupIdentityApplianceById(LookupIdentityApplianceByIdRequest req) throws IdentityServerException {
        com.atricore.idbus.console.lifecycle.main.spi.request.LookupIdentityApplianceByIdRequest beReq =
                dozerMapper.map(req,  com.atricore.idbus.console.lifecycle.main.spi.request.LookupIdentityApplianceByIdRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.LookupIdentityApplianceByIdResponse beRes = null;
        try {
            beRes = idApplianceManagementService.lookupIdentityApplianceById(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, LookupIdentityApplianceByIdResponse.class);
    }

    public UpdateIdentityApplianceResponse updateIdentityAppliance(UpdateIdentityApplianceRequest req) throws IdentityServerException{
        //First find identity appliance in DB
        com.atricore.idbus.console.lifecycle.main.spi.request.LookupIdentityApplianceByIdRequest beLookupReq =
                new  com.atricore.idbus.console.lifecycle.main.spi.request.LookupIdentityApplianceByIdRequest();

        IdentityAppliance updatedAppliance = prepareApplianceForUpdate(req.getAppliance());

        //Finally call the update method
//        this.updateAppliance(updatedAppliance);
        return this.updateAppliance(updatedAppliance);
    }

    private IdentityAppliance prepareApplianceForUpdate(IdentityApplianceDTO updatedApplianceDto) throws IdentityServerException {
        com.atricore.idbus.console.lifecycle.main.spi.request.LookupIdentityApplianceByIdRequest beLookupReq =
                new  com.atricore.idbus.console.lifecycle.main.spi.request.LookupIdentityApplianceByIdRequest();

        beLookupReq.setIdentityApplianceId(new Long(updatedApplianceDto.getId()).toString());

        com.atricore.idbus.console.lifecycle.main.spi.response.LookupIdentityApplianceByIdResponse beLookupRes =
                null;
        try {
            beLookupRes = idApplianceManagementService.lookupIdentityApplianceById(beLookupReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }

        IdentityAppliance foundAppliance = beLookupRes.getIdentityAppliance();

        //Then, update the found identity appliance with data from DTO object
        dozerMapper.map(updatedApplianceDto, foundAppliance);
        return foundAppliance;
    }

    private UpdateIdentityApplianceResponse updateAppliance(IdentityAppliance appliance) throws IdentityServerException {
        //Prepare Request object for calling BE updateIdentityAppliance method
        com.atricore.idbus.console.lifecycle.main.spi.request.UpdateIdentityApplianceRequest beReq =
              new com.atricore.idbus.console.lifecycle.main.spi.request.UpdateIdentityApplianceRequest();

        beReq.setAppliance(appliance);

        com.atricore.idbus.console.lifecycle.main.spi.response.UpdateIdentityApplianceResponse beRes = null;
        try {
            beRes = idApplianceManagementService.updateIdentityAppliance(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, UpdateIdentityApplianceResponse.class);
    }


    public RemoveIdentityApplianceResponse removeIdentityAppliance(RemoveIdentityApplianceRequest req) throws IdentityServerException{
        //First find identity appliance in DB
        com.atricore.idbus.console.lifecycle.main.spi.request.LookupIdentityApplianceByIdRequest beLookupReq =
                new  com.atricore.idbus.console.lifecycle.main.spi.request.LookupIdentityApplianceByIdRequest();

        beLookupReq.setIdentityApplianceId(new Long(req.getIdentityAppliance().getId()).toString());

        com.atricore.idbus.console.lifecycle.main.spi.response.LookupIdentityApplianceByIdResponse beLookupRes =
                null;
        try {
            beLookupRes = idApplianceManagementService.lookupIdentityApplianceById(beLookupReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }

        IdentityAppliance foundAppliance = beLookupRes.getIdentityAppliance();

        //Prepare Request object for calling BE updateIdentityAppliance method
        com.atricore.idbus.console.lifecycle.main.spi.request.RemoveIdentityApplianceRequest beReq =
                new com.atricore.idbus.console.lifecycle.main.spi.request.RemoveIdentityApplianceRequest();

        beReq.setIdentityAppliance(foundAppliance);

        com.atricore.idbus.console.lifecycle.main.spi.response.RemoveIdentityApplianceResponse beRes = null;
        try {
            beRes = idApplianceManagementService.removeIdentityAppliance(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, RemoveIdentityApplianceResponse.class);
    }

    public ListIdentityAppliancesResponse listIdentityAppliances(ListIdentityAppliancesRequest req) throws IdentityServerException {
        com.atricore.idbus.console.lifecycle.main.spi.request.ListIdentityAppliancesRequest beReq =
                dozerMapper.map(req,  com.atricore.idbus.console.lifecycle.main.spi.request.ListIdentityAppliancesRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.ListIdentityAppliancesResponse beRes = null;
        try {
            beRes = idApplianceManagementService.listIdentityAppliances(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, ListIdentityAppliancesResponse.class);
    }

    public void setIdApplianceManagementService(IdentityApplianceManagementService idApplianceManagementService) {
        this.idApplianceManagementService = idApplianceManagementService;
    }

    public void setDozerMapper(DozerBeanMapper dozerMapper) {
        this.dozerMapper = dozerMapper;
    }

    public AddResourceResponse addResource(AddResourceRequest req) throws IdentityServerException {
        com.atricore.idbus.console.lifecycle.main.spi.request.AddResourceRequest beReq =
                dozerMapper.map(req,  com.atricore.idbus.console.lifecycle.main.spi.request.AddResourceRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.AddResourceResponse beRes = null;
        try {
            beRes = idApplianceManagementService.addResource(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, AddResourceResponse.class);
    }

    public LookupResourceByIdResponse lookupResourceById(LookupResourceByIdRequest req) throws IdentityServerException {
        com.atricore.idbus.console.lifecycle.main.spi.request.LookupResourceByIdRequest beReq =
                dozerMapper.map(req,  com.atricore.idbus.console.lifecycle.main.spi.request.LookupResourceByIdRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.LookupResourceByIdResponse beRes = null;
        try {
            beRes = idApplianceManagementService.lookupResourceById(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, LookupResourceByIdResponse.class);
    }

    /****************************
     * List methods
     ***************************/
    public ListIdentityVaultsResponse listIdentityVaults(ListIdentityVaultsRequest req) throws IdentityServerException{
        com.atricore.idbus.console.lifecycle.main.spi.request.ListIdentityVaultsRequest beReq =
                dozerMapper.map(req,  com.atricore.idbus.console.lifecycle.main.spi.request.ListIdentityVaultsRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.ListIdentityVaultsResponse beRes = null;
        try {
            beRes = idApplianceManagementService.listIdentityVaults(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, ListIdentityVaultsResponse.class);
    }

    public ListUserInformationLookupsResponse listUserInformationLookups(ListUserInformationLookupsRequest req) throws IdentityServerException{
        com.atricore.idbus.console.lifecycle.main.spi.request.ListUserInformationLookupsRequest beReq =
                dozerMapper.map(req,  com.atricore.idbus.console.lifecycle.main.spi.request.ListUserInformationLookupsRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.ListUserInformationLookupsResponse beRes = null;
        try {
            beRes = idApplianceManagementService.listUserInformationLookups(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, ListUserInformationLookupsResponse.class);
    }

    public ListAccountLinkagePoliciesResponse listAccountLinkagePolicies(ListAccountLinkagePoliciesRequest req) throws IdentityServerException{
        com.atricore.idbus.console.lifecycle.main.spi.request.ListAccountLinkagePoliciesRequest beReq =
                dozerMapper.map(req,  com.atricore.idbus.console.lifecycle.main.spi.request.ListAccountLinkagePoliciesRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.ListAccountLinkagePoliciesResponse beRes = null;
        try {
            beRes = idApplianceManagementService.listAccountLinkagePolicies(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, ListAccountLinkagePoliciesResponse.class);
    }

    public ListAuthenticationContractsResponse listAuthenticationContracts(ListAuthenticationContractsRequest req) throws IdentityServerException{
        com.atricore.idbus.console.lifecycle.main.spi.request.ListAuthenticationContractsRequest beReq =
                dozerMapper.map(req,  com.atricore.idbus.console.lifecycle.main.spi.request.ListAuthenticationContractsRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.ListAuthenticationContractsResponse beRes = null;
        try {
            beRes = idApplianceManagementService.listAuthenticationContracts(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, ListAuthenticationContractsResponse.class);
    }

    public ListAuthenticationMechanismsResponse listAuthenticationMechanisms(ListAuthenticationMechanismsRequest req) throws IdentityServerException{
        com.atricore.idbus.console.lifecycle.main.spi.request.ListAuthenticationMechanismsRequest beReq =
                dozerMapper.map(req,  com.atricore.idbus.console.lifecycle.main.spi.request.ListAuthenticationMechanismsRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.ListAuthenticationMechanismsResponse beRes = null;
        try {
            beRes = idApplianceManagementService.listAuthenticationMechanisms(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, ListAuthenticationMechanismsResponse.class);
    }

    public ListAttributeProfilesResponse listAttributeProfiles(ListAttributeProfilesRequest req) throws IdentityServerException{
        com.atricore.idbus.console.lifecycle.main.spi.request.ListAttributeProfilesRequest beReq =
                dozerMapper.map(req,  com.atricore.idbus.console.lifecycle.main.spi.request.ListAttributeProfilesRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.ListAttributeProfilesResponse beRes = null;
        try {
            beRes = idApplianceManagementService.listAttributeProfiles(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, ListAttributeProfilesResponse.class);
    }

    public ListAuthAssertionEmissionPoliciesResponse listAuthAssertionEmissionPolicies(ListAuthAssertionEmissionPoliciesRequest req) throws IdentityServerException{
        com.atricore.idbus.console.lifecycle.main.spi.request.ListAuthAssertionEmissionPoliciesRequest beReq =
                dozerMapper.map(req,  com.atricore.idbus.console.lifecycle.main.spi.request.ListAuthAssertionEmissionPoliciesRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.ListAuthAssertionEmissionPoliciesResponse beRes = null;
        try {
            beRes = idApplianceManagementService.listAuthAssertionEmissionPolicies(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, ListAuthAssertionEmissionPoliciesResponse.class);
    }


    /****************************
     * Lookup methods
     ***************************/
    public LookupIdentityVaultByIdResponse lookupIdentityVaultById(LookupIdentityVaultByIdRequest req) throws IdentityServerException{
        com.atricore.idbus.console.lifecycle.main.spi.request.LookupIdentityVaultByIdRequest beReq =
                dozerMapper.map(req,  com.atricore.idbus.console.lifecycle.main.spi.request.LookupIdentityVaultByIdRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.LookupIdentityVaultByIdResponse beRes = null;
        try {
            beRes = idApplianceManagementService.lookupIdentityVaultById(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, LookupIdentityVaultByIdResponse.class);
    }

    public LookupUserInformationLookupByIdResponse lookupUserInformationLookupById(LookupUserInformationLookupByIdRequest req) throws IdentityServerException{
        com.atricore.idbus.console.lifecycle.main.spi.request.LookupUserInformationLookupByIdRequest beReq =
                dozerMapper.map(req,  com.atricore.idbus.console.lifecycle.main.spi.request.LookupUserInformationLookupByIdRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.LookupUserInformationLookupByIdResponse beRes = null;
        try {
            beRes = idApplianceManagementService.lookupUserInformationLookupById(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, LookupUserInformationLookupByIdResponse.class);
    }

    public LookupAccountLinkagePolicyByIdResponse lookupAccountLinkagePolicyById(LookupAccountLinkagePolicyByIdRequest req) throws IdentityServerException{
        com.atricore.idbus.console.lifecycle.main.spi.request.LookupAccountLinkagePolicyByIdRequest beReq =
                dozerMapper.map(req,  com.atricore.idbus.console.lifecycle.main.spi.request.LookupAccountLinkagePolicyByIdRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.LookupAccountLinkagePolicyByIdResponse beRes = null;
        try {
            beRes = idApplianceManagementService.lookupAccountLinkagePolicyById(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, LookupAccountLinkagePolicyByIdResponse.class);
    }

    public LookupAuthenticationContractByIdResponse lookupAuthenticationContractById(LookupAuthenticationContractByIdRequest req) throws IdentityServerException{
        com.atricore.idbus.console.lifecycle.main.spi.request.LookupAuthenticationContractByIdRequest beReq =
                dozerMapper.map(req,  com.atricore.idbus.console.lifecycle.main.spi.request.LookupAuthenticationContractByIdRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.LookupAuthenticationContractByIdResponse beRes = null;
        try {
            beRes = idApplianceManagementService.lookupAuthenticationContractById(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, LookupAuthenticationContractByIdResponse.class);
    }

    public LookupAuthenticationMechanismByIdResponse lookupAuthenticationMechanismById(LookupAuthenticationMechanismByIdRequest req) throws IdentityServerException{
        com.atricore.idbus.console.lifecycle.main.spi.request.LookupAuthenticationMechanismByIdRequest beReq =
                dozerMapper.map(req,  com.atricore.idbus.console.lifecycle.main.spi.request.LookupAuthenticationMechanismByIdRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.LookupAuthenticationMechanismByIdResponse beRes = null;
        try {
            beRes = idApplianceManagementService.lookupAuthenticationMechanismById(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, LookupAuthenticationMechanismByIdResponse.class);
    }

    public LookupAttributeProfileByIdResponse lookupAttributeProfileById(LookupAttributeProfileByIdRequest req) throws IdentityServerException{
        com.atricore.idbus.console.lifecycle.main.spi.request.LookupAttributeProfileByIdRequest beReq =
                dozerMapper.map(req,  com.atricore.idbus.console.lifecycle.main.spi.request.LookupAttributeProfileByIdRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.LookupAttributeProfileByIdResponse beRes = null;
        try {
            beRes = idApplianceManagementService.lookupAttributeProfileById(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, LookupAttributeProfileByIdResponse.class);
    }

    public LookupAuthAssertionEmissionPolicyByIdResponse lookupAuthAssertionEmissionPolicyById(LookupAuthAssertionEmissionPolicyByIdRequest req) throws IdentityServerException{
        com.atricore.idbus.console.lifecycle.main.spi.request.LookupAuthAssertionEmissionPolicyByIdRequest beReq =
                dozerMapper.map(req,  com.atricore.idbus.console.lifecycle.main.spi.request.LookupAuthAssertionEmissionPolicyByIdRequest.class);

        com.atricore.idbus.console.lifecycle.main.spi.response.LookupAuthAssertionEmissionPolicyByIdResponse beRes = null;
        try {
            beRes = idApplianceManagementService.lookupAuthAssertionEmissionPolicyById(beReq);
        } catch (com.atricore.idbus.console.lifecycle.main.exception.IdentityServerException e) {
            throw new IdentityServerException(e);
        }
        return dozerMapper.map(beRes, LookupAuthAssertionEmissionPolicyByIdResponse.class);
    }


    /******************************************************
     * Helper methods
     ******************************************************/

    private void populateServiceProvider(ServiceProviderDTO sp, IdentityApplianceDefinitionDTO iad) {
        sp.setIdentityAppliance(iad);
        sp.setDescription(sp.getName() + " description");

        LocationDTO location = sp.getLocation();
        location.setProtocol(iad.getLocation().getProtocol());
        location.setHost(iad.getLocation().getHost());
        location.setPort(iad.getLocation().getPort());
        location.setContext(iad.getLocation().getContext());
        location.setUri(createUrlSafeString(sp.getName() + "-sp"));
        
        sp.getActiveBindings().add(BindingDTO.SSO_ARTIFACT);
        sp.getActiveBindings().add(BindingDTO.SSO_REDIRECT);

        sp.getActiveProfiles().add(ProfileDTO.SSO);
        sp.getActiveProfiles().add(ProfileDTO.SSO_SLO);

        IdentityProviderChannelDTO idpChannel = new IdentityProviderChannelDTO();
        idpChannel.setName(sp.getName() + " to idp default channel");
        idpChannel.setTarget(sp);

        LocationDTO idpLocation = new LocationDTO();
        idpLocation.setProtocol(iad.getLocation().getProtocol());
        idpLocation.setHost(iad.getLocation().getHost());
        idpLocation.setPort(iad.getLocation().getPort());
        idpLocation.setContext(iad.getLocation().getContext());
        idpLocation.setUri(createUrlSafeString(sp.getName()) + "/SAML2");
        idpChannel.setLocation(idpLocation);

        idpChannel.getActiveBindings().add(BindingDTO.SAMLR2_ARTIFACT);
        idpChannel.getActiveBindings().add(BindingDTO.SAMLR2_HTTP_REDIRECT);

        idpChannel.getActiveProfiles().add(ProfileDTO.SSO);
        idpChannel.getActiveProfiles().add(ProfileDTO.SSO_SLO);

//        sp.setDefaultChannel(idpChannel);

        SamlR2ProviderConfigDTO spSamlConfig = new SamlR2ProviderConfigDTO();
        spSamlConfig.setName(sp.getName() + " samlr2 config");
        spSamlConfig.setSigner(iad.getKeystore());
        spSamlConfig.setEncrypter(iad.getKeystore());
        sp.setConfig(spSamlConfig);
    }

    private ProviderDTO createIdentityProvider(IdentityApplianceDefinitionDTO iad) {
        IdentityProviderDTO idp = new IdentityProviderDTO();
        idp.setName(iad.getName() + " idp");
        idp.setIdentityAppliance(iad);

        ServiceProviderChannelDTO spChannel = new ServiceProviderChannelDTO();
        spChannel.setName(idp.getName() + " to sp default channel");
        spChannel.setTarget(idp);

        spChannel.getActiveBindings().add(BindingDTO.SAMLR2_ARTIFACT);
        spChannel.getActiveBindings().add(BindingDTO.SAMLR2_HTTP_REDIRECT);
        spChannel.getActiveBindings().add(BindingDTO.SAMLR2_HTTP_POST);
        spChannel.getActiveBindings().add(BindingDTO.SAMLR2_SOAP);

        spChannel.getActiveProfiles().add(ProfileDTO.SSO);
        spChannel.getActiveProfiles().add(ProfileDTO.SSO_SLO);

        LocationDTO idpLocation = new LocationDTO();
        idpLocation.setProtocol(iad.getLocation().getProtocol());
        idpLocation.setHost(iad.getLocation().getHost());
        idpLocation.setPort(iad.getLocation().getPort());
        idpLocation.setContext(iad.getLocation().getContext());
        idpLocation.setUri(createUrlSafeString(idp.getName()));
        idp.setLocation(idpLocation);

        LocationDTO spChannelLocation = new LocationDTO();
        spChannelLocation.setProtocol(iad.getLocation().getProtocol());
        spChannelLocation.setHost(iad.getLocation().getHost());
        spChannelLocation.setPort(iad.getLocation().getPort());
        spChannelLocation.setContext(iad.getLocation().getContext());
        spChannelLocation.setUri(createUrlSafeString(idp.getName()));
        spChannel.setLocation(spChannelLocation);

        //simple sso wizard creates only one vault
//        spChannel.setIdentityVault(iad.getIdentityVaults().get(0));

//        idp.setDefaultChannel(spChannel);

        SamlR2ProviderConfigDTO idpSamlConfig = new SamlR2ProviderConfigDTO();
        idpSamlConfig.setName(idp.getName() + " samlr2 config");
        idpSamlConfig.setSigner(iad.getKeystore());
        idpSamlConfig.setEncrypter(iad.getKeystore());
        idp.setConfig(idpSamlConfig);

        Set<AuthenticationMechanismDTO> authMechanisms = new HashSet<AuthenticationMechanismDTO>();
        BasicAuthenticationDTO authMechanism = new BasicAuthenticationDTO();
        authMechanism.setHashAlgorithm("MD5");
        authMechanism.setHashEncoding("HEX");
        authMechanism.setIgnoreUsernameCase(false);
        authMechanisms.add(authMechanism);
        idp.setAuthenticationMechanisms(authMechanisms);

        return idp;
    }

    private JOSSOActivationDTO createJOSSOActivation(IdentityApplianceDefinitionDTO iad, ServiceProviderDTO sp) {
        JOSSOActivationDTO activation = new JOSSOActivationDTO();
        activation.setPartnerAppId(sp.getName());
        activation.setName(activation.getPartnerAppId().toLowerCase() + "-josso-activation");
        activation.setDescription(activation.getPartnerAppId() + " JOSSO Activation");

        LocationDTO location = new LocationDTO();
        location.setProtocol(sp.getLocation().getProtocol());
        location.setHost(sp.getLocation().getHost());
        location.setPort(sp.getLocation().getPort());
        location.setContext(sp.getLocation().getContext());
        location.setUri(sp.getLocation().getUri());
        activation.setPartnerAppLocation(location);

        if (iad.getExecutionEnvironments() == null) {
            iad.setExecutionEnvironments(new HashSet<ExecutionEnvironmentDTO>());
        }
        
        String targetPlatform = sp.getDescription();
        ExecutionEnvironmentDTO executionEnv = findExecutionEnvironment(iad, sp);
        if (executionEnv == null) {
            if (targetPlatform.equals("jb32") || targetPlatform.equals("jb40") ||
                    targetPlatform.equals("jb42")) {
                executionEnv = new JbossExecutionEnvironmentDTO();
            } else if (targetPlatform.equals("tc50") || targetPlatform.equals("tc55") ||
                    targetPlatform.equals("tc60")) {
                executionEnv = new TomcatExecutionEnvironmentDTO();
            }
            executionEnv.setName(targetPlatform + "-" + sp.getLocation().getHost() +
                    "-" + sp.getLocation().getPort());
            executionEnv.setDescription(executionEnv.getName().replaceAll("-", " "));
            int port = sp.getLocation().getPort();
            String portString = (port == 80 || port == 443 ? "" :  ":" + port);
            executionEnv.setInstallUri(sp.getLocation().getProtocol() + "://" +
                    sp.getLocation().getHost() + portString);
            executionEnv.setActivations(new HashSet<ActivationDTO>());
            iad.getExecutionEnvironments().add(executionEnv);
        }

        activation.setSp(sp);
        sp.setActivation(activation);

        executionEnv.getActivations().add(activation);
        activation.setExecutionEnv(executionEnv);

        return activation;
    }

    private ExecutionEnvironmentDTO findExecutionEnvironment(IdentityApplianceDefinitionDTO iad, ServiceProviderDTO sp) {
        if (iad.getExecutionEnvironments() != null) {
            for (ExecutionEnvironmentDTO executionEnv : iad.getExecutionEnvironments()) {
                if (executionEnv.getName().equals(sp.getDescription() + "-" +
                        sp.getLocation().getHost() + "-" + sp.getLocation().getPort())) {
                    return executionEnv;
                }
            }
        }
        return null;
    }

    /**
     * Creates stringToCheck safe string. String will consist of letters, numbers, underscores and dashes
     * @param stringToCheck
     * @return url safe string
     */
    private String createUrlSafeString(String stringToCheck){
    	String regex = "[^a-zA-Z0-9-_]";
        return stringToCheck.replaceAll(regex, "-").toLowerCase();
    }


}