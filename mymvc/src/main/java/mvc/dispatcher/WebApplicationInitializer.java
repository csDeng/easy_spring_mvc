package mvc.dispatcher;

import javax.servlet.ServletContext;

public interface WebApplicationInitializer {

    void onStartUp(ServletContext servletContext);
}
