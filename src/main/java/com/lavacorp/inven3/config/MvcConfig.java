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
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/item").setViewName("item/index");
        registry.addViewController("/item/location").setViewName("item/category/index");
        registry.addViewController("/stock").setViewName("stock/index");
        registry.addViewController("/stock/location").setViewName("stock/location/index");
        registry.addViewController("/supplier").setViewName("supplier/index");
        registry.addViewController("/order").setViewName("order/purchase/index");
        registry.addViewController("/order/purchase").setViewName("order/purchase/index");
        registry.addViewController("/order/purchase/return").setViewName("order/purchase/return/index");
        registry.addViewController("/order/sales").setViewName("order/sales/index");
        registry.addViewController("/order/sales/return").setViewName("order/sales/return/index");
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
