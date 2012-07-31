package com.atricore.idbus.console.lifecycle.main.transform.transformers.sso;

import com.atricore.idbus.console.lifecycle.main.domain.metadata.InternalSaml2ServiceProvider;
import com.atricore.idbus.console.lifecycle.main.exception.TransformException;
import com.atricore.idbus.console.lifecycle.main.transform.TransformEvent;
import com.atricore.idbus.console.lifecycle.main.transform.transformers.AbstractIdPChannelTransformer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This handles SP components generation, valid for all non-overridden federated connections
 *
 * @author <a href="mailto:sgonzalez@atricore.org">Sebastian Gonzalez Oyuela</a>
 * @version $Id$
 */
public class SPDefaultIdPChannelTransformer extends AbstractIdPChannelTransformer {

    private static final Log logger = LogFactory.getLog(SPDefaultIdPChannelTransformer.class);

    public SPDefaultIdPChannelTransformer() {
        super();
        setContextIdpChannelBean("defaultIdpChannelBean");
    }
    @Override
    public boolean accept(TransformEvent event) {
        return event.getData() instanceof InternalSaml2ServiceProvider &&
                !((InternalSaml2ServiceProvider)event.getData()).isRemote();
    }

    @Override
    public void before(TransformEvent event) throws TransformException {
        InternalSaml2ServiceProvider sp = (InternalSaml2ServiceProvider) event.getData();
        generateSPComponents(sp, null, null, null, null, event.getContext());
    }


}
