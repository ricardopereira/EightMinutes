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
    private Mapa mapa;
    
    public Continente(String nome, Mapa mapa){        
        this.nome = nome; 
        this.mapa = mapa;
    }
   
    public Regiao adicionaRegiao(Regiao regiao){
        getRegioes().add(regiao);
        return regiao;
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
    
    public void carregaListaRegioesComExercitosPorJogador(Mapa mapa,Jogador jogador,ArrayList<Regiao> regioesAux,boolean addRegiaoInicial){
        if (regioesAux == null)
            regioesAux = new ArrayList<>();
            
        for(int i=0;i<this.getRegioes().size();i++){               
            for(int m=0;m<this.getRegioes().get(i).getPecas().size();m++){
                if(this.getRegioes().get(i).getContinente() == this){
                    if(this.getRegioes().get(i).getPecas().get(m).getJogador()==jogador){                        
                        if(regioesAux.indexOf(this.getRegioes().get(i))==-1){
                            if(addRegiaoInicial)                           
                                regioesAux.add(this.getRegioes().get(i));                
                            else
                            {
                                if(this.getMapa().getRegiaoInicial()!=this.getRegioes().get(i))
                                    regioesAux.add(this.getRegioes().get(i));
                            }
                        }    
                    } 
                }
            }           
        }
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

    /**
     * @return the mapa
     */
    public Mapa getMapa() {
        return mapa;
    }

    /**
     * @param mapa the mapa to set
     */
    private void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }
}
