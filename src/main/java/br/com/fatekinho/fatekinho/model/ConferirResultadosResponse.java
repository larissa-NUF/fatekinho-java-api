package br.com.fatekinho.fatekinho.model;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ConferirResultadosResponse {

    @Getter @Setter
    @Id
    private List<String> resultados;

    @Getter @Setter
    private List<String> jogo;

    @Getter @Setter
    private int modalidadeId;

    @Getter @Setter
    private int tipo;

    @Getter @Setter
    private double valorApostado;
}
