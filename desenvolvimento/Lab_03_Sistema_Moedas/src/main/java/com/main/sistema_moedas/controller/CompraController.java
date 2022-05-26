package com.main.sistema_moedas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.main.sistema_moedas.model.*;
import com.main.sistema_moedas.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.main.sistema_moedas.model.usuario.Aluno;
import com.main.sistema_moedas.model.usuario.Role;
import com.main.sistema_moedas.model.usuario.Usuario;

@Controller
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private CompraRepository compraRepository;
    @Autowired VantagemRepository vantagemRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping("/new/{id}")
    public String novo(@PathVariable long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Aluno aluno = ((Aluno) auth.getPrincipal());
        Vantagem v = vantagemRepository.findById(id).orElse(null);
        Conta conta = aluno.getConta();
        if((v == null) || (conta.getSaldo() < v.getValor())) return "redirect:/vantagem/listar";
        Compra compra = new Compra(v.getValor(), aluno, v);
        conta.retirar(v.getValor());
        compraRepository.save(compra);
        contaRepository.save(conta);
        return "redirect:/vantagem/listar";
    }
}
