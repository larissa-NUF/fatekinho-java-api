package br.com.fatekinho.fatekinho.controller;

import br.com.fatekinho.fatekinho.model.Loteria;
import br.com.fatekinho.fatekinho.model.response.LoteriaResultadoResponse;
import br.com.fatekinho.fatekinho.repository.LoteriasRepository;
import br.com.fatekinho.fatekinho.service.interfaces.IHttpService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/loteria")
public class LoteriaController {

    private final IHttpService httpService;

    @Autowired
    private LoteriasRepository _loteriaRepository;

    @Autowired
    public LoteriaController(IHttpService httpService) {
        this.httpService = httpService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/", produces = "application/json")
    @Operation(summary = "Retorna loterias")
    public List<Loteria> FindAll() {
        return _loteriaRepository.findAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/obter-por-codigo", produces = "application/json")
    @Operation(summary = "Retorna detalhes da loteria por codigo")
    public Loteria FindById(@RequestParam String codigo) {
        return _loteriaRepository.findByCodigo(codigo);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/todos", produces = "application/json")
    @Operation(summary = "Retorna resultado da loteria especificada")
    public List<LoteriaResultadoResponse> GetTodos(@RequestParam String tipoLoteria) {
        return this.httpService.ObterResultadoLoteria(tipoLoteria);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/concurso", produces = "application/json")
    @Operation(summary = "Retorna resultado do concurso especificado")
    public LoteriaResultadoResponse GetConcurso(@RequestParam String tipoLoteria,@RequestParam String tipoConcurso) {
        return this.httpService.ObterResultadoConcurso(tipoLoteria, tipoConcurso);
    }
}

