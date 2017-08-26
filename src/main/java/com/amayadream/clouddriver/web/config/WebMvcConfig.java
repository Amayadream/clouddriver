package com.amayadream.clouddriver.web.config;

import com.amayadream.clouddriver.web.interceptor.SignInterceptor;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;

/**
 *
 * @author : Amayadream
 * @date :   2017-08-16 18:28
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Resource
    private SignInterceptor signInterceptor;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("512MB"); // KB,MB
        factory.setMaxRequestSize("512MB");
        return factory.createMultipartConfig();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(signInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public/");
        registry.addResourceHandler("/public/**").addResourceLocations("classpath:/plugins/");
        super.addResourceHandlers(registry);
    }

}
