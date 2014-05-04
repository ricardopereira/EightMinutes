/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.states;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import pt.eightminutes.logic.*;

public class EstadosAdapter implements IEstados, Serializable {
    
    private Jogo jogo;
    
    public EstadosAdapter(Jogo jogo){
        this.jogo = jogo;
    }   

    @Override
    public Jogo getJogo() {
        return jogo;
    }

    /**
     * @param jogo the jogo to set
     */
    public void setJogo(Jogo jogo) {
        if (jogo == null) return;
        if (jogo == this.jogo) return;
        this.jogo = jogo;
    }

    @Override
    public IEstados defineNumJogadores(int numJogadores) { return this; }

    @Override
    public IEstados defineDadosJogadores(String nome, Cor cor) {  return this; }

    @Override
    public IEstados comecaApostas() {  return this; }

    @Override
    public IEstados defineApostasJogadores(int numMoedas) {  return this; }

    @Override
    public IEstados comecaJogo() { return this; }
    
    @Override
    public IEstados escolheCartas(int idx) { return this; }
    
    @Override
    public IEstados verificaPontuacao() { return this; }

    @Override
    public IEstados compraCarta() { return this; }
    
    @Override
    public IEstados escolheAccao(Accao accao) { return this; }

    @Override
    public IEstados colocaCidade(Regiao regiao) { return this; }

    @Override
    public IEstados colocaExercito(Regiao regiao, Integer qtd) { return this; }    
    
    @Override
    public IEstados moveExercito(Regiao regiao, ArrayList<Exercito> exercitos) { return this; }
    
    @Override
    public IEstados destroiExercito(Exercito exercito) { return this; }

    @Override
    public IEstados moveExercitoAgua(Regiao regiao, ArrayList<Exercito> exercitos) { return this; }

    @Override
    public IEstados passaVez() { return this; }
    
    @Override
    public IEstados mudaAccao() { return this; }
    
    @Override
    public IEstados novoJogo() { return this; };
    
    @Override
    public IEstados carregaJogo() throws FileNotFoundException, IOException { return this; }
    
    @Override
    public IEstados gravaJogo() { return this; }
    
    @Override
    public IEstados abandonaJogo() { return this; }
}
