package com.main.sistema_moedas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.sistema_moedas.model.Instituicao;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {

}
