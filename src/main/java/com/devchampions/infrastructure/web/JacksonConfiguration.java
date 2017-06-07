package com.devchampions.infrastructure.web;

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class JacksonConfiguration {

    @Bean
    Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> builder.modules(new JavaTimeModule(), new Jdk8Module());
    }

}
