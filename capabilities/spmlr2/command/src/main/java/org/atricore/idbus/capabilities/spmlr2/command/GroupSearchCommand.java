package org.atricore.idbus.capabilities.spmlr2.command;

import oasis.names.tc.spml._2._0.RequestType;
import oasis.names.tc.spml._2._0.SelectionType;
import oasis.names.tc.spml._2._0.search.*;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.gogo.commands.Option;
import org.atricore.idbus.capabilities.spmlr2.main.SPMLR2Constants;
import org.atricore.idbus.capabilities.spmlr2.main.binding.SpmlR2Binding;
import org.atricore.idbus.capabilities.spmlr2.main.psp.SpmlR2PSPMediator;
import org.atricore.idbus.kernel.main.federation.metadata.EndpointDescriptor;
import org.atricore.idbus.kernel.main.mediation.channel.PsPChannel;
import org.atricore.idbus.kernel.main.mediation.provider.ProvisioningServiceProvider;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

/**
 * @author <a href=mailto:sgonzalez@atricor.org>Sebastian Gonzalez Oyuela</a>
 */
@Command(scope = "spml", name = "grpsearch", description = "SPML Group SEARCH operation")
public class GroupSearchCommand extends SpmlCommandSupport {

    @Option(name = "-q", aliases = "--query", description = "SPML Group search query", required = false, multiValued = false)
    String qry;

    @Option(name = "-n", aliases = "--name", description = "SPML Group name", required = false, multiValued = false)
    String name;


    @Override
    protected RequestType buildSpmlRequest(ProvisioningServiceProvider psp, PsPChannel pspChannel) {
        SearchRequestType spmlRequest = new SearchRequestType();
        spmlRequest.setRequestID(uuidGenerator.generateId());
        spmlRequest.getOtherAttributes().put(SPMLR2Constants.groupAttr, "true");

        SearchQueryType spmlQry  = new SearchQueryType();
        spmlQry.setScope(ScopeType.ONE_LEVEL);
        spmlQry.setTargetID(targetId);

        SelectionType spmlSelect = new SelectionType();
        spmlSelect.setNamespaceURI("http://www.w3.org/TR/xpath20");

        if (name != null)
            qry = "/groups[name='"+name+"']";

        spmlSelect.setPath(qry);
        spmlSelect.getOtherAttributes().put(SPMLR2Constants.groupAttr, "true");

        LogicalOperatorType spmlAnd = new LogicalOperatorType();
        spmlAnd.getAny().add(spmlSelect);

        JAXBElement jaxbSelect= new JAXBElement(
                new QName( SPMLR2Constants.SPML_NS, "select"),
                spmlSelect.getClass(),
                spmlSelect
        );

        spmlQry.getAny().add(jaxbSelect);

        spmlRequest.setQuery(spmlQry);
        return spmlRequest;
    }
}
