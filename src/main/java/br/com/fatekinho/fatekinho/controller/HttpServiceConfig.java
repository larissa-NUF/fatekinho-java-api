package br.com.fatekinho.fatekinho.controller;

import br.com.fatekinho.fatekinho.service.HttpService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info=@Info(title="API Fatekinho"))
public class HttpServiceConfig {

    @Bean
    HttpService httpService() {
        return new HttpService();
    }

}
