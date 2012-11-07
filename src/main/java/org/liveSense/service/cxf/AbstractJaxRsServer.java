//$URL: http://feanor:8050/svn/test/trunk/DevTest/apache-sling/adaptto/sling-cxf-integration/helloworld-application/src/main/java/adaptto/slingcxf/server/util/AbstractJaxWsServer.java $
//$Id: AbstractJaxWsServer.java 680 2011-09-12 16:57:25Z PRO-VISION\SSeifert $
package org.liveSense.service.cxf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.cxf.BusFactory;
import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.jaxrs.provider.JAXBElementProvider;
import org.apache.felix.scr.annotations.Component;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract servlet-based implementation for CXF-based SOAP services. Ensures
 * that correct class loader is used is during initialization and invoking
 * phases. Via getCurrentRequest() and getCurrentResponse() it is possible to
 * access these objects from SOAP method implementations.
 */
@Component(componentAbstract=true)
public abstract class AbstractJaxRsServer extends AbstractWsServer {
	private static final long serialVersionUID = 1L;

	public static Logger log = LoggerFactory.getLogger(AbstractWsServer.class);
	
	
	@Override
	public void init(ServletConfig pServletConfig) throws ServletException {
		ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
		try {
			
			Thread.currentThread().setContextClassLoader(BusFactory.class.getClassLoader());

			super.init(pServletConfig);
			JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
			sf.setBus(getBus());
			
			sf.setAddress(getServletUrl() == null ? RequestWrapper.VIRTUAL_PATH : getServletUrl());
			
			Map<Object, Object> extensionMappings = new HashMap<Object, Object>();
			extensionMappings.put("xml", "application/xml");
			extensionMappings.put("json", "application/json");
			extensionMappings.put("html", "text/html");
			sf.setExtensionMappings(extensionMappings);

			List<Object> providers = new ArrayList<Object>();
			providers.add(new JAXBElementProvider());
			providers.add(new JacksonJaxbJsonProvider());
			sf.setProviders(providers);
			
			BindingFactoryManager manager = sf.getBus().getExtension(BindingFactoryManager.class);
			JAXRSBindingFactory factory = new JAXRSBindingFactory();
			factory.setBus(sf.getBus());
			manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, factory);
			
			sf.setResourceClasses(getServerInterfaceType());
			//sf.setBindingId(JAXRSBindingFactory.JAXRS_BINDING_ID);
			sf.setResourceProvider(getServerInterfaceType(), new SingletonResourceProvider(this));
			sf.create();
		} finally {
			Thread.currentThread().setContextClassLoader(oldClassLoader);
		}
	}

	@Override
	public void destroy() {
		ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
		Thread.currentThread().setContextClassLoader(BusFactory.class.getClassLoader());
		try {
			super.destroy();
		} finally {
			Thread.currentThread().setContextClassLoader(oldClassLoader);
		}
	}
}
