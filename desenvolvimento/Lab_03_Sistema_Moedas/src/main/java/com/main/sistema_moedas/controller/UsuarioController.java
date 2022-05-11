package com.main.sistema_moedas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.main.sistema_moedas.model.Instituicao;
import com.main.sistema_moedas.repository.InstituicaoRepository;

@Controller
@RequestMapping("/usuario")

public class UsuarioController {
	@Autowired
	private InstituicaoRepository iRepository;
	
	@GetMapping("/new")
	public ModelAndView newUsuario() {
		ModelAndView mv = new ModelAndView("usuarios/new");
		List<Instituicao> instituicaos = iRepository.findAll();
		mv.addObject("instituicaos", instituicaos);
		return mv;
	}
	

}
