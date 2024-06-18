package br.com.fatekinho.fatekinho.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Usuarios")
public class Usuario
{
    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuario;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private boolean tipo;

    @Getter @Setter
    private String senha;

    @Getter @Setter
    private int idCliente;

    @Getter @Setter
    @Transient
    private String nome;

    @Getter @Setter
    @Transient
    private String cpf;

    @Getter @Setter
    @Transient
    private String data_nasc;

    @Getter @Setter
    @Transient
    private String cep;

    @Getter @Setter
    @Transient
    private String numero;
}
