package br.com.fatekinho.fatekinho.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Loterias")
public class Loteria {
    @Getter @Setter
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Getter @Setter
    private String Codigo;

    @Getter @Setter
    private String Nome;

    @Getter @Setter
    private String Descricao;

}
