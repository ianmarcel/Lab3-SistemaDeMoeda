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
import com.main.sistema_moedas.model.Endereco;
import com.main.sistema_moedas.model.usuario.Empresa;
import com.main.sistema_moedas.model.usuario.Role;
import com.main.sistema_moedas.repository.RoleRepository;
import com.main.sistema_moedas.repository.UsuarioRepository;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {
	
	@Autowired
	private RoleRepository rRepository;
	@Autowired
	private UsuarioRepository uRepository;
	@PostMapping("/new")
	public String novo(Empresa emp ,Endereco e) {
		
		Role admin = rRepository.findByNameRole("ROLE_ADMIN").orElse(new Role("ROLE_ADMIN"));
		Role empresa = rRepository.findByNameRole("ROLE_EMPRESA").orElse(new Role("ROLE_EMPRESA"));
		List<Role> listaderoles = new ArrayList<>();
		listaderoles.add(admin);
		listaderoles.add(empresa);
		emp.setRoles(listaderoles);
		emp.setEndereco(e);
		uRepository.save(emp);
	   return  ("redirect:/empresa/");
	}

	@GetMapping("/editar")
	public ModelAndView editaEmpresa(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario user = (Usuario) auth.getPrincipal();
		ModelAndView mv = new ModelAndView("empresa/editar");
		mv.addObject("empresa", ((Empresa) user));
		return mv;
	}

	@PostMapping("/update")
	public String updateEmpresa(@Validated Empresa empresa, BindingResult result){
		if(result.hasErrors()){
			return "empresa/editar";
		}
		uRepository.save(empresa);
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
