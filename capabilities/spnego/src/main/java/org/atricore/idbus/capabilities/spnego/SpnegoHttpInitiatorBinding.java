/*
 * Atricore IDBus
 *
 * Copyright (c) 2009, Atricore Inc.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.atricore.idbus.capabilities.spnego;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.atricore.idbus.kernel.main.federation.metadata.EndpointDescriptor;
import org.atricore.idbus.kernel.main.mediation.Channel;
import org.atricore.idbus.kernel.main.mediation.MediationMessage;
import org.atricore.idbus.kernel.main.mediation.MediationMessageImpl;
import org.atricore.idbus.kernel.main.mediation.MediationState;
import org.atricore.idbus.kernel.main.mediation.camel.component.binding.AbstractMediationHttpBinding;
import org.atricore.idbus.kernel.main.mediation.camel.component.binding.CamelMediationMessage;

import java.util.Map;

/**
 * @author <a href="mailto:sgonzalez@atricore.org">Sebastian Gonzalez Oyuela</a>
 * @version $Id$
 */
public class SpnegoHttpInitiatorBinding extends AbstractMediationHttpBinding {

    private static final Log logger = LogFactory.getLog(SpnegoHttpInitiatorBinding.class);

    protected SpnegoHttpInitiatorBinding(Channel channel) {
        super(SpnegoBinding.SPNEGO_HTTP_INITIATOR.getValue(), channel);
    }

    public MediationMessage createMessage(CamelMediationMessage message) {
        SpnegoMessage sm = null;

        Exchange exchange = message.getExchange().getExchange();
        logger.debug("Create Message Body from exchange " + exchange.getClass().getName());

        Message httpMsg = exchange.getIn();

        if (httpMsg.getHeader("http.requestMethod") == null) {

            if (logger.isDebugEnabled()) {
                Map<String, Object> h = httpMsg.getHeaders();
                for (String key : h.keySet()) {
                    logger.debug("CAMEL Header:" + key + ":"+ h.get(key));
                }
            }

            throw new IllegalArgumentException("Unknown message, no valid HTTP Method header found!");
        }

        sm = new InitiateSpnegoNegotiation();

        // HTTP Request Parameters from HTTP Request body
        MediationState state = createMediationState(exchange);

        return new MediationMessageImpl(message.getMessageId(),
                        sm,
                        null,
                        null,
                        null,
                        state);
    }

    public void copyMessageToExchange(CamelMediationMessage spnegoOut, Exchange exchange) {
        MediationMessage<SpnegoMessage> out = spnegoOut.getMessage();
        EndpointDescriptor ed = out.getDestination();

        Message httpOut = exchange.getOut();
        Message httpIn = exchange.getIn();

        SpnegoMessage sm = out.getContent();

        copyBackState(out.getState(), exchange);

        if (sm instanceof InitiateSpnegoNegotiation) {
            InitiateSpnegoNegotiation isn = (InitiateSpnegoNegotiation) sm;
            logger.debug("Initiating Spnego Negotiation on " + isn.getTargetSpnegoEndpoint());

            httpOut.getHeaders().put("Cache-Control", "no-cache, no-store");
            httpOut.getHeaders().put("Pragma", "no-cache");
            httpOut.getHeaders().put("http.responseCode", 302);
            httpOut.getHeaders().put("Content-Type", "text/html");
            httpOut.getHeaders().put("Location", isn.getTargetSpnegoEndpoint());
        } else
        if (sm instanceof RequestToken) {
            logger.debug("Requesting GSSAPI token to SPNEGO/HTTP initiator");
            httpOut.getHeaders().put(SpnegoHeader.AUTHN.getValue(), SpnegoHeader.NEGOTIATE.getValue());
            httpOut.getHeaders().put("http.responseCode", SpnegoStatus.UNAUTHORIZED.getValue());
        }

    }
}
