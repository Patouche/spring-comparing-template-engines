package com.jeroenreijn.examples.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author : patouche - 29/11/15.
 */
public class BenchmarkAppInitializer implements WebApplicationInitializer {

    /** Application initializer classes. */
    private static final List<Class> INITIALIZER_CLASSES = Arrays.asList(TemplatingProfileInitializer.class);

    @Override
    public void onStartup(final ServletContext container) {

        container.setInitParameter("contextInitializerClasses", INITIALIZER_CLASSES.stream()
                .map(Class::getName)
                .collect(Collectors.joining(";")));
        container.addFilter("encoding-filter", this.encodingFilter());

        final AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfiguration.class);
        container.addListener(new ContextLoaderListener(rootContext));

        final AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(WebConfiguration.class);

        // Register and map the dispatcher servlet
        final ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

    public CharacterEncodingFilter encodingFilter() {
        final CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding(StandardCharsets.UTF_8.displayName());
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }

}