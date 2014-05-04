/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.states;

import pt.eightminutes.logic.Jogador;
import pt.eightminutes.logic.Jogo;


public class AguardaAposta extends EstadosAdapter{

    public AguardaAposta(Jogo jogo) {
        super(jogo);
    }
    
    @Override
    public IEstados defineApostasJogadores(Jogador jogador,int numMoedas) {
        getJogo().apostaJogador(jogador, numMoedas);       
        return this; 
    }

    @Override
    public IEstados comecaJogo() { 
        return new AguardaEscolheCarta(getJogo()); 
    }
    
}
