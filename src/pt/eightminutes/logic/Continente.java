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
    
    public void carregaListaRegioesComExercitosPorJogador(Jogador jogador,ArrayList<Regiao> regioesAux,boolean addRegiaoInicial){
        Regiao regiao=null;
        if (regioesAux == null)
            regioesAux = new ArrayList<>();
        
        //Lista de exercitos do jogador
        for(int i=0;i< jogador.getListaExercitoComRegiao().size();i++){
            regiao = jogador.getListaExercitoComRegiao().get(i).getRegiao();
            //verifica se a regiao pertence ao continente
            if(regiao.getContinente() == this){
                //verifica se ja existe na lista
                if(regioesAux.indexOf(regiao)==-1){
                    if(addRegiaoInicial)                           
                        regioesAux.add(regiao);                
                    else
                    {
                        //Adiciona se for diferente da regiao inicial
                        if(this.getMapa().getRegiaoInicial()!=regiao)
                            regioesAux.add(regiao);
                        //retirar se ja tiver uma cidade
                        for (Cidade itemCidade : jogador.getListaCidadeComRegiao()) {
                            if(regiao==itemCidade.getRegiao())
                                regioesAux.remove(regiao);
                        }
                    }
                }
            }
        }        
    }
    
    public boolean temExercitosDoJogador(Jogador jogador, boolean addRegiaoComCidade) {
        boolean myTemCidade=true;
        if (jogador == null) return false;
        //Listar Exercitos com regiao
        for (Exercito item : jogador.getListaExercitoComRegiao()) {
            // verifica se não é null e se o continente é este
            if (item.getRegiao() != null && item.getRegiao().getContinente() == this)
                if(!addRegiaoComCidade){
                    //verifica se tem alguma cidade                   
                                                                                 
                    for (Cidade itemCidade : jogador.getListaCidadeComRegiao()) {
                        if(item.getRegiao()==itemCidade.getRegiao())
                           myTemCidade = false; 
                    }
                    
                    if(myTemCidade){
                        if(item.getRegiao()==this.getMapa().getRegiaoInicial())                                               
                            myTemCidade = false;     
                    }    
                    
                    return myTemCidade;
                }
                else
                    return true;
        }
        return false;
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
