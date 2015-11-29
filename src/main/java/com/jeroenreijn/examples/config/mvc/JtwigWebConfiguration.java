package com.jeroenreijn.examples.config.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jeroenreijn.examples.config.TemplatingConfig;
import com.jeroenreijn.examples.config.TemplatingProfiles;
import com.lyncode.jtwig.mvc.JtwigViewResolver;

/**
 * @author : patouche - 26/11/15.
 */
@Configuration
@Profile(TemplatingProfiles.JTWIG)
public class JtwigWebConfiguration {

    public static final TemplatingConfig CONFIG = TemplatingProfiles.get(TemplatingProfiles.JTWIG);

    @Bean
    public JtwigViewResolver jtwigViewResolver() {
        final JtwigViewResolver viewResolver = new JtwigViewResolver();
        viewResolver.setPrefix("/WEB-INF/jtwig/");
        viewResolver.setSuffix(".twig");
        viewResolver.setViewNames("*-jtwig");
        viewResolver.setOrder(CONFIG.getOrder());
        viewResolver.setCached(true);
        return viewResolver;
    }
}
