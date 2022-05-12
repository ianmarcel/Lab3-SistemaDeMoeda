package com.main.sistema_moedas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.main.sistema_moedas.model.usuario.Professor;
import com.main.sistema_moedas.model.usuario.Usuario;
import com.main.sistema_moedas.repository.UsuarioRepository;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private UsuarioRepository uRepository;

    @GetMapping("")
    public ModelAndView homeProfessor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Professor professor = (Professor) auth.getPrincipal();
        ModelAndView mv = new ModelAndView("professor/professor");
        mv.addObject("professor", professor);
        mv.addObject("conta", professor.getConta());
        return mv;
    }

    // @GetMapping("/editar")
    // public ModelAndView editaAluno() {
    // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    // Usuario user = (Usuario) auth.getPrincipal();
    // ModelAndView mv = new ModelAndView("aluno/editar");
    // mv.addObject("aluno", ((Aluno) user));
    // mv.addObject("end", user.getEndereco());
    // return mv;
    // }

    // @PostMapping("/editar")
    // public String updateAluno(@Validated Aluno aluno, Endereco end, BindingResult
    // result) {
    // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    // Aluno user = ((Aluno) auth.getPrincipal());
    // Long id = user.getId();
    // String senha = user.getSenha();
    // List<Role> roles = user.getRoles();
    // if (result.hasErrors()) {
    // return "aluno/editar";
    // }
    // aluno.setId(id);
    // aluno.setSenha(senha);
    // aluno.setEndereco(end);
    // aluno.setRoles(roles);
    // uRepository.save(aluno);
    // return "redirect:/login";
    // }

    @GetMapping("/deletar")
    public String deletarUsuario() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario user = (Usuario) auth.getPrincipal();
        uRepository.delete(user);
        auth.setAuthenticated(false);
        return "redirect:/logout";
    }
}
