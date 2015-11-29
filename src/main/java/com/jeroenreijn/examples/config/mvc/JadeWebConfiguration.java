package com.jeroenreijn.examples.config.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jeroenreijn.examples.config.TemplatingConfig;
import com.jeroenreijn.examples.config.TemplatingProfiles;

import de.neuland.jade4j.JadeConfiguration;
import de.neuland.jade4j.spring.template.SpringTemplateLoader;
import de.neuland.jade4j.spring.view.JadeViewResolver;

/**
 * @author : patouche - 26/11/15.
 */
@Configuration
@Profile(TemplatingProfiles.JADE)
public class JadeWebConfiguration {

    public static final TemplatingConfig CONFIG = TemplatingProfiles.get(TemplatingProfiles.JADE);

    @Bean
    public JadeViewResolver jadeViewResolver() {
        final JadeViewResolver viewResolver = new JadeViewResolver();
        viewResolver.setPrefix("/");
        viewResolver.setSuffix(".jade");
        viewResolver.setViewNames("*-jade");
        viewResolver.setRenderExceptions(true);
        viewResolver.setConfiguration(this.jadeConfiguration());
        return viewResolver;
    }

    @Bean
    public JadeConfiguration jadeConfiguration() {
        final JadeConfiguration jadeConfiguration = new JadeConfiguration();
        jadeConfiguration.setPrettyPrint(false);
        jadeConfiguration.setTemplateLoader(this.jade4jTemplateLoader());
        return jadeConfiguration;
    }

    @Bean
    public SpringTemplateLoader jade4jTemplateLoader() {
        final SpringTemplateLoader springTemplateLoader = new SpringTemplateLoader();
        springTemplateLoader.setBasePath("/WEB-INF/jade/");
        return springTemplateLoader;
    }
}
