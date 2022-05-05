package com.main.sistema_moedas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.sistema_moedas.model.Conta;
import com.main.sistema_moedas.model.Endereco;
import com.main.sistema_moedas.model.usuario.Aluno;
import com.main.sistema_moedas.model.usuario.Role;
import com.main.sistema_moedas.repository.RoleRepository;
import com.main.sistema_moedas.repository.UsuarioRepository;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

	@Autowired
	private RoleRepository rRepository;
	@Autowired
	private UsuarioRepository uRepository;

	@PostMapping("/new")
	public String novo(Aluno a, Endereco e) {
		Role admin = rRepository.findByNameRole("ROLE_ADMIN").orElse(new Role("ROLE_ADMIN"));
		Role aluno = rRepository.findByNameRole("ROLE_ALUNO").orElse(new Role("ROLE_ALUNO"));
		List<Role> listaderoles = new ArrayList<>();
		listaderoles.add(admin);
		listaderoles.add(aluno);
		a.setRoles(listaderoles);
		a.setEndereco(e);
		a.setConta(new Conta());
		uRepository.save(a);
		return ("redirect:/aluno/");
	}

}
