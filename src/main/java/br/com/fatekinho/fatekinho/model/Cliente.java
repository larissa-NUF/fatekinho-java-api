package br.com.fatekinho.fatekinho.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Getter @Setter
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cliente;

    @Getter @Setter
    private String nome;

    @Getter @Setter
    private String data_nasc;

    @Getter @Setter
    private String cpf;

    @Getter @Setter
    private String cep;

    @Getter @Setter
    private String numero;

    @Getter @Setter
    private String complemento;
}
