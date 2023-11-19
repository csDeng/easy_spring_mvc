package mvc.context;

import org.springframework.beans.factory.Aware;

import javax.servlet.ServletConfig;

public interface ServletConfigAware extends Aware {

  void setServletConfig(ServletConfig servletConfig);
}
