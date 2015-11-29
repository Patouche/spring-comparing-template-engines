package com.jeroenreijn.examples.config;

import java.util.Locale;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.MustacheMessageInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.jeroenreijn.examples.config.mvc.FreeMarkerWebConfiguration;
import com.jeroenreijn.examples.config.mvc.HandlebarsWebConfiguration;
import com.jeroenreijn.examples.config.mvc.HttlWebConfiguration;
import com.jeroenreijn.examples.config.mvc.JadeWebConfiguration;
import com.jeroenreijn.examples.config.mvc.JspWebConfiguration;
import com.jeroenreijn.examples.config.mvc.JtwigWebConfiguration;
import com.jeroenreijn.examples.config.mvc.MustacheWebConfiguration;
import com.jeroenreijn.examples.config.mvc.PebbleWebConfiguration;
import com.jeroenreijn.examples.config.mvc.ScalateWebConfiguration;
import com.jeroenreijn.examples.config.mvc.ThymeleafWebConfiguration;
import com.jeroenreijn.examples.config.mvc.VelocityWebConfiguration;

/**
 * @author : patouche - 26/11/15.
 */
@Configuration
@EnableWebMvc
@Import(value = {
        FreeMarkerWebConfiguration.class,
        HandlebarsWebConfiguration.class,
        HttlWebConfiguration.class,
        JadeWebConfiguration.class,
        JspWebConfiguration.class,
        JtwigWebConfiguration.class,
        MustacheWebConfiguration.class,
        PebbleWebConfiguration.class,
        ScalateWebConfiguration.class,
        ThymeleafWebConfiguration.class,
        VelocityWebConfiguration.class
})
@PropertySource(value = "classpath:/messages.properties", name = "messages")
@ComponentScan("com.jeroenreijn.examples.controller")
public class WebConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebConfiguration.class);

    private ApplicationContext applicationContext;

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/favicon.ico").addResourceLocations("/favicon.ico");
        registry.addResourceHandler("/robots.txt").addResourceLocations("/robots.txt");
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        if (!applicationContext.getBeansOfType(MustacheMessageInterceptor.class).isEmpty()) {
            registry.addInterceptor(applicationContext.getBean(MustacheMessageInterceptor.class));
        }
    }

    @Bean
    public SessionLocaleResolver localeResolver() {
        final SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        return sessionLocaleResolver;
    }

    @Bean
    public MessageSource messageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    @Override
    public void configureViewResolvers(final ViewResolverRegistry registry) {
        applicationContext.getBeansOfType(ViewResolver.class).values()
                .stream()
                .peek(v -> LOGGER.info("Register view resolver : {}", v.getClass()))
                .forEach(registry::viewResolver);

        Stream.of(applicationContext.getEnvironment().getActiveProfiles())
                .filter(p -> TemplatingProfiles.ORDERS.keySet().contains(p))
                .forEach(p -> LOGGER.info("Profile {} enable ... URL : http://localhost:8080/{}", p, p));
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
