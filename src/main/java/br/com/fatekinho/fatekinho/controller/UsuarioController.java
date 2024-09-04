package br.com.fatekinho.fatekinho.controller;

import java.util.List;
import java.util.Optional;

import br.com.fatekinho.fatekinho.model.*;
import br.com.fatekinho.fatekinho.model.response.LoginResponse;
import br.com.fatekinho.fatekinho.repository.ClienteRepository;
import br.com.fatekinho.fatekinho.repository.FatecoinsRepository;
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

import br.com.fatekinho.fatekinho.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository _usuarioRepository;
    @Autowired
    private ClienteRepository _clienteRepository;
    @Autowired
    private FatecoinsRepository _FatecoinsRepository;

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


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/insert")
    @Operation(summary = "Salva usuário")
    public Usuario InsertUser(@RequestBody UsuarioDTO usuarioDTO) {
        System.out.println(usuarioDTO.getUsuario().getEmail());
        _clienteRepository.save(usuarioDTO.getCliente());
        usuarioDTO.getUsuario().setIdCliente(usuarioDTO.getCliente().getId_cliente());
        _usuarioRepository.save(usuarioDTO.getUsuario());
        Fatecoins coins = new Fatecoins();
        coins.setQtd(0);
        coins.setIdCliente(usuarioDTO.getCliente().getId_cliente());
        coins.setIdUsuario(usuarioDTO.getUsuario());
        _FatecoinsRepository.save(coins);
        return usuarioDTO.getUsuario();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public LoginResponse login(@RequestBody UsuarioLogin user) {
        Optional<Usuario> optionalUser = _usuarioRepository.findByEmail(user.getEmail());
        
        if (optionalUser.isPresent() && optionalUser.get().getSenha().equals(user.getSenha())) {
            Usuario loggedInUser = optionalUser.get();
            Cliente cliente = _clienteRepository.getById((long) loggedInUser.getIdCliente());
            loggedInUser.setCpf(cliente.getCpf());
            loggedInUser.setNome(cliente.getNome());
            loggedInUser.setData_nasc(cliente.getData_nasc());
            loggedInUser.setCep(cliente.getCep());
            return new LoginResponse(true, loggedInUser, "Login bem-sucedido!"); // Supondo que getIdUsuario() retorne o ID do usuário
        }
        
        return new LoginResponse(false, null, "Nome de usuário ou senha incorretos.");
    }


}
