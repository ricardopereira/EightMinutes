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

    public Jogador getJogadorControlaContinente(Jogo jogo){
        int myQtdExerc=0;        
        int myQtdMax=0;
        Jogador jogadorControla=null;
        
        for(int m=0;m<jogo.getJogadores().size();m++){            
            myQtdExerc = 0;
            for(int i=0;i<getRegioes().size();i++){
                if(getRegioes().get(i).getJogadorControlaRegiao(jogo) == jogo.getJogadores().get(m)){
                    myQtdExerc++;
                }
            }
            if (myQtdExerc>myQtdMax){
                // se tiver mais do que o ultimo verificado passa a ser o jogador que controla a regiao
                jogadorControla = jogo.getJogadores().get(m);
            }
            else
            if(myQtdExerc==myQtdMax){
                //se tiver o mesmo numero do jogador que controla deixam os dois de controlar
                jogadorControla = null;
            }
        }
        
        return jogadorControla;
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
