package mvc.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

public class ServletBeanPostProcess implements BeanPostProcessor {

  private ServletContext servletContext;

  private ServletConfig servletConfig;

  public ServletBeanPostProcess(ServletContext servletContext, ServletConfig servletConfig) {
    this.servletContext = servletContext;
    this.servletConfig = servletConfig;
  }

  /**
   * 用户想拿到ServletContext/ServletConfig 需要自行实现XXAware,通过该接口获取属性
   * @param bean
   * @param beanName
   * @return
   * @throws BeansException
   */
  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {


    if (bean instanceof ServletConfigAware){
      ((ServletConfigAware)bean).setServletConfig(this.servletConfig);
    }

    if (bean instanceof  ServletContextAware){
      ((ServletContextAware)bean).setServletContext(this.servletContext);
    }
    return bean;
  }
}
