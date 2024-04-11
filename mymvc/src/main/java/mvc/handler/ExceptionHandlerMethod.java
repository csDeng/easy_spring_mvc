package mvc.handler;

import java.lang.reflect.Method;

public class ExceptionHandlerMethod extends HandlerMethod{
    public ExceptionHandlerMethod(Object bean, Method method) {
        super(bean, method);
    }
}
