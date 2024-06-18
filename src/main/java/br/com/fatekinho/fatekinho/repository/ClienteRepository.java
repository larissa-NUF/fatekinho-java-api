package br.com.fatekinho.fatekinho.repository;

import br.com.fatekinho.fatekinho.model.Cliente;
import br.com.fatekinho.fatekinho.model.Fatecoins;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
