package org.atricore.idbus.kernel.main.mediation.camel.component.http;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.protocol.RequestAddCookies;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.HttpContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author <a href=mailto:sgonzalez@atricore.org>Sebastian Gonzalez Oyuela</a>
 */
public class IDBusRequestAddCookies extends RequestAddCookies {

    private String cookieDomain;

    private static final Log logger = LogFactory.getLog(IDBusRequestAddCookies.class);

    public IDBusRequestAddCookies(String cookieDomain) {
        this.cookieDomain = cookieDomain;
    }

    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {

        HttpServletRequest originalRequest = (HttpServletRequest) context.getAttribute("org.atricorel.idbus.kernel.main.binding.http.HttpServletRequest");

        if (originalRequest != null) {
            // TODO : Sevlet cookies to cookie store

            // Obtain cookie store
            CookieStore cookieStore = (CookieStore) context.getAttribute(
                    ClientContext.COOKIE_STORE);
            if (cookieStore == null) {
                logger.error("Cookie store not specified in HTTP context");
                return;
            }

            if (originalRequest.getCookies() != null) {
                for (javax.servlet.http.Cookie svltCookie : originalRequest.getCookies()) {
                    Cookie clientCookie = toClientCookie(context, svltCookie);
                    cookieStore.addCookie(clientCookie);
                }
            }

        }

        super.process(request, context);

    }

    protected Cookie toClientCookie(HttpContext context, javax.servlet.http.Cookie svltCookie) {

        BasicClientCookie cookie = new BasicClientCookie(svltCookie.getName(), svltCookie.getValue());

        cookie.setDomain(svltCookie.getDomain() != null ? svltCookie.getDomain() : cookieDomain);
        // Path is not that important since we're already on the server and the cookie was received.
        cookie.setPath(svltCookie.getPath() != null ? svltCookie.getPath() : "/");
        // TODO : FOR NOW WE ONLY SUPPORT SESSION COOKIES cookie.setExpiryDate();
        cookie.setVersion(svltCookie.getVersion());
        cookie.setSecure(svltCookie.getSecure());
        cookie.setComment(svltCookie.getComment());

        return cookie;
    }
}
