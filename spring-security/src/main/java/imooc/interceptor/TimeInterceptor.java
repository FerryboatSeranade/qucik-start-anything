package imooc.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TimeInterceptor
 *
 * @title: TimeInterceptor
 * @Author shu.shen
 * @Date: 2020/10/24 1:12
 * @Version 1.0
 */
@Slf4j
@Component
public class TimeInterceptor implements HandlerInterceptor {

    /**
     * 在controller.API之前调用
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle start...");
        request.setAttribute("startTime", System.currentTimeMillis());

        Class<?> handlerClass = null;
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if(handler instanceof HandlerMethod) {
            handlerClass = handlerMethod.getBean().getClass();
        }
        //print class name
        System.out.println("handlerClass.getName() = " + handlerClass.getName());
        //print api's name
        System.out.println("handlerMethod.getMethod().getName() = " + handlerMethod.getMethod().getName());

        log.info("preHandle end...");
        //控制对API的开关
        return true;
    }

    /**
     * 在controller.API之后调用：前置依赖：API要正常执行完,不可以抛出异常
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle start...");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("API方法耗时：" + (System.currentTimeMillis() - start)+"ms");
        log.info("postHandle end...");
    }

    /**
     * 在controller.API之后调用：无论该API正常执行或者抛出异常
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion start...");
        System.out.println("ex is"+ex);
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("API方法耗时：" + (System.currentTimeMillis() - start)+"ms");
        log.info("afterCompletion end...");
    }
}
