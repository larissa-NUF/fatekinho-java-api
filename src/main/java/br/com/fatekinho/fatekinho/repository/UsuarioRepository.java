package br.com.fatekinho.fatekinho.repository;

import br.com.fatekinho.fatekinho.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> { }
