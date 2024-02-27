package com.deveso.documentclassificationapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Tüm endpoint'ler için uygula
                        .allowedOrigins("http://localhost:5173") // Frontend uygulamanızın çalıştığı adres
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // İzin verilen HTTP metodları
                        .allowedHeaders("*") // Tüm header'lar
                        .allowCredentials(true);
            }
        };
    }
}
