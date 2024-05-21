package br.com.fatekinho.fatekinho.controller;

import java.util.List;
import java.util.Optional;

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
    public List<Usuario> FindAll() {
        return _usuarioRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Usuario findByID(@PathVariable Long id) {
        return _usuarioRepository.findById(id).get();
    }
    
    @GetMapping(value = "/e-mail/{email}")
    public Usuario findByEmail(@PathVariable String email) {
        return _usuarioRepository.findByEmail(email).orElse(null);
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

    @CrossOrigin
    @PostMapping(value = "/login")
    public LoginResponse login(@RequestBody Usuario user) {
        Optional<Usuario> optionalUser = _usuarioRepository.findByEmail(user.getEmail());
        
        if (optionalUser.isPresent() && optionalUser.get().getSenha().equals(user.getSenha())) {
            Usuario loggedInUser = optionalUser.get();
            return new LoginResponse(true, loggedInUser.getIdUsuario(), "Login bem-sucedido!"); // Supondo que getIdUsuario() retorne o ID do usuário
        }
        
        return new LoginResponse(false, 0, "Nome de usuário ou senha incorretos.");
    }

    public class LoginResponse {
        private boolean success;
        private long userId; // Supondo que userId é um long
        private String message;

        // Construtor
        public LoginResponse(boolean success, long userId, String message) {
            this.success = success;
            this.userId = userId;
            this.message = message;
        }

        // Getters e Setters
        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
