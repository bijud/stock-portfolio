
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * This class bootstraps our web application on Tomcat loading the web
 * application.
 * 
 * @author Biju
 *
 */
public class Webapp implements WebApplicationInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet
	 * .ServletContext)
	 */
	@Override
	public void onStartup(ServletContext container) throws ServletException {
		WebApplicationContext context = getContext();
		container.addListener(new ContextLoaderListener(context));
		ServletRegistration.Dynamic registration = container.addServlet("trading", new DispatcherServlet(context));
		registration.setLoadOnStartup(1);
		registration.addMapping("/");
	}

	/**
	 * This starts the web application context, telling Spring to look in
	 * org.tradingapp for annotations.
	 * 
	 * @return - {@link AnnotationConfigWebApplicationContext} object that
	 *         contains all of the annotated objects.
	 */
	private AnnotationConfigWebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation("org.tradingapp");
		return context;
	}
}
