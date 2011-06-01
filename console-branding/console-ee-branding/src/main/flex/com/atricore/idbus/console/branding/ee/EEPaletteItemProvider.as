package com.atricore.idbus.console.branding.ee {
import com.atricore.idbus.console.base.diagram.DiagramElementTypes;
import com.atricore.idbus.console.base.palette.PaletteItemProvider;
import com.atricore.idbus.console.base.palette.model.PaletteDrawer;
import com.atricore.idbus.console.base.palette.model.PaletteEntry;
import com.atricore.idbus.console.base.palette.model.PaletteRoot;

public class EEPaletteItemProvider implements PaletteItemProvider {

    public function EEPaletteItemProvider() {
    }

    public function getPalette():PaletteRoot {
        var pr:PaletteRoot  = new PaletteRoot("Identity Appliance Modeler Palette", null, null);
        
        var saml2PaletteDrawer:PaletteDrawer = new PaletteDrawer("Entities", null, null);

        saml2PaletteDrawer.add(
                new PaletteEntry("Identity Provider", EmbeddedIcons.idpMiniIcon, "Identity Provider Entry", DiagramElementTypes.IDENTITY_PROVIDER_ELEMENT_TYPE)

                );
        saml2PaletteDrawer.add(
                new PaletteEntry("External Identity Provider", EmbeddedIcons.externalIdpMiniIcon, "External Identity Provider Entry", DiagramElementTypes.EXTERNAL_IDENTITY_PROVIDER_ELEMENT_TYPE)

                );
        saml2PaletteDrawer.add(
                new PaletteEntry("Service Provider", EmbeddedIcons.spMiniIcon, "Service Provider Entry", DiagramElementTypes.SERVICE_PROVIDER_ELEMENT_TYPE)

                );
        saml2PaletteDrawer.add(
                new PaletteEntry("External Service Provider", EmbeddedIcons.externalSpMiniIcon, "External Service Provider Entry", DiagramElementTypes.EXTERNAL_SERVICE_PROVIDER_ELEMENT_TYPE)

                );

        pr.add(saml2PaletteDrawer);

        var cloudPaletteDrawer:PaletteDrawer = new PaletteDrawer("Cloud Entities", null, null);

        cloudPaletteDrawer.add(
                new PaletteEntry("Salesforce", EmbeddedIcons.salesforceMiniIcon, "Salesforce Entry", DiagramElementTypes.SALESFORCE_ELEMENT_TYPE)

                );
        cloudPaletteDrawer.add(
                new PaletteEntry("Google Apps", EmbeddedIcons.googleAppsMiniIcon, "Google Apps Entry", DiagramElementTypes.GOOGLE_APPS_ELEMENT_TYPE)

                );
        cloudPaletteDrawer.add(
                new PaletteEntry("SugarCRM", EmbeddedIcons.sugarCRMMiniIcon, "SugarCRM Entry", DiagramElementTypes.SUGAR_CRM_ELEMENT_TYPE)

                );

        pr.add(cloudPaletteDrawer);

        var authenticationPaletteDrawer:PaletteDrawer = new PaletteDrawer("Authentication", null, null);
        authenticationPaletteDrawer.add(
                new PaletteEntry("WiKID 2FA", EmbeddedIcons.wikidMiniIcon, "WiKID 2FA Entry", DiagramElementTypes.WIKID_ELEMENT_TYPE)

                );
        authenticationPaletteDrawer.add(
                new PaletteEntry("Directory Service", EmbeddedIcons.directoryServiceMiniIcon, "Directory Service Entry", DiagramElementTypes.DIRECTORY_SERVICE_ELEMENT_TYPE)

                );
        authenticationPaletteDrawer.add(
                new PaletteEntry("Windows Domain", EmbeddedIcons.windowsIntegratedAuthnMiniIcon, "Windows Domain Entry", DiagramElementTypes.WINDOWS_INTEGRATED_AUTHN_ELEMENT_TYPE)

                );


        pr.add(authenticationPaletteDrawer);

        var identitySourcesPaletteDrawer:PaletteDrawer = new PaletteDrawer("Identity Sources", null, null);
        identitySourcesPaletteDrawer.add(
                new PaletteEntry("Identity Vault", EmbeddedIcons.vaultMiniIcon, "Identity Vault Entry", DiagramElementTypes.IDENTITY_VAULT_ELEMENT_TYPE)

                );
        identitySourcesPaletteDrawer.add(
                new PaletteEntry("DB Identity Source", EmbeddedIcons.dbIdentitySourceMiniIcon, "DB Identity Source Entry", DiagramElementTypes.DB_IDENTITY_SOURCE_ELEMENT_TYPE)

                );
        identitySourcesPaletteDrawer.add(
                new PaletteEntry("LDAP Identity Source", EmbeddedIcons.ldapIdentitySourceMiniIcon, "LDAP Identity Source Entry", DiagramElementTypes.LDAP_IDENTITY_SOURCE_ELEMENT_TYPE)

                );
        identitySourcesPaletteDrawer.add(
                new PaletteEntry("XML Identity Source", EmbeddedIcons.xmlIdentitySourceMiniIcon, "XML Identity Source Entry", DiagramElementTypes.XML_IDENTITY_SOURCE_ELEMENT_TYPE)

                );

        pr.add(identitySourcesPaletteDrawer);

        var environmentsPaletteDrawer:PaletteDrawer = new PaletteDrawer("Execution Environments", null, null);

        environmentsPaletteDrawer.add(
                new PaletteEntry("Alfresco", EmbeddedIcons.alfrescoEnvironmentMiniIcon, "Alfresco Environment Entry", DiagramElementTypes.ALFRESCO_EXECUTION_ENVIRONMENT_ELEMENT_TYPE)

                );

        environmentsPaletteDrawer.add(
                new PaletteEntry("Apache", EmbeddedIcons.apacheEnvironmentMiniIcon, "Apache Environment Entry", DiagramElementTypes.APACHE_EXECUTION_ENVIRONMENT_ELEMENT_TYPE)

                );

        environmentsPaletteDrawer.add(
                new PaletteEntry("Java EE", EmbeddedIcons.javaEnvironmentMiniIcon, "Java EE Environment Entry", DiagramElementTypes.JAVAEE_EXECUTION_ENVIRONMENT_ELEMENT_TYPE)

                );

        environmentsPaletteDrawer.add(
                new PaletteEntry("JBoss", EmbeddedIcons.jbossEnvironmentMiniIcon, "JBoss Environment Entry", DiagramElementTypes.JBOSS_EXECUTION_ENVIRONMENT_ELEMENT_TYPE)

                );

        environmentsPaletteDrawer.add(
                new PaletteEntry("JBoss Portal", EmbeddedIcons.jbossEnvironmentMiniIcon, "JBoss Portal Environment Entry", DiagramElementTypes.JBOSS_PORTAL_EXECUTION_ENVIRONMENT_ELEMENT_TYPE)

                );

        environmentsPaletteDrawer.add(
                new PaletteEntry("Liferay Portal", EmbeddedIcons.liferayEnvironmentMiniIcon, "Liferay Portal Environment Entry", DiagramElementTypes.LIFERAY_EXECUTION_ENVIRONMENT_ELEMENT_TYPE)

                );

        environmentsPaletteDrawer.add(
                new PaletteEntry("PHP", EmbeddedIcons.phpEnvironmentMiniIcon, "PHP Environment Entry", DiagramElementTypes.PHP_EXECUTION_ENVIRONMENT_ELEMENT_TYPE)

                );
        
        environmentsPaletteDrawer.add(
                new PaletteEntry("PhpBB", EmbeddedIcons.phpbbEnvironmentMiniIcon, "PhpBB Environment Entry", DiagramElementTypes.PHPBB_EXECUTION_ENVIRONMENT_ELEMENT_TYPE)

                );

        environmentsPaletteDrawer.add(
                new PaletteEntry("Tomcat", EmbeddedIcons.tomcatEnvironmentMiniIcon, "Tomcat Environment Entry", DiagramElementTypes.TOMCAT_EXECUTION_ENVIRONMENT_ELEMENT_TYPE)

                );

        environmentsPaletteDrawer.add(
                new PaletteEntry("Webserver", EmbeddedIcons.webEnvironmentMiniIcon, "Webserver Environment Entry", DiagramElementTypes.WEBSERVER_EXECUTION_ENVIRONMENT_ELEMENT_TYPE)

                );

        environmentsPaletteDrawer.add(
                new PaletteEntry("Weblogic", EmbeddedIcons.weblogicEnvironmentMiniIcon, "Weblogic Environment Entry", DiagramElementTypes.WEBLOGIC_EXECUTION_ENVIRONMENT_ELEMENT_TYPE)

                );

        environmentsPaletteDrawer.add(
                new PaletteEntry("Websphere CE", EmbeddedIcons.websphereEnvironmentMiniIcon, "Websphere CE Environment Entry", DiagramElementTypes.WEBSPHERE_EXECUTION_ENVIRONMENT_ELEMENT_TYPE)

                );

        environmentsPaletteDrawer.add(
                new PaletteEntry("Windows IIS", EmbeddedIcons.windowsEnvironmentMiniIcon, "Windows IIS Environment Entry", DiagramElementTypes.WINDOWS_EXECUTION_ENVIRONMENT_ELEMENT_TYPE)

                );

        pr.add(environmentsPaletteDrawer);

        var connectionPaletteDrawer:PaletteDrawer = new PaletteDrawer("Connections", null, null);
        connectionPaletteDrawer.add(
                new PaletteEntry("Federated Connection", EmbeddedIcons.connectionFederatedMiniIcon, "Federated Connection Entry", DiagramElementTypes.FEDERATED_CONNECTION_ELEMENT_TYPE)

                );

        connectionPaletteDrawer.add(
                new PaletteEntry("Activation", EmbeddedIcons.connectionActivationMiniIcon, "Activation Entry", DiagramElementTypes.ACTIVATION_ELEMENT_TYPE)

                );

        connectionPaletteDrawer.add(
                new PaletteEntry("Identity Lookup", EmbeddedIcons.connectionIdentityLookupMiniIcon , "Identity Lookup Entry", DiagramElementTypes.IDENTITY_LOOKUP_ELEMENT_TYPE)

                );

        connectionPaletteDrawer.add(
                new PaletteEntry("Identity Verification", EmbeddedIcons.connectionDelegatedAuthnMiniIcon , "Identity Verification Entry", DiagramElementTypes.DELEGATED_AUTHENTICATION_ELEMENT_TYPE)

                );

        pr.add(connectionPaletteDrawer);

        return pr;
    }
}
}