package com.jeroenreijn.examples.config.mvc;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jeroenreijn.examples.config.TemplatingConfig;
import com.jeroenreijn.examples.config.TemplatingProfiles;
import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.ServletLoader;
import com.mitchellbosecke.pebble.spring.PebbleViewResolver;

/**
 * @author : patouche - 26/11/15.
 */
@Configuration
@Profile(TemplatingProfiles.PEBBLE)
public class PebbleWebConfiguration {

    public static final TemplatingConfig CONFIG = TemplatingProfiles.get(TemplatingProfiles.PEBBLE);

    @Bean
    public ServletLoader pebbleTemplateLoader(final ServletContext servletContext) {
        return new ServletLoader(servletContext);
    }

    @Bean
    public PebbleEngine pebbleEngine(final ServletContext servletContext) {
        final PebbleEngine pebbleEngine = new PebbleEngine();
        pebbleEngine.setLoader(this.pebbleTemplateLoader(servletContext));
        return pebbleEngine;
    }

    @Bean
    public PebbleViewResolver pebbleViewResolver(final ServletContext servletContext) {
        final PebbleViewResolver viewResolver = new PebbleViewResolver();
        viewResolver.setPrefix("/WEB-INF/pebble/");
        viewResolver.setSuffix(".html");
        viewResolver.setOrder(CONFIG.getOrder());
        viewResolver.setPebbleEngine(this.pebbleEngine(servletContext));
        return viewResolver;
    }
}
