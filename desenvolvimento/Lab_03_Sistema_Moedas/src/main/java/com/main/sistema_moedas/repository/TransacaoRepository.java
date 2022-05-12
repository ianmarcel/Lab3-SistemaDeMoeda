package com.main.sistema_moedas.repository;

import com.main.sistema_moedas.model.Conta;
import com.main.sistema_moedas.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    public List<Transacao> findByContaOrigem(Conta conta);
    public List<Transacao> findByContaDestino(Conta conta);
}
