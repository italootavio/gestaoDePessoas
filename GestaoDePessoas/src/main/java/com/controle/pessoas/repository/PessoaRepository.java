package com.controle.pessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controle.pessoas.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Integer>{}
