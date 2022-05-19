package com.main.sistema_moedas.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.main.sistema_moedas.model.Vantagem;
import com.main.sistema_moedas.model.usuario.Empresa;
import com.main.sistema_moedas.repository.VantagemRepository;

@Controller
@RequestMapping("/vantagem")
public class VantagemController {

	private static final String PATH_IMAGENS = "src\\main\\resources\\static\\";

	@Autowired
	private VantagemRepository vRepository;

	@GetMapping("/{id}")
	public ModelAndView mostrar(@PathVariable long id) {
		ModelAndView mv = new ModelAndView("vantagem/vantagem");
		Optional<Vantagem> v = vRepository.findById(id);

		if (v.isEmpty()) {
			mv.addObject("error", true);
		}
		mv.addObject("vantagem", v.get());

		return mv;
	}

	@GetMapping("/")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("vantagem/listar");
		mv.addObject("vantagens", vRepository.findAll());
		return mv;
	}

	@GetMapping("/new")
	public String formularioNova() {
		return "vantagem/new";
	}

	@PostMapping("/new")
	public String nova(String produto, int valor, String descricao, @RequestParam("foto") MultipartFile foto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Empresa e = (Empresa) auth.getPrincipal();
		if (valor < 0)
			return "";
		Vantagem v = new Vantagem(produto, valor, descricao, e);

		v = vRepository.saveAndFlush(v);
		try {
			if (!foto.isEmpty()) {
				byte[] bytes = foto.getBytes();
				String path = "images/" +e.getId() + "/" + v.getId()+ "/";
				String fileName = foto.getOriginalFilename();
				
				Path p = Paths.get(PATH_IMAGENS + path);
				if(!Files.exists(p))
					Files.createDirectories(p);
				p = Paths.get(PATH_IMAGENS + path + fileName);
				Files.write(p, bytes);
				v.setFoto(path + fileName);
				vRepository.saveAndFlush(v);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return "redirect:/vantagem/";
	}
	
	@GetMapping("/editar")
	public String formularioEditar() {
		return "vantagem/Editar";
	}

	@PostMapping("/editar/{id}")
	public String editar(String produto, int valor, String descricao, @PathVariable long id) {
		Vantagem v = vRepository.findById(id).get();
		if (valor < 0)
			return "";
		
		v.setDescricao(descricao);
		v.setProduto(produto);
		v.setValor(valor);
		
		vRepository.save(v);

		return "redirect:/vantagem/";
	}
}
