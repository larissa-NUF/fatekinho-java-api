package br.com.fatekinho.fatekinho.service.interfaces;

import br.com.fatekinho.fatekinho.model.ResultadosJogoBicho;
import br.com.fatekinho.fatekinho.model.response.LoteriaResultadoResponse;

import java.util.List;

public interface IJogoBichoService {
    ResultadosJogoBicho GerarResultado();
    double ConferirResultados (List<String> resultados, List<String> jogo, int modalidadeId, int tipo, double valorAposado);
}
