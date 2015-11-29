package com.jeroenreijn.examples.config.mvc;

import org.fusesource.scalate.spring.view.ScalateViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jeroenreijn.examples.config.TemplatingConfig;
import com.jeroenreijn.examples.config.TemplatingProfiles;

/**
 * @author : patouche - 26/11/15.
 */
@Configuration
@Profile(TemplatingProfiles.SCALATE)
public class ScalateWebConfiguration {

    public static final TemplatingConfig CONFIG = TemplatingProfiles.get(TemplatingProfiles.SCALATE);

    @Bean
    public ScalateViewResolver ScalateViewResolver() {
        final ScalateViewResolver viewResolver = new ScalateViewResolver();
        viewResolver.setPrefix("/WEB-INF/scalate/");
        viewResolver.setSuffix(".scaml");
        viewResolver.setOrder(CONFIG.getOrder());
        return viewResolver;
    }

}
