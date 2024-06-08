package br.com.fatekinho.fatekinho.service.interfaces;

import br.com.fatekinho.fatekinho.model.response.LoteriaResultadoResponse;

import java.util.List;

public interface IHttpService {
    List<LoteriaResultadoResponse> ObterResultadoLoteria(String tipoLoteria);
    LoteriaResultadoResponse ObterResultadoConcurso(String tipoLoteria,String tipoConcurso);
    List<String> ObterLoteria();
}
