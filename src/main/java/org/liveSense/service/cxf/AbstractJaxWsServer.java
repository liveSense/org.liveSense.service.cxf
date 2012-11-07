//$URL: http://feanor:8050/svn/test/trunk/DevTest/apache-sling/adaptto/sling-cxf-integration/helloworld-application/src/main/java/adaptto/slingcxf/server/util/AbstractJaxWsServer.java $
//$Id: AbstractJaxWsServer.java 680 2011-09-12 16:57:25Z PRO-VISION\SSeifert $
package org.liveSense.service.cxf;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.cxf.BusFactory;
import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.felix.scr.annotations.Component;

/**
 * Abstract servlet-based implementation for CXF-based SOAP services. Ensures
 * that correct class loader is used is during initialization and invoking
 * phases. Via getCurrentRequest() and getCurrentResponse() it is possible to
 * access these objects from SOAP method implementations.
 */
@Component(componentAbstract=true)
public abstract class AbstractJaxWsServer extends AbstractWsServer {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig pServletConfig) throws ServletException {
		ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
		try {
			// set classloader to CXF bundle class loader to avoid OSGI classloader problems 
			Thread.currentThread().setContextClassLoader(BusFactory.class.getClassLoader());

			super.init(pServletConfig);

			// register SOAP service
			ServerFactoryBean sf = new JaxWsServerFactoryBean();
			sf.setBus(getBus());
			sf.setAddress(getServletUrl() == null ? RequestWrapper.VIRTUAL_PATH : getServletUrl());
			sf.setServiceClass(getServerInterfaceType());
			sf.getServiceFactory().setDataBinding(new JAXBDataBinding());
			sf.setServiceBean(this);
			sf.create();

		} finally {
			Thread.currentThread().setContextClassLoader(oldClassLoader);
		}
	}
	
	@Override
	public void destroy() {
		ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
		try {
			super.destroy();
		} finally {
			Thread.currentThread().setContextClassLoader(oldClassLoader);
		}
	}

}
