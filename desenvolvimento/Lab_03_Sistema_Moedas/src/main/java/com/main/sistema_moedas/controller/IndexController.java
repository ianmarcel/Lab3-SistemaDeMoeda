package com.main.sistema_moedas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/cadastrarusuario")
    
    public String formularioCadastro() {
    	return "formulario";
    }
    @GetMapping("/logar")
    public String Login() {
    	return "formularioLogin";
    }
}