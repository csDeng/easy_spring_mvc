package mvc.dispatcher;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class DispatcherServlet extends BaseHttpServlet {

//    private List<HandlerMapping> handlerMappings = new ArrayList<>();
//
//    private List<HandlerMethodAdapter> handlerMethodAdapters = new ArrayList<>();
//
//    private List<HandlerExceptionResolver> handlerExceptionResolvers = new ArrayList<>();

    private Properties defaultStrategies;

    public static final String DEFAULT_STRATEGIES_PATH = "DispatcherServlet.properties";

    public DispatcherServlet(ApplicationContext webApplicationContext) {
        super(webApplicationContext);
    }

    // 组件初始化
    @Override
    protected void onRefresh(ApplicationContext webApplicationContext) {
//        initHandlerMapping(webApplicationContext);
//        initHandlerMethodAdapter(webApplicationContext);
//        initHandlerException(webApplicationContext);

    }

    // 初始化异常解析器
//    private void initHandlerException(ApplicationContext webApplicationContext) {
//        // 从容器中拿
//        final Map<String, HandlerExceptionResolver> map = BeanFactoryUtils.beansOfTypeIncludingAncestors(webApplicationContext, HandlerExceptionResolver.class, true, false);
//        if (!ObjectUtils.isEmpty(map)){
//            this.handlerExceptionResolvers = new ArrayList<>(map.values());
//        }else {
//            // 则从默认配置文件中拿
//            this.handlerExceptionResolvers.addAll(getDefaultStrategies(webApplicationContext,HandlerExceptionResolver.class));
//        }
//        this.handlerExceptionResolvers.sort(Comparator.comparingInt(Ordered::getOrder));
//    }

    // 初始化适配器
//    private void initHandlerMethodAdapter(ApplicationContext webApplicationContext) {
//        // 从容器中拿
//        final Map<String, HandlerMethodAdapter> map = BeanFactoryUtils.beansOfTypeIncludingAncestors(webApplicationContext, HandlerMethodAdapter.class, true, false);
//        if (!ObjectUtils.isEmpty(map)){
//            this.handlerMethodAdapters = new ArrayList<>(map.values());
//        }else {
//            // 则从默认配置文件中拿
//            this.handlerMethodAdapters.addAll(getDefaultStrategies(webApplicationContext,HandlerMethodAdapter.class));
//        }
//        this.handlerMethodAdapters.sort(Comparator.comparingInt(Ordered::getOrder));
//
//    }

//    private void initHandlerMapping(ApplicationContext webApplicationContext) {
//
//        // 从容器中拿
//        final Map<String, HandlerMapping> map = BeanFactoryUtils.beansOfTypeIncludingAncestors(webApplicationContext, HandlerMapping.class, true, false);
//        if (!ObjectUtils.isEmpty(map)){
//            this.handlerMappings = new ArrayList<>(map.values());
//        }else {
//            // 则从默认配置文件中拿
//            this.handlerMappings.addAll(getDefaultStrategies(webApplicationContext,HandlerMapping.class));
//        }
//        this.handlerMappings.sort(Comparator.comparingInt(Ordered::getOrder));
//    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(">>>>>>>>>>> 请求分发");
//        Exception ex = null;
//        HandlerExecutionChain handlerExecutionChain = null;
//        try {
//            handlerExecutionChain = getHandler(req);
//            if (ObjectUtils.isEmpty(handlerExecutionChain)){
//                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
//                return;
//            }
//            // todo 拦截器
//
//            // 获得适配器
//            final HandlerMethodAdapter ha = getHandlerMethodAdapter(handlerExecutionChain.getHandlerMethod());
//            if (!handlerExecutionChain.applyPreInterceptor(req,resp)) {
//                return;
//            }
//            ha.handler(req,resp,handlerExecutionChain.getHandlerMethod());
//            handlerExecutionChain.applyPostInterceptor(req,resp);
//        } catch (InvocationTargetException ite){
//            ex = (Exception) ite.getCause();
//        } catch (Exception e) {
//            ex = e;
//        }
//        try {
//            processResult(req,resp,handlerExecutionChain,ex);
//        } catch (Exception e) {
//            throw new ServletException(e.getMessage());
//        }
    }

//    private void processResult(HttpServletRequest req, HttpServletResponse resp, HandlerExecutionChain handlerExecutionChain, Exception ex) throws Exception {
//
//        if (ex!=null){
//            HandlerMethod handlerMethod = handlerExecutionChain == null ? null : handlerExecutionChain.getHandlerMethod();
//            processResultException(req,resp,handlerMethod,ex);
//        }
//
//        handlerExecutionChain.afterCompletion(req,resp,handlerExecutionChain.getHandlerMethod(),ex);
//    }

//    private void processResultException(HttpServletRequest req, HttpServletResponse resp, HandlerMethod handlerMethod, Exception ex) throws Exception {
//
//        for (HandlerExceptionResolver handlerExceptionResolver : this.handlerExceptionResolvers) {
//            if (handlerExceptionResolver.resolveException(req, resp, handlerMethod, ex)) {
//                return;
//            }
//        }
//        throw new ServletException(ex.getMessage());
//    }

//    private HandlerMethodAdapter getHandlerMethodAdapter(HandlerMethod handlerMethod) throws Exception {
//        for (HandlerMethodAdapter handlerMethodAdapter : this.handlerMethodAdapters) {
//            if (handlerMethodAdapter.support(handlerMethod)) {
//                return handlerMethodAdapter;
//            }
//        }
//        throw new NotFoundException(handlerMethod + "没有支持的适配器");
//    }
//
//    // 获取映射器
//    private HandlerExecutionChain getHandler(HttpServletRequest req) throws Exception {
//        // 拿到所有组件进行遍历
//        for (HandlerMapping handlerMapping : handlerMappings) {
//            final HandlerExecutionChain handler = handlerMapping.getHandler(req);
//            if (handler!=null){
//                return handler;
//            }
//        }
//        return null;
//    }
//

    protected <T> List<T> getDefaultStrategies(ApplicationContext context, Class<T> strategyInterface) {
        if (defaultStrategies == null) {
            try {
                ClassPathResource resource = new ClassPathResource(DEFAULT_STRATEGIES_PATH, DispatcherServlet.class);
                defaultStrategies = PropertiesLoaderUtils.loadProperties(resource);
            } catch (IOException ex) {
                throw new IllegalStateException("Could not load '" + DEFAULT_STRATEGIES_PATH + "': " + ex.getMessage());
            }
        }

        String key = strategyInterface.getName();
        String value = defaultStrategies.getProperty(key);
        if (value != null) {
            String[] classNames = StringUtils.commaDelimitedListToStringArray(value);
            List<T> strategies = new ArrayList<>(classNames.length);
            for (String className : classNames) {
                try {
                    Class<?> clazz = ClassUtils.forName(className, DispatcherServlet.class.getClassLoader());
                    Object strategy = createDefaultStrategy(context, clazz);
                    strategies.add((T) strategy);
                } catch (ClassNotFoundException ex) {
                    throw new BeanInitializationException(
                        "Could not find DispatcherServlet's default strategy class [" + className +
                            "] for interface [" + key + "]", ex);
                } catch (LinkageError err) {
                    throw new BeanInitializationException(
                        "Unresolvable class definition for DispatcherServlet's default strategy class [" +
                            className + "] for interface [" + key + "]", err);
                }
            }
            return strategies;
        } else {
            return Collections.emptyList();
        }
    }

    protected Object createDefaultStrategy(ApplicationContext context, Class<?> clazz) {
        return context.getAutowireCapableBeanFactory().createBean(clazz);
    }

}
