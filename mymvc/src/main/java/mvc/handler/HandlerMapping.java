package mvc.handler;

import org.springframework.core.Ordered;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 获取映射器

 */
public interface HandlerMapping extends Ordered {

    HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception;
}
