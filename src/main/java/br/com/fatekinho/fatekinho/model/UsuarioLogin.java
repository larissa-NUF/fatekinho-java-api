package br.com.fatekinho.fatekinho.model;

import lombok.Getter;
import lombok.Setter;

public class UsuarioLogin {
    @Getter
    @Setter
    private String email;

    @Getter @Setter
    private String senha;
}
