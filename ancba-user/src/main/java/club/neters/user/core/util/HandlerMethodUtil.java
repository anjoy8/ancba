package club.neters.user.core.util;

import club.neters.user.core.annotation.AllowAnonymous;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wuare
 * @date 2021/7/2
 */
public class HandlerMethodUtil {

    public static HandlerMethod getHandlerMethod(HttpServletRequest request) {
        DispatcherServlet dispatcherServlet = ApplicationContextUtil.getBean(DispatcherServlet.class);
        try {
            HandlerExecutionChain handlerExecutionChain = findHandler(dispatcherServlet, request);
            if (handlerExecutionChain == null) {
                return null;
            }
            Object handler = handlerExecutionChain.getHandler();
            if (!(handler instanceof HandlerMethod)) {
                return null;
            }
            return (HandlerMethod) handler;
        } catch (Exception ignored) {
            return null;
        }
    }

    private static HandlerExecutionChain findHandler(DispatcherServlet dispatcherServlet, HttpServletRequest request) throws Exception {
        if (dispatcherServlet.getHandlerMappings() != null) {
            for (HandlerMapping mapping : dispatcherServlet.getHandlerMappings()) {
                HandlerExecutionChain handler = mapping.getHandler(request);
                if (handler != null) {
                    return handler;
                }
            }
        }
        return null;
    }
}
