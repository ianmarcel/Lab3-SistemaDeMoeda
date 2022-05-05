package com.main.sistema_moedas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.main.sistema_moedas.model.Endereco;
import com.main.sistema_moedas.model.usuario.Empresa;
import com.main.sistema_moedas.model.usuario.Role;
import com.main.sistema_moedas.repository.RoleRepository;
import com.main.sistema_moedas.repository.UsuarioRepository;

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

}
