package org.example.config;

import org.example.service.Coach;
import org.example.service.impl.Football;
import org.example.service.impl.Golf;
import org.example.service.impl.Tennis;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan()
public class BeanConfig {
    @Bean
    public Coach football(){
        return new Football();//method name becomes id of bean
    }
    @Bean
    public Coach tennis(){
        return new Tennis();//method name becomes id of bean
    }
    @Bean
    public Coach golf(){
        return new Golf();//method name becomes id of bean
    }
}
