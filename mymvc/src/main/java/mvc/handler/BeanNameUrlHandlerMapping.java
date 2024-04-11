package mvc.handler;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 不提供实现，只说明有这个场景
 * @Author: Xhy
 * @gitee: https://gitee.com/XhyQAQ
 * @copyright: B站: https://space.bilibili.com/152686439
 * @CreateTime: 2023-10-14 17:48
 */
public class BeanNameUrlHandlerMapping extends AbstractHandlerMapping{
    @Override
    protected HandlerMethod getHandlerInternal(HttpServletRequest request) {
        return null;
    }

    @Override
    protected void detectHandlerMethod(String name) throws Exception {

    }

    @Override
    protected boolean isHandler(Class type) {
        return false;
    }

    @Override
    protected void setOrder(int order) {
        this.order = 2;
    }
}
