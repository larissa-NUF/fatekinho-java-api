package br.com.fatekinho.fatekinho.controller;

import br.com.fatekinho.fatekinho.model.*;
import br.com.fatekinho.fatekinho.repository.ApostasJogoBichoRepository;
import br.com.fatekinho.fatekinho.repository.FatecoinsRepository;
import br.com.fatekinho.fatekinho.repository.LoteriasRepository;
import br.com.fatekinho.fatekinho.service.interfaces.IHttpService;
import br.com.fatekinho.fatekinho.service.interfaces.IJogoBichoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogo-bicho")
public class JogoBichoController {
    private final IJogoBichoService jogoBichoService;
    @Autowired
    private ApostasJogoBichoRepository _apostasJogoBichoRepository;

    @Autowired
    private FatecoinsRepository _FatecoinsRepository;
    @Autowired
    public JogoBichoController(IJogoBichoService jogoBichoService) {
        this.jogoBichoService = jogoBichoService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/", produces = "application/json")
    @Operation(summary = "Salva aposta do jogo do bicho")
    public ApostasJogoBicho Save(@RequestBody ApostasJogoBicho aposta)
    {
        aposta.setFinalizada(false);
        aposta.setGanho(0);

        Fatecoins saldo1 = _FatecoinsRepository.findByIdCliente(aposta.getIdCliente()).get();
        saldo1.setQtd(saldo1.getQtd() - (int)aposta.getValor());
        _FatecoinsRepository.save(saldo1);

        return _apostasJogoBichoRepository.save(aposta);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/gerar", produces = "application/json")
    @Operation(summary = "Gera Resultado do jogo do bicho")
    public ResultadosJogoBicho Gerar()
    {
        return jogoBichoService.GerarResultado();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/conferir", produces = "application/json")
    @Operation(summary = "Confere Resultado do jogo do bicho")
    public double ConferirResultados(@RequestBody ConferirResultadosResponse conferirResultadosResponse)
    {
        return jogoBichoService.ConferirResultados(
                conferirResultadosResponse.getResultados(),
                conferirResultadosResponse.getJogo(),
                conferirResultadosResponse.getModalidadeId(),
                conferirResultadosResponse.getTipo(),
                conferirResultadosResponse.getValorApostado()
        );
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/apostas", produces = "application/json")
    @Operation(summary = "Retorna apostas do cliente")
    public List<ApostasJogoBicho> FindById(@RequestParam int idCliente) {
        return _apostasJogoBichoRepository.getByIdCliente(idCliente);
    }
}
