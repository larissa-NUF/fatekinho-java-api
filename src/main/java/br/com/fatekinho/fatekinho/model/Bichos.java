package br.com.fatekinho.fatekinho.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Bichos {

    @Getter @Setter
    private String nome;

    @Getter @Setter
    private int numero;

    @Getter @Setter
    private List<String> dezenas;
    public Bichos(String nome, int numero, List<String> dezenas) {
        this.nome = nome;
        this.numero = numero;
        this.dezenas = dezenas;
    }

}


