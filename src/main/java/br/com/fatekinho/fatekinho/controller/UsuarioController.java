package br.com.fatekinho.fatekinho.controller;

import br.com.fatekinho.fatekinho.model.Usuario;
import br.com.fatekinho.fatekinho.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository _usuarioRepository;

    @GetMapping(value = "/")
    public List<Usuario> Get() {
        return _usuarioRepository.findAll();
    }
}
