package com.main.sistema_moedas.controller;

import java.util.ArrayList;
import java.util.List;

import com.main.sistema_moedas.model.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.sistema_moedas.model.Conta;
import com.main.sistema_moedas.model.Endereco;
import com.main.sistema_moedas.model.usuario.Aluno;
import com.main.sistema_moedas.model.usuario.Role;
import com.main.sistema_moedas.repository.RoleRepository;
import com.main.sistema_moedas.repository.UsuarioRepository;
import org.springframework.web.servlet.ModelAndView;

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

	@GetMapping("/editar")
	public ModelAndView editaAluno(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario user = (Usuario) auth.getPrincipal();
		ModelAndView mv = new ModelAndView("aluno/editar");
		mv.addObject("aluno", ((Aluno) user));
		return mv;
	}

	@PostMapping("/update")
	public String updateAluno(@Validated Aluno aluno, BindingResult result){
		if(result.hasErrors()){
			return "aluno/editar";
		}
		uRepository.save(aluno);
		return "redirect:/";
	}

	@GetMapping("/deletar")
	public String deletarUsuario(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario user = (Usuario) auth.getPrincipal();
		uRepository.delete(user);
		auth.setAuthenticated(false);
		return "redirect:/index";
	}
}
