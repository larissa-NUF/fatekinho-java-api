package br.com.fatekinho.fatekinho.model.response;

import br.com.fatekinho.fatekinho.model.Usuario;
import lombok.Getter;
import lombok.Setter;

public class LoginResponse {
    @Getter @Setter
    private boolean success;

    @Getter @Setter
    private Usuario user;

    @Getter @Setter// Supondo que userId é um long
    private String message;

    // Construtor
    public LoginResponse(boolean success, Usuario user, String message) {
        this.success = success;
        this.user = user;
        this.message = message;
    }

}