package com.project2.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// creates beans for DataSource , SessionFactory , HibernateTransactionManager
		return new Class[] {DBConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		//enable webmvc , handler mapping , scan all the components in the base package
		return new Class[] {WebAppConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		//forward all the incoming requests to DispatcherServlet by specifying the url pattern as '/'
		return new String[] {"/"};
		//this is same like url-pattern in servlet dispatcher
	}

}
