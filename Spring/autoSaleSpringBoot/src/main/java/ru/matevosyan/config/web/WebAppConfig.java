package ru.matevosyan.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


/**
 * Configuration app views.
 */

@Configuration
//@EnableSpringDataWebSupport
public class WebAppConfig implements WebMvcConfigurer {

    /**
     * add the url and responsible view to which dispatcher servlet redirect if URL is mapping.
     * @param registry ViewControllerRegistry.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/signIn").setViewName("signIn");
        registry.addViewController("/ROLE_ADMIN/**").setViewName("admin");
        registry.addViewController("/ROLE_USER/**").setViewName("user");
        registry.addViewController("/anonymous/**").setViewName("anonymous");
    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
//        messageConverters.add(new MappingJackson2HttpMessageConverter());
//        WebMvcConfigurer.super.configureMessageConverters(messageConverters);
//    }

}
