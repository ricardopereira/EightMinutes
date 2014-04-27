/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.Estados;

import pt.eightminutes.logic.Jogo;


public class AguardaAposta extends AdaptadorEstados{

    public AguardaAposta(Jogo jogo) {
        super(jogo);
    }
    
    @Override
    public IEstados defineApostasJogadores(int numMoedas) {
        getJogo().apostaJogador(getJogo().getJogadorActivo(), numMoedas);       
        return this; 
    }

    @Override
    public IEstados comecaJogo() {        
        return new AguardaEscolheCarta(getJogo()); 
    }
    
}
