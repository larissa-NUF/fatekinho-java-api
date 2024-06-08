package br.com.fatekinho.fatekinho.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

public class LoteriaResultadoResponse {
    @Getter @Setter
    @JsonProperty("acumulou")
    private boolean Acumulou;

    @Getter @Setter
    @JsonProperty("concurso")
    private int Concurso;

    @Getter @Setter
    @JsonProperty("data")
    private String Data;

    @Getter @Setter
    @JsonProperty("dataProximoConcurso")
    private String DataProximoConcurso;

    @Getter @Setter
    @JsonProperty("dezenas")
    private List<String> Dezenas;

    @Getter @Setter
    @JsonProperty("dezenasOrdemSorteio")
    private List<String> DezenasOrdemSorteio;

    @Getter @Setter
    @JsonProperty("estadosPremiados")
    private List<Object> EstadosPremiados;

    @Getter @Setter
    @JsonProperty("local")
    private String Local;

    @Getter @Setter
    @JsonProperty("localGanhadores")
    private List<Ganhadores> LocalGanhadores;

    @Getter @Setter
    @JsonProperty("loteria")
    private String Loteria;

    @Getter @Setter
    @JsonProperty("mesSorte")
    private String MesSorte;

    @Getter @Setter
    @JsonProperty("observacao")
    private String Observacao;

    @Getter @Setter
    @JsonProperty("premiacoes")
    private List<Premiacao> Premiacoes;

    @Getter @Setter
    @JsonProperty("proximoConcurso")
    private int ProximoConcurso;

    @Getter @Setter
    @JsonProperty("timeCoracao")
    private String TimeCoracao;

    @Getter @Setter
    @JsonProperty("trevos")
    private List<String> Trevos;

    @Getter @Setter
    @JsonProperty("valorAcumuladoConcursoEspecial")
    private long ValorAcumuladoConcursoEspecial;

    @Getter @Setter
    @JsonProperty("valorAcumuladoConcurso_0_5")
    private long ValorAcumuladoConcurso05;

    @Getter @Setter
    @JsonProperty("valorAcumuladoProximoConcurso")
    private long ValorAcumuladoProximoConcurso;

    @Getter @Setter
    @JsonProperty("valorArrecadado")
    private long ValorArrecadado;

    @Getter @Setter
    @JsonProperty("valorEstimadoProximoConcurso")
    private long ValorEstimadoProximoConcurso;
}

