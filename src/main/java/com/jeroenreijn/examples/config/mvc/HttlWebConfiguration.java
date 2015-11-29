package com.jeroenreijn.examples.config.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jeroenreijn.examples.config.TemplatingConfig;
import com.jeroenreijn.examples.config.TemplatingProfiles;

import httl.web.springmvc.HttlViewResolver;

/**
 * @author : patouche - 26/11/15.
 */
@Configuration
@Profile(TemplatingProfiles.HTTL)
public class HttlWebConfiguration {

    public static final TemplatingConfig CONFIG = TemplatingProfiles.get(TemplatingProfiles.HTTL);

    @Bean
    protected HttlViewResolver httlViewResolver() {
        final HttlViewResolver viewResolver = new HttlViewResolver();
        viewResolver.setContentType("text/html; charset=UTF-8");
        viewResolver.setPrefix("/WEB-INF/httl/");
        viewResolver.setSuffix(".httl");
        viewResolver.setOrder(CONFIG.getOrder());
        return viewResolver;
    }

}
