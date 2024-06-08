package br.com.fatekinho.fatekinho.repository;

import br.com.fatekinho.fatekinho.model.Loteria;
import br.com.fatekinho.fatekinho.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoteriasRepository extends JpaRepository<Loteria, Long> {

    @Query("select u from Loteria u where u.Codigo = ?1")
    Loteria findByCodigo(String codigo);
}