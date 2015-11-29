package com.jeroenreijn.examples.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jeroenreijn.examples.InMemoryPresentationsRepository;
import com.jeroenreijn.examples.services.PresentationsService;

/**
 * @author : patouche - 29/11/15.
 */
@Configuration
public class AppConfiguration {

    @Bean
    public InMemoryPresentationsRepository presentationsRepository() {
        return new InMemoryPresentationsRepository();
    }

    @Bean
    public PresentationsService presentationsService() {
        return new PresentationsService();
    }
}
