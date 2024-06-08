package br.com.fatekinho.fatekinho.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class Ganhadores {
    @Getter
    @Setter
    @JsonProperty("posicao")
    private int Posicao;

    @Getter @Setter
    @JsonProperty("ganhadores")
    private int Ganhadores;

    @Getter @Setter
    @JsonProperty("municipio")
    private String Municipio;

    @Getter @Setter
    @JsonProperty("nomeFatansiaUL")
    private String NomeFatansiaUL;

    @Getter @Setter
    @JsonProperty("uf")
    private String Uf;

    @Getter @Setter
    @JsonProperty("serie")
    private String Serie;
}
