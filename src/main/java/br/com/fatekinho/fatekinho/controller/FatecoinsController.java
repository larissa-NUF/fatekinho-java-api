package br.com.fatekinho.fatekinho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatekinho.fatekinho.model.Fatecoins;
import br.com.fatekinho.fatekinho.repository.FatecoinsRepository;
import io.swagger.v3.oas.annotations.Operation;

//site para testar: http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

@RestController
@RequestMapping("/fatecoins")
public class FatecoinsController {
	
	@Autowired
	private FatecoinsRepository _FatecoinsRepository;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/get/{id}")
	@Operation(summary = "Obtem o saldo de coins por id")
	public Fatecoins findByID(@PathVariable Long id) {
		return _FatecoinsRepository.findById(id).get();
		
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/get/cliente/{idCliente}")
	@Operation(summary = "Obtem o saldo de coins por idCliente")
	public Fatecoins findbyIDCliente(@PathVariable Long idCliente) {
		return _FatecoinsRepository.findByIdCliente(idCliente).get();
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping(value = "/update/{id}")
	@Operation(summary = "Atualiza o saldo")
	public void updateSaldo(@PathVariable Long id,@RequestBody Fatecoins saldo) {
		Fatecoins saldo1 = _FatecoinsRepository.findByIdCliente(id).get();
		saldo1.setQtd(saldo.getQtd());
		_FatecoinsRepository.save(saldo1);
	}
	
		
}
