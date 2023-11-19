package mvc.context;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

public interface ConfigurableWebApplicationContext extends WebApplicationContext{

  void setServletContext(ServletContext servletContext);

  void setServletConfig(ServletConfig servletConfig);

  ServletContext getServletContext();


  ServletConfig getServletConfig();
}
