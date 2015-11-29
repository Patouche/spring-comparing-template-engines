package com.jeroenreijn.examples.config.mvc;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.MustacheMessageInterceptor;
import org.springframework.web.servlet.view.mustache.MustacheTemplateLoader;
import org.springframework.web.servlet.view.mustache.MustacheViewResolver;

import com.jeroenreijn.examples.config.TemplatingConfig;
import com.jeroenreijn.examples.config.TemplatingProfiles;

/**
 * @author : patouche - 26/11/15.
 */
@Configuration
@Profile(TemplatingProfiles.MUSTACHE)
public class MustacheWebConfiguration {

    public static final TemplatingConfig CONFIG = TemplatingProfiles.get(TemplatingProfiles.MUSTACHE);

    @Bean
    public MustacheTemplateLoader mustacheTemplateLoader() {
        return new MustacheTemplateLoader();
    }

    @Bean
    public MustacheViewResolver mustacheViewResolver() {
        final MustacheViewResolver viewResolver = new MustacheViewResolver();
        viewResolver.setOrder(CONFIG.getOrder());
        viewResolver.setPrefix("/WEB-INF/mustache/");
        viewResolver.setSuffix(".mustache");
        viewResolver.setViewNames("*-mustache");
        viewResolver.setTemplateLoader(this.mustacheTemplateLoader());
        return viewResolver;
    }

    @Bean
    public MustacheMessageInterceptor mustacheMessageInterceptor(final MessageSource mesageSource, final LocaleResolver localeResolver) {
        final MustacheMessageInterceptor interceptor = new MustacheMessageInterceptor();
        interceptor.setMessageSource(mesageSource);
        interceptor.setLocaleResolver(localeResolver);
        interceptor.setViewResolver(this.mustacheViewResolver());
        return interceptor;
    }

}
