package com.main.sistema_moedas.model;

import javax.persistence.*;

import com.main.sistema_moedas.model.usuario.Aluno;

@Entity
public class Compra {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int valor;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Vantagem vantagem;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Aluno aluno;

    public Compra(int valortotal, Aluno aluno,Vantagem vantagem){
        this.setValor(valortotal);
        this.setAluno(aluno);
        this.setVantagem(vantagem);
    }

    public int getValor() {
        return valor;
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

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
