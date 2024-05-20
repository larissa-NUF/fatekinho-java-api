package br.com.fatekinho.fatekinho.controller;

import br.com.fatekinho.fatekinho.model.Usuario;
import br.com.fatekinho.fatekinho.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository _usuarioRepository;

    @GetMapping(value = "/all")
    public List<Usuario> FindAll() {
        return _usuarioRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Usuario findByID(@PathVariable Long id) {
        return _usuarioRepository.findById(id).get();
    }

    @PostMapping(value = "/insert")
    public Usuario InsertUser(@RequestBody Usuario user) {
        return _usuarioRepository.save(user);
    }

    @PutMapping(value = "/update/{id}")
    public void updateUser(@PathVariable Long id,@RequestBody Usuario user) {
        Usuario user1 = _usuarioRepository.findById(id).get();
        user1.setSenha(user.getSenha());
        user1.setEmail(user.getEmail());
        _usuarioRepository.save(user1);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        _usuarioRepository.delete(_usuarioRepository.findById(id).get());
    }
}
