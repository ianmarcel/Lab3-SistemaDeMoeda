package com.main.sistema_moedas.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.main.sistema_moedas.model.usuario.Aluno;

public class Compra {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int valortotal;
    private Conta conta;
    private Vantagem vantagem;
    private Aluno aluno;
    public int getValortotal() {
        return valortotal;
    }
  
    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Vantagem getVantagem() {
        return vantagem;
    }

    public void setVantagem(Vantagem vantagem) {
        this.vantagem = vantagem;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
   
    
    public Conta getConta() {
        return conta;
    }
    public void setConta(Conta conta) {
        this.conta = conta;
    }
    public void setValortotal(int valortotal) {
        this.valortotal = valortotal;
    }
    public Compra(int valortotal,Conta conta,Vantagem vantagem,Aluno aluno){
         this.setValortotal(valortotal);
         this.setConta(conta);
         this.setVantagem(vantagem);
         this.setAluno(aluno);
    }

}
