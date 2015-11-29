package com.jeroenreijn.examples.config.mvc;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.jeroenreijn.examples.config.TemplatingConfig;
import com.jeroenreijn.examples.config.TemplatingProfiles;

/**
 * @author : patouche - 26/11/15.
 */
@Configuration
@Profile(TemplatingProfiles.FREEMARKER)
public class FreeMarkerWebConfiguration {

    public static final TemplatingConfig CONFIG = TemplatingProfiles.get(TemplatingProfiles.FREEMARKER);

    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        final FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setOrder(CONFIG.getOrder());
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".ftl");
        viewResolver.setViewNames("*-freemarker");
        return viewResolver;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        final Properties settings = new Properties();
        settings.put("auto_import", "spring.ftl as spring");

        final FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/freemarker/");
        freeMarkerConfigurer.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
        freeMarkerConfigurer.setFreemarkerSettings(settings);
        return freeMarkerConfigurer;
    }
}
