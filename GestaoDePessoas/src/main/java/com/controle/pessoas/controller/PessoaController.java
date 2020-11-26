package com.controle.pessoas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.controle.pessoas.model.Pessoa;
import com.controle.pessoas.repository.*;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	@Autowired
	PessoaRepository repository;
	
	@GetMapping
	public List<Pessoa> getPessoas() {
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> getPessoaId(@PathVariable Integer id) {
		if(repository.findById(id).isPresent()) {
			return new ResponseEntity<Pessoa>(repository.findById(id).get(),HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Pessoa inserirPessoa(@RequestBody Pessoa p) {
		repository.save(p);
		return p;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> editarPessoa(@PathVariable Integer id, @RequestBody Pessoa p) {
		Optional<Pessoa> pBD = repository.findById(id);
		if (!pBD.isEmpty()) {
			Pessoa pAux = pBD.get();
			pAux.setCPF(p.getCPF());
			pAux.setDataNascimento(p.getDataNascimento());
			pAux.setIdade(p.getIdade());
			pAux.setNome(p.getNome());
			return new ResponseEntity<Pessoa>(repository.save(pAux),HttpStatus.CREATED);
		}
		return ResponseEntity.notFound().build();
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Pessoa> apagarPessoa(@PathVariable Integer id) {
		Optional<Pessoa> p = repository.findById(id);
		if (!p.isEmpty()) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
