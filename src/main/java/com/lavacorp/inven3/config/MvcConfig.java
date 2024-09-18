package com.lavacorp.inven3.config;

import com.lavacorp.inven3.dao.OrderDirection;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new Converter<String, OrderDirection>() {
            @Override
            public OrderDirection convert(@NotNull String source) {
                try {
                    return OrderDirection.valueOf(source.toUpperCase());
                } catch (IllegalArgumentException e) {
                    return OrderDirection.ASC;
                }
            }
        });
    }
}
