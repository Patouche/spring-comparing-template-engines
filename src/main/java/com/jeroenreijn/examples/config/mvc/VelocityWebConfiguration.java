package com.jeroenreijn.examples.config.mvc;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import com.jeroenreijn.examples.config.TemplatingConfig;
import com.jeroenreijn.examples.config.TemplatingProfiles;

/**
 * @author : patouche - 26/11/15.
 */
@Configuration
@Profile(TemplatingProfiles.VELOCITY)
public class VelocityWebConfiguration {

    public static final TemplatingConfig CONFIG = TemplatingProfiles.get(TemplatingProfiles.VELOCITY);

    @Bean
    public VelocityViewResolver velocityViewResolver() {
        final VelocityViewResolver viewResolver = new VelocityViewResolver();
        viewResolver.setOrder(CONFIG.getOrder());
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".vm");
        viewResolver.setViewNames("*-velocity");
        viewResolver.setToolboxConfigLocation("/WEB-INF/velocity/toolbox.xml");
        viewResolver.setExposeSpringMacroHelpers(true);
        return viewResolver;
    }

    @Bean
    public VelocityConfigurer velocityConfig() {
        final Properties properties = new Properties();
        properties.put("velocimacro.library", "includes.vm");
        final VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
        velocityConfigurer.setResourceLoaderPath("/WEB-INF/velocity/");
        velocityConfigurer.setVelocityProperties(properties);
        return velocityConfigurer;
    }
}
