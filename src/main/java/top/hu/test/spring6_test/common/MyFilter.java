package top.hu.test.spring6_test.common;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
//@Configuration
public class MyFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        log.warn("{},init请求创建");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("-----您已经进入了过滤器-----------");
//        if (servletRequest.getParameter("name").isEmpty()){
//            log.error("name不存在");
//        }
        servletRequest.setCharacterEncoding("utf-8");
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        log.error("{},拦截器销毁");
        Filter.super.destroy();
    }
}
