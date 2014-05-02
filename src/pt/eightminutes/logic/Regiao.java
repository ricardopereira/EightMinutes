/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.logic;

import java.io.Serializable;
import java.util.ArrayList;

public class Regiao implements Serializable{
    private String nome;
    private Continente continente;
    private ArrayList<Regiao> regioesVizinhas = new ArrayList<>();
    private ArrayList<Peca> pecas = new ArrayList<>();
    
    public Regiao(String nome, Continente continente){
        this.nome = nome;
        this.continente = continente;
    }
    
    public void adicionaRegiaoVizinha(ArrayList<Regiao> regioes){
        //fica assim até se decidir como definir quais são as regioes vizinhas
        for(int i=0;i<regioes.size();i++)
        {
            regioesVizinhas.add(regioes.get(i));
        }
    }
        
    public Jogador getJogadorControlaRegiao(Jogo jogo){
        int myQtdExerc=0;        
        int myQtdMax=0;
        Jogador jogadorControla=null;
        
        for(int m=0;m<jogo.getJogadores().size();m++){            
            myQtdExerc = 0;
            for(int i=0;i<getPecas().size();i++){
                if(getPecas().get(i) instanceof Exercito){
                    if(getPecas().get(i).getJogador() == jogo.getJogadores().get(m)){
                        myQtdExerc++;
                    }
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
     * @return the continente
     */
    public Continente getContinente() {
        return continente;
    }

    /**
     * @param continente the continente to set
     */
    public void setContinente(Continente continente) {
        this.continente = continente;
    }

    /**
     * @return the pecas
     */
    public ArrayList<Peca> getPecas() {
        return pecas;
    }

    /**
     * @param pecas the pecas to set
     */
    public void setPecas(ArrayList<Peca> pecas) {
        this.pecas = pecas;
    }

    /**
     * @return the regioesVizinhas
     */
    public ArrayList<Regiao> getRegioesVizinhas() {
        return regioesVizinhas;
    }

    /**
     * @param regioes the regioesVizinhas to set
     */
    public void setRegioesVizinhas(ArrayList<Regiao> regioesVizinhas) {
        this.regioesVizinhas = regioesVizinhas;
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
    
    @Override
    public String toString() {
        String s = "";
        s+=" "+getNome()+"\n";        
        return s;
    } 
}
