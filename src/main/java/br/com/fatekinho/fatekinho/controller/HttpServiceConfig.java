package br.com.fatekinho.fatekinho.controller;

import br.com.fatekinho.fatekinho.service.HttpService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpServiceConfig {

    @Bean
    public HttpService httpService() {
        return new HttpService();
    }

}
