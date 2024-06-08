package br.com.fatekinho.fatekinho.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Premiacao {
    @Getter @Setter
    @JsonProperty("faixa")
    private int Faixa;

    @Getter @Setter
    @JsonProperty("descricao")
    private String Descricao;

    @Getter @Setter
    @JsonProperty("valorPremio")
    private int ValorPremio;

    @Getter @Setter
    @JsonProperty("ganhadores")
    private int Ganhadores;
}
