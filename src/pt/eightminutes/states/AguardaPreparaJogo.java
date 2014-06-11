/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.states;

import pt.eightminutes.logic.Jogo;


public class AguardaPreparaJogo extends EstadosAdapter {

    public AguardaPreparaJogo(Jogo jogo) {
        super(jogo);
    }
    
    @Override
    public IEstados defineNumJogadores(int numJogadores) {
        getJogo().setNumJogadores(numJogadores);
        return this; 
    }
    
    @Override
    public IEstados defineDadosJogadores(String nome) {
        getJogo().criaJogador(nome);
        return this; 
    }
    
    @Override
    public IEstados comecaApostas() {
        return new AguardaAposta(getJogo()); 
    }
    
}
