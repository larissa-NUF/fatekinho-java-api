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
    private int idCliente;
}
