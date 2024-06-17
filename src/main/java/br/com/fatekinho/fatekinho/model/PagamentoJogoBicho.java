package br.com.fatekinho.fatekinho.model;

import lombok.Getter;
import lombok.Setter;

public class PagamentoJogoBicho {
    @Getter @Setter
    private int modalidadeId;

    @Getter @Setter
    private double valorFixo;

    @Getter @Setter
    private double[] unitario;

    public PagamentoJogoBicho(int modalidadeId, double valorFixo, double[] unitario) {
        this.modalidadeId = modalidadeId;
        this.valorFixo = valorFixo;
        this.unitario = unitario;
    }
}
