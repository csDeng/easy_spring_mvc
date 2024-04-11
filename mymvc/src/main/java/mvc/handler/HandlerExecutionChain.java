package mvc.handler;

import org.xhy.web.intercpetor.HandlerInterceptor;
import org.xhy.web.intercpetor.MappedInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class HandlerExecutionChain {

    private final HandlerMethod handlerMethod;

    private List<HandlerInterceptor> interceptors = new ArrayList<>();

    public HandlerExecutionChain(HandlerMethod handlerMethod){
        this.handlerMethod = handlerMethod;
    }

    public HandlerMethod getHandlerMethod() {
        return handlerMethod;
    }

    public void setInterceptors(List<HandlerInterceptor> interceptors) {
        // 路径映射匹配
        for (HandlerInterceptor interceptor : interceptors) {
            if (interceptor instanceof MappedInterceptor){
                if (((MappedInterceptor)interceptor).match(handlerMethod.getPath())) {
                    this.interceptors.add(interceptor);
                }
            }else {
                this.interceptors.add(interceptor);
            }

        }

    }
    // 多个拦截器执行，一旦有一个拦截器返回false，整个链路可以崩掉
    public boolean applyPreInterceptor(HttpServletRequest req, HttpServletResponse resp) {
        for (HandlerInterceptor interceptor : this.interceptors) {
            if (!interceptor.preHandle(req,resp)) {
                return false;
            }
        }
        return true;
    }

    public void applyPostInterceptor(HttpServletRequest req, HttpServletResponse resp) {

        for (HandlerInterceptor interceptor : this.interceptors) {
            interceptor.postHandle(req,resp);
        }
    }

    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, HandlerMethod handlerMethod, Exception ex) {
        for (HandlerInterceptor interceptor : this.interceptors) {
            interceptor.afterCompletion(req,resp,handlerMethod,ex);
        }
    }
}
