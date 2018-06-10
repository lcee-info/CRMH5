package com.sail.simonli.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sail.simonli.server.interceptor.LogHandlerInterceptor;
import com.sail.simonli.server.interceptor.SessionHandlerInterceptor;
import com.sail.simonli.server.interceptor.UserInterceptor;


@Configuration
public class MvcConfigurerAdapter extends WebMvcConfigurerAdapter{

	 /**
     * 注册 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	
    	registry.addInterceptor(new UserInterceptor());
    	
        //registry.addInterceptor(new LogHandlerInterceptor());
        
        registry.addInterceptor(new SessionHandlerInterceptor());
        
    }
}
