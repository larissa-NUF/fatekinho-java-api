package br.com.fatekinho.fatekinho.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "ApostasJogoBicho")
public class ApostasJogoBicho {
    @Getter @Setter
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int Id;

    @Getter @Setter
    @JsonProperty("idCliente")
    private int IdCliente;

    @Getter @Setter
    @JsonProperty("valor")
    private double Valor;

    @Getter @Setter
    @JsonProperty("jogo")
    private String Jogo;

    @Getter @Setter
    @JsonProperty("modalidade")
    private int Modalidade;

    @Getter @Setter
    @JsonProperty("tipo")
    private int Tipo;

    @Getter @Setter
    @JsonProperty("dataCadastro")
    private Date DataCadastro;

    @Getter @Setter
    @JsonProperty("ganho")
    private double Ganho;

    @Getter @Setter
    @JsonProperty("finalizada")
    private boolean Finalizada;

    @Getter @Setter
    @Column(nullable=true)
    @JsonProperty("idSorteio")
    private int IdSorteio;

    @Getter @Setter
    @JsonProperty("dataCadastroSorteio")
    @Column(nullable=true,insertable = false, updatable = false)
    private Date DataCadastroSorteio;
}

