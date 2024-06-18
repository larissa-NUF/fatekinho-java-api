package br.com.fatekinho.fatekinho.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "ResultadosJogoBicho")
public class ResultadosJogoBicho {
    @Getter @Setter
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Getter @Setter
    private Date DataCadastro;

    @Getter @Setter
    private String Jogo;
}
