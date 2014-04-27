/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.logic;

import java.io.Serializable;
import java.util.ArrayList;

public class Continente implements Serializable{    
    private String nome;
    private ArrayList<Regiao> regioes = new ArrayList<>();
    
    public Continente(String nome){        
        this.nome = nome; 
    }
   
    
    public void adicionaRegiao(Regiao regiao){
        getRegioes().add(regiao);       
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
     * @return the qtdRegioes
     */
    public int getQtdRegioes() {
        return regioes.size();
    }


    /**
     * @return the regioes
     */
    public ArrayList<Regiao> getRegioes() {
        return regioes;
    }

    /**
     * @param regioes the regioes to set
     */
    public void setRegioes(ArrayList<Regiao> regioes) {
        this.regioes = regioes;
    }
    
    @Override
    public String toString() {
        String s = "";
        s+=" "+getNome()+"\n";        
        return s;
    } 
}
