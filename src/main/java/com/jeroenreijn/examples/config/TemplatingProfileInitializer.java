package com.jeroenreijn.examples.config;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author : patouche - 29/11/15.
 */
public class TemplatingProfileInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TemplatingProfileInitializer.class);

    @Override
    public void initialize(final ConfigurableApplicationContext applicationContext) {
        final ConfigurableEnvironment environment = applicationContext.getEnvironment();
        final String[] activeProfiles = environment.getActiveProfiles();

        boolean defined = false;

        if (activeProfiles != null && activeProfiles.length != 0) {
            defined = Arrays.stream(activeProfiles)
                    .anyMatch(p -> TemplatingProfiles.ORDERS.keySet().contains(p));
        }

        if (!defined) {
            LOGGER.info("Load all templating solution ...");
            TemplatingProfiles.ORDERS.keySet().stream()
                    .forEach(environment::addActiveProfile);
        }
    }
}
