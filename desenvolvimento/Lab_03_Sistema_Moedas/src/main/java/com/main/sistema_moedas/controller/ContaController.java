package com.main.sistema_moedas.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.main.sistema_moedas.model.Conta;
import com.main.sistema_moedas.model.Transacao;
import com.main.sistema_moedas.model.usuario.Aluno;
import com.main.sistema_moedas.model.usuario.Professor;
import com.main.sistema_moedas.model.usuario.Usuario;
import com.main.sistema_moedas.repository.ContaRepository;
import com.main.sistema_moedas.repository.TransacaoRepository;
import com.main.sistema_moedas.repository.UsuarioRepository;

@Controller
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private UsuarioRepository uRepository;
    @Autowired
    private TransacaoRepository tRepository;
    @Autowired
    private ContaRepository cRepository;


    @GetMapping("")
    public String conta() {
        return "conta/conta";
    }

    @GetMapping("/transferir")
    public ModelAndView transferirTela() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Professor p = (Professor) auth.getPrincipal();
        List<Aluno> alunos = uRepository.findByInstituicao(p.getInstituicao());
        ModelAndView mv = new ModelAndView("conta/transferir");
        mv.addObject("alunos", alunos);
        return mv;
    }

    @PostMapping("/transferencia")
    public String transferencia(Long alunoId, int qtdMoedas, String descricao) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Professor p = (Professor) auth.getPrincipal();
        Conta contaP = p.getConta();
        Conta contaA = ((Aluno) uRepository.findById(alunoId).get()).getConta();
        // if(!(a.getInstituicao().equals(p.getInstituicao()))) return "";

        if (!contaP.retirar(qtdMoedas))
            return "";
        contaA.adicionar(qtdMoedas);
        Transacao transacao = new Transacao();
        transacao.setContaDestino(contaA);
        transacao.setContaOrigem(contaP);
        transacao.setDescricao(descricao);
        transacao.setValor(qtdMoedas);
        transacao.setData(LocalDateTime.now());

        tRepository.save(transacao);
        cRepository.save(contaA);
        cRepository.save(contaP);

        return "redirect:/conta/transferir";
    }

    @GetMapping("/extrato")
    public ModelAndView consultaExtrato() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario user = (Usuario) auth.getPrincipal();
        List<Transacao> listaT;
        if (user instanceof Professor)
            listaT = tRepository.findByContaOrigem(((Professor) user).getConta());
        else
            listaT = tRepository.findByContaDestino(((Aluno) user).getConta());
        ModelAndView mv = new ModelAndView("conta/extrato");
        mv.addObject("extrato", listaT);
        return mv;
    }

}
