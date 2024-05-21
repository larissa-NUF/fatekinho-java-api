package br.com.fatekinho.fatekinho.controller;

import java.util.List;
import java.util.Optional;

import br.com.fatekinho.fatekinho.model.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatekinho.fatekinho.model.Usuario;
import br.com.fatekinho.fatekinho.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository _usuarioRepository;

    @GetMapping(value = "/all")
    @Operation(summary = "Obtem todos os usuários")
    public List<Usuario> FindAll() {
        return _usuarioRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Obtem usuário por id")
    public Usuario findByID(@PathVariable Long id) {
        return _usuarioRepository.findById(id).get();
    }
    
    @GetMapping(value = "/e-mail/{email}")
    @Operation(summary = "Obtem por e-mail")
    public Usuario findByEmail(@PathVariable String email) {
        return _usuarioRepository.findByEmail(email).orElse(null);
    }

    @PostMapping(value = "/insert")
    @Operation(summary = "Salva usuário")
    public Usuario InsertUser(@RequestBody Usuario user) {
        return _usuarioRepository.save(user);
    }

    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Atualiza usuário")
    public void updateUser(@PathVariable Long id,@RequestBody Usuario user) {
        Usuario user1 = _usuarioRepository.findById(id).get();
        user1.setSenha(user.getSenha());
        user1.setEmail(user.getEmail());
        _usuarioRepository.save(user1);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Deleta usuário")
    public void deleteUser(@PathVariable Long id) {
        _usuarioRepository.delete(_usuarioRepository.findById(id).get());
    }

    @CrossOrigin
    @PostMapping(value = "/login")
    @Operation(summary = "Realiza login")
    public LoginResponse login(@RequestBody Usuario user) {
        Optional<Usuario> optionalUser = _usuarioRepository.findByEmail(user.getEmail());
        
        if (optionalUser.isPresent() && optionalUser.get().getSenha().equals(user.getSenha())) {
            Usuario loggedInUser = optionalUser.get();
            return new LoginResponse(true, loggedInUser.getIdUsuario(), "Login bem-sucedido!"); // Supondo que getIdUsuario() retorne o ID do usuário
        }
        
        return new LoginResponse(false, 0, "Nome de usuário ou senha incorretos.");
    }


}
