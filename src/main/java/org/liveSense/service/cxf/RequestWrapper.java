//$URL: http://feanor:8050/svn/test/trunk/DevTest/apache-sling/adaptto/sling-cxf-integration/helloworld-application/src/main/java/adaptto/slingcxf/server/util/RequestWrapper.java $
//$Id: RequestWrapper.java 677 2011-09-09 15:26:10Z PRO-VISION\SSeifert $
package org.liveSense.service.cxf;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.impl.UriBuilderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Request wrapper that maps all pathinfo to a virtual path, to whom the SOAP
 * services are registered to.
 */
class RequestWrapper extends HttpServletRequestWrapper {

	Logger log = LoggerFactory.getLogger(HttpServletRequestWrapper.class);
	public static final String VIRTUAL_PATH = "/soaprequest";

	String servletUrl = null;

	public RequestWrapper(HttpServletRequest pRequest, String servletUrl) {
		super(pRequest);
		this.servletUrl = servletUrl;

	}

	public String getServletUrl() {
		return servletUrl;
	}
	
	
	@Override
	public String getRequestURI() {
		return getPathInfo();
	}

	@Override
	public String getPathInfo() {
		if (servletUrl != null) {
			String requestPath = ((HttpServletRequest)getRequest()).getPathInfo();
			int index = StringUtils.indexOf(requestPath, servletUrl);
			if (index == -1) {
				return servletUrl;
			} else 
			try {
				URI uri = new URI(requestPath);
				
				if (StringUtils.contains(uri.getPath(), servletUrl)) {
					String urlPrefix = StringUtils.substring(uri.getPath(), 0, StringUtils.indexOf(uri.getPath(), servletUrl));
					String urlPostfix = StringUtils.substring(uri.getPath(), StringUtils.indexOf(uri.getPath(), servletUrl)+servletUrl.length());
	    			String selector = null;
	    			if (urlPostfix.startsWith(".")) {
	    				if (StringUtils.contains(urlPostfix, "/")) {
	    					selector = StringUtils.substring(urlPostfix, StringUtils.indexOf(urlPostfix, ".")+1, StringUtils.indexOf(urlPostfix, "/"));
	    					urlPostfix = "/" + StringUtils.substringAfter(urlPostfix, "/");
	    				} else {
	    					selector = urlPostfix.substring(1);
	    					urlPostfix = "";
	    				}
	    			}
	    			UriBuilderImpl uriBuilder = new UriBuilderImpl(uri);
	    			if (StringUtils.isNotEmpty(selector)) {
	    				uriBuilder.replacePath(urlPrefix+servletUrl+urlPostfix+"."+selector);
	    			} else {
		    			uriBuilder.replacePath(urlPrefix+servletUrl+urlPostfix);	    				
	    			}
	    			return uriBuilder.build((Object)null).toString();
				} else {
					log.warn("URI ("+uri.toString()+") does not contain: "+requestPath);
					return servletUrl;
				}
			} catch (URISyntaxException e) {
				log.error("URI creation problem: "+requestPath, e);
				return VIRTUAL_PATH;
			}
		} else {
			return VIRTUAL_PATH;
		}
	}
/*
	public String getTranslatedPath(String requestPath) throws URISyntaxException {
		URI uri = new URI(requestPath);
		System.out.println("Path: "+uri.getPath()+" Query: "+uri.getQuery()+" Fragment: "+uri.getFragment());
		return uri.toString();
	}
	*/
}
