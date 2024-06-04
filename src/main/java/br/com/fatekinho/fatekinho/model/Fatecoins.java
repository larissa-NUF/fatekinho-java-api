package br.com.fatekinho.fatekinho.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "fatecoins")
public class Fatecoins {
	@Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCoins;

    @Getter @Setter
    private int qtd;

    @Getter @Setter
    private int idCliente;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario idUsuario;
}
