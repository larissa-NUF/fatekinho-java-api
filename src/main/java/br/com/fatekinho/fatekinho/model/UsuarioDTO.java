package br.com.fatekinho.fatekinho.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class UsuarioDTO {
    @JsonProperty("cliente")
    @Getter @Setter
    private Cliente Cliente;

    @JsonProperty("usuario")
    @Getter @Setter
    private Usuario Usuario;
}
