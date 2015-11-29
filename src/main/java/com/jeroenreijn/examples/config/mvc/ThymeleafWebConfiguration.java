package com.jeroenreijn.examples.config.mvc;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.jeroenreijn.examples.config.TemplatingConfig;
import com.jeroenreijn.examples.config.TemplatingProfiles;

/**
 * @author : patouche - 29/11/15.
 */
@Configuration
@Profile(TemplatingProfiles.THYMELEAF)
public class ThymeleafWebConfiguration {

    public static final TemplatingConfig CONFIG = TemplatingProfiles.get(TemplatingProfiles.THYMELEAF);

    @Bean
    public ServletContextTemplateResolver thymeleafTemplateResolver() {
        final ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/thymeleaf/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheable(true);
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(this.thymeleafTemplateResolver());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        final ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setViewNames(new String[] { "*-thymeleaf" });
        viewResolver.setTemplateEngine(this.templateEngine());
        viewResolver.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        viewResolver.setCache(true);
        viewResolver.setOrder(CONFIG.getOrder());
        return viewResolver;
    }

}
