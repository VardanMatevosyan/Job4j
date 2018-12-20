package ru.matevosyan.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Configuration app views.
 */
@Configuration
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
}
