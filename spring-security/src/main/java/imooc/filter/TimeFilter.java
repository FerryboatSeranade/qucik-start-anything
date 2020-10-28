package imooc.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * TimeFilter
 *
 * @title: TimeFilter
 * @Author shu.shen
 * @Date: 2020/10/24 0:58
 * @Version 1.0
 */
@Slf4j
//@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init....");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("doFilter start....");
        long start = System.currentTimeMillis();
        chain.doFilter(request,response);
        log.info("time Filter耗时: {} ms", System.currentTimeMillis()-start);
        log.info("doFilter end....");
    }

    @Override
    public void destroy() {
        log.info("destroy....");
    }
}
