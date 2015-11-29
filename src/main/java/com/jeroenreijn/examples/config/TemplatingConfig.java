package com.jeroenreijn.examples.config;

public class TemplatingConfig {

    private final int order;

    protected TemplatingConfig(final int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}