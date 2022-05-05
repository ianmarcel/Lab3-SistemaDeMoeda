package com.main.sistema_moedas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.sistema_moedas.model.Conta;
import com.main.sistema_moedas.model.Endereco;
import com.main.sistema_moedas.model.usuario.Aluno;
import com.main.sistema_moedas.repository.UsuarioRepository;

@Controller
@RequestMapping("/aluno")
public class AlunoController {
	
	@Autowired
	private UsuarioRepository uRepository;
	@PostMapping("/new")
	public String novo(Aluno a,Endereco e) {
		a.setEndereco(e);
		a.setConta(new Conta());
		uRepository.save(a);
	   return  ("redirect:/aluno/");
	}

}
