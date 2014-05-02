/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.states;

import java.util.ArrayList;
import pt.eightminutes.logic.Accao;
import pt.eightminutes.logic.Carta;
import pt.eightminutes.logic.Cor;
import pt.eightminutes.logic.Exercito;
import pt.eightminutes.logic.Jogo;
import pt.eightminutes.logic.Regiao;

public class EstadosAdapter implements IEstados {
    
    private Jogo jogo;
    
    public EstadosAdapter(Jogo jogo){
        this.jogo = jogo;
    }   

    /**
     * @return the jogo
     */
    public Jogo getJogo() {
        return jogo;
    }

    /**
     * @param jogo the jogo to set
     */
    public void setJogo(Jogo jogo) {
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
    public IEstados colocaExercito(Regiao regiao, ArrayList<Exercito> exercitos) { return this; }

    @Override
    public IEstados colocaExercitoCont(Regiao regiao, ArrayList<Exercito> exercitos) { return this; }
    
    @Override
    public IEstados moveExercito(Regiao regiao, ArrayList<Exercito> exercitos) { return this; }
    
    @Override
    public IEstados moveExercitoCont(Regiao regiao, ArrayList<Exercito> exercitos) { return this; }

    @Override
    public IEstados destroiExercito(Exercito exercito) { return this; }

    @Override
    public IEstados moveExercitoAgua(Regiao regiao, ArrayList<Exercito> exercitos) { return this; }
    
    @Override
    public IEstados moveExercitoAguaCont(Regiao regiao, ArrayList<Exercito> exercitos) { return this; }

    @Override
    public IEstados passaVez() { return this; }
    
    @Override
    public IEstados mudaAccao() { return this; }
    
    @Override
    public IEstados novoJogo() { return this; };
    
    @Override
    public IEstados retomarJogo() { return this; }
    
    @Override
    public IEstados gravaJogo() { return this; }
    
    @Override
    public IEstados abandonaJogo() { return this; }
}
