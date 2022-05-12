package com.main.sistema_moedas.controller;
import java.time.LocalDateTime;
import java.util.List;

import com.main.sistema_moedas.model.Transacao;
import com.main.sistema_moedas.model.usuario.Professor;
import com.main.sistema_moedas.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.main.sistema_moedas.model.Conta;
import com.main.sistema_moedas.model.Endereco;
import com.main.sistema_moedas.model.Instituicao;
import com.main.sistema_moedas.model.usuario.Aluno;
import com.main.sistema_moedas.model.usuario.Role;
import com.main.sistema_moedas.model.usuario.Usuario;
import com.main.sistema_moedas.repository.InstituicaoRepository;
import com.main.sistema_moedas.repository.UsuarioRepository;

@Controller
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private UsuarioRepository uRepository;
    @Autowired
    private InstituicaoRepository iRepository;
    @Autowired
    private TransacaoRepository tRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping("/transferir")
    public ModelAndView transferirTela() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Professor p = (Professor) auth.getPrincipal();
        List<Aluno> alunos = uRepository.findByInstituicao(p.getInstituicao());
        ModelAndView mv = new ModelAndView("transferir");
        mv.addObject("alunos", alunos);
        return mv;
    }

    @PostMapping("/transferencia")
    public String transferencia(Long alunoId, int qtdMoedas, String descricao) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Professor p = (Professor) auth.getPrincipal();
        Conta contaP = p.getConta();
        Conta contaA = ((Aluno) uRepository.findById(alunoId).get()).getConta();
        //if(!(a.getInstituicao().equals(p.getInstituicao()))) return "";

        if(!contaP.retirar(qtdMoedas)) return "";
        contaA.adicionar(qtdMoedas);
        Transacao transacao = new Transacao();
        transacao.setDestino(contaA);
        transacao.setOrigem(contaP);
        transacao.setDescricao(descricao);
        transacao.setValor(qtdMoedas);
        transacao.setData(LocalDateTime.now());

        return "redirect:/transferir";
    }

    @GetMapping("/extrato")
    public ModelAndView consultaExtrato() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario user = (Usuario) auth.getPrincipal();
        List<Transacao> listaT;
        if(user instanceof Professor)
            listaT = tRepository.findByOrigem(((Professor) user).getConta());
        else
            listaT = tRepository.findByDestino(((Aluno) user).getConta());
        ModelAndView mv = new ModelAndView("/extrato");
        mv.addObject("extrato", listaT);
        return mv;
    }

}
