/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.logic;

import java.io.Serializable;

public abstract class Peca implements Serializable{
    private String nome;
    private Regiao regiao;
    private Cor cor;

    public Peca(String nome,Regiao regiao,Cor cor){
        this.nome = nome;
        this.regiao = regiao;
        this.cor = cor;
    }
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the regiao
     */
    public Regiao getRegiao() {
        return regiao;
    }

    /**
     * @param regiao the regiao to set
     */
    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }
    
}
