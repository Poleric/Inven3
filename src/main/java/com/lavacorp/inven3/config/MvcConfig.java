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
        registry.addViewController("/").setViewName("item/index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/item").setViewName("item/index");
        registry.addViewController("/item/item-low").setViewName("item/item-low");
        registry.addViewController("/item/category").setViewName("item/category/index");
        registry.addViewController("/stock").setViewName("stock/index");
        registry.addViewController("/stock/location").setViewName("stock/location/index");
        registry.addViewController("/supplier").setViewName("supplier/index");
        registry.addViewController("/purchase").setViewName("purchase/index");
        registry.addViewController("/purchase/add-stock").setViewName("purchase/add-stock");
        registry.addViewController("/purchase/return").setViewName("purchase/return/index");
        registry.addViewController("/sales").setViewName("sales/index");
        registry.addViewController("/sales/add-stock").setViewName("sales/add-stock");
        registry.addViewController("/sales/return").setViewName("sales/return/index");
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
