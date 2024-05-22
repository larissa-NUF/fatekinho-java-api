package br.com.fatekinho.fatekinho.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fatekinho.fatekinho.model.Fatecoins;
import br.com.fatekinho.fatekinho.model.Usuario;

@Repository
public interface FatecoinsRepository extends JpaRepository<Fatecoins, Long>{
	Optional<Fatecoins> findByIdCliente(long IdCliente);
}
