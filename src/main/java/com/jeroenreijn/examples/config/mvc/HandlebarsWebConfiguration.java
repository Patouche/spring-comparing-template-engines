package com.jeroenreijn.examples.config.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import com.jeroenreijn.examples.config.TemplatingConfig;
import com.jeroenreijn.examples.config.TemplatingProfiles;

/**
 * @author : patouche - 26/11/15.
 */
@Configuration
@Profile(TemplatingProfiles.HANDLEBARS)
public class HandlebarsWebConfiguration {

    public static final TemplatingConfig CONFIG = TemplatingProfiles.get(TemplatingProfiles.HANDLEBARS);

    @Bean
    public HandlebarsViewResolver handlebarsViewResolver() {
        final HandlebarsViewResolver viewResolver = new HandlebarsViewResolver();
        viewResolver.setPrefix("/WEB-INF/handlebars/");
        viewResolver.setSuffix(".hbs");
        viewResolver.setViewNames("*-handlebars");
        viewResolver.setOrder(CONFIG.getOrder());
        return viewResolver;
    }

}
