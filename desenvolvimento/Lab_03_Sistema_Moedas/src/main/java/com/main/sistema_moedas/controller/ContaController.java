package com.main.sistema_moedas.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.main.sistema_moedas.repository.CompraRepository;
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
    @Autowired
    private CompraRepository coRepository;

    private boolean isAluno;
    private List<Transacao> listaT;
    private Usuario user;

    @GetMapping("")
    public ModelAndView conta() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView mv = new ModelAndView("conta/conta");
        validate(auth);
        mv.addObject("isAluno", isAluno)
                .addObject("conta", isAluno ? ((Aluno) user).getConta() : ((Professor) user).getConta())
                .addObject("user", user);
        return mv;
    }

    @GetMapping("/transferir")
    public ModelAndView transferirTela() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Professor p = (Professor) auth.getPrincipal();
        List<Aluno> alunos = uRepository.findByInstituicao(p.getInstituicao());
        ModelAndView mv = new ModelAndView("conta/transferir");
        mv.addObject("alunos", alunos);
        mv.addObject("conta", p.getConta());
        return mv;
    }

    @PostMapping("/transferencia")
    public ModelAndView transferencia(Long alunoId, int qtdMoedas, String descricao) {
        ModelAndView mv = transferirTela();
        if (qtdMoedas < 1 || alunoId==null || descricao==null)
            return mv.addObject("erro", 0);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Professor p = (Professor) auth.getPrincipal();
        Conta contaP = p.getConta();
        Conta contaA = ((Aluno) uRepository.findById(alunoId).get()).getConta();
        if (!contaP.retirar(qtdMoedas))
            mv.addObject("erro", 1);
        else {
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
            mv.addObject("aluno", uRepository.findAlunoByConta(contaA).get())
                    .addObject("qtd", qtdMoedas);
        }
        return mv;
    }

    @GetMapping("/extrato")
    public ModelAndView consultaExtrato() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        validate(auth);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL u, HH:mm:ss");
        ModelAndView mv = new ModelAndView("conta/extrato");
        mv.addObject("extrato", listaT);
        mv.addObject("isAluno", isAluno);
        mv.addObject("ur", uRepository);
        mv.addObject("formatter", formatter);
        mv.addObject("now", LocalDateTime.now());
        mv.addObject("conta", isAluno ? ((Aluno) user).getConta() : ((Professor) user).getConta());
        if (isAluno)
        	mv.addObject("compras", coRepository.findByAluno((Aluno) user));
        return mv;
    }

    public void validate(Authentication auth) {
        user = (Usuario) auth.getPrincipal();
        if (user instanceof Professor) {
            listaT = tRepository.findByContaOrigem(((Professor) user).getConta());
            isAluno = false;
        } else {
            listaT = tRepository.findByContaDestino(((Aluno) user).getConta());
            isAluno = true;
        }
    }

}
