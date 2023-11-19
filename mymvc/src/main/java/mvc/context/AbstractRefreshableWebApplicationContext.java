package mvc.context;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.AbstractRefreshableConfigApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

public abstract class AbstractRefreshableWebApplicationContext
    extends AbstractRefreshableConfigApplicationContext
    implements ConfigurableWebApplicationContext {


  protected ServletContext servletContext;

  protected ServletConfig servletConfig;

  @Override
  public void setServletConfig(ServletConfig servletConfig) {
    this.servletConfig = servletConfig;
  }

  @Override
  public void setServletContext(ServletContext servletContext) {
    this.servletContext = servletContext;
  }

  @Override
  public ServletConfig getServletConfig() {
    return this.servletConfig;
  }

  @Override
  public ServletContext getServletContext() {
    return this.servletContext;
  }

  /**
   * Bean 后置处理器
   * @param beanFactory the bean factory used by the application context
   */
  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
    beanFactory.addBeanPostProcessor(new ServletBeanPostProcess(this.servletContext, this.servletConfig));


    beanFactory.ignoreDependencyInterface(ServletContextAware.class);
    beanFactory.ignoreDependencyInterface(ServletConfigAware.class);
  }

}
