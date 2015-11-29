package com.jeroenreijn.examples.config;

import java.util.Map;
import java.util.Optional;

import com.google.common.collect.ImmutableMap;

/**
 * @author : patouche - 29/11/15.
 */
public interface TemplatingProfiles {

    String JSP = "jsp";

    String FREEMARKER = "freemarker";

    String HTTL = "httl";

    String VELOCITY = "velocity";

    String THYMELEAF = "thymeleaf";

    String JADE = "jade";

    String SCALATE = "scalate";

    String MUSTACHE = "mustache";

    String PEBBLE = "pebble";

    String HANDLEBARS = "handlebars";

    String JTWIG = "jtwig";

    Map<String, TemplatingConfig> ORDERS = ImmutableMap.<String, TemplatingConfig>builder()
            .put(FREEMARKER, new TemplatingConfig(0))
            .put(VELOCITY, new TemplatingConfig(1))
            .put(THYMELEAF, new TemplatingConfig(2))
            .put(JADE, new TemplatingConfig(5))
            .put(JSP, new TemplatingConfig(6))
            .put(SCALATE, new TemplatingConfig(7))
            .put(MUSTACHE, new TemplatingConfig(8))
            .put(HTTL, new TemplatingConfig(9))
            .put(PEBBLE, new TemplatingConfig(10))
            .put(HANDLEBARS, new TemplatingConfig(11))
            .put(JTWIG, new TemplatingConfig(12))
            .build();

    static TemplatingConfig get(String solution) {
        return Optional.ofNullable(ORDERS.get(solution))
                .orElseThrow(() -> new RuntimeException("Cannot find config for templating : " + solution));
    }

}
