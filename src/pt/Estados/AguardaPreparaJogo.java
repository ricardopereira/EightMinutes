/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.Estados;

import pt.eightminutes.logic.Cor;
import pt.eightminutes.logic.Jogo;


public class AguardaPreparaJogo extends AdaptadorEstados{

    public AguardaPreparaJogo(Jogo jogo) {
        super(jogo);
    }
    
    @Override
    public IEstados defineNumJogadores(int numJogadores) {
        getJogo().setNumJogadores(numJogadores);
        return this; 
    }
    
    @Override
    public IEstados defineDadosJogadores(String nome, Cor cor) {
        getJogo().criaJogador(nome, cor);        
        return this; 
    }
    
    @Override
    public IEstados comecaApostas() {
        return new AguardaAposta(getJogo()); 
    }
    
}
