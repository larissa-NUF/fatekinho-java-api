package br.com.fatekinho.fatekinho.repository;

import br.com.fatekinho.fatekinho.model.ApostasJogoBicho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApostasJogoBichoRepository extends JpaRepository<ApostasJogoBicho, Long> {
    @Query(value = "SELECT * FROM ApostasJogoBicho where Finalizada = 0", nativeQuery = true)
    List<ApostasJogoBicho> getAberto();

    @Query(value = "select a.*,r.DataCadastro AS DataCadastroSorteio from ApostasJogoBicho a left join ResultadosJogoBicho AS r on r.Id = a.IdSorteio where a.IdCliente = ?1 order by a.DataCadastro desc", nativeQuery = true)
    List<ApostasJogoBicho> getByIdCliente(int idCliente);
}