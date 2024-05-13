package br.com.fatekinho.fatekinho.controller;

import br.com.fatekinho.fatekinho.model.Usuario;
import br.com.fatekinho.fatekinho.repository.UsuarioRepository;
import br.com.fatekinho.fatekinho.service.HttpService;
import br.com.fatekinho.fatekinho.service.interfaces.IHttpService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loteria")
public class LoteriaController {

    private final IHttpService httpService;

    @Autowired
    public LoteriaController(IHttpService httpService) {
        this.httpService = httpService;
    }

    @GetMapping(value = "/ultimos-3-meses", produces = "application/json")
    public JSONArray Get() {
        return this.httpService.ObterResultadoLotoFacil();
    }
}

