/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.states;

import pt.eightminutes.logic.Exercito;
import pt.eightminutes.logic.Jogo;


public class AguardaDestroiExercito extends EstadosAdapter{

    public AguardaDestroiExercito(Jogo jogo) {
        super(jogo);
    }
    
    
    @Override
    public IEstados destroiExercito(Exercito exercito) { 
        getJogo().getJogadorActivo().destroiExercito(exercito);
        if(getJogo().getEstadoAnterior().getClass() == AguardaEscolheAccao.class)
            return new AguardaEscolheAccao(getJogo());
        else
        {
            getJogo().mudaJogador();
            return new AguardaEscolheCarta(getJogo());
        }
    }
    
    @Override
    public IEstados abandonaJogo() {
        // ToDo: Verificar com o Serrano
        //Abandonar jogo vai para as Opções ou para a Pontuação?
        return new AguardaOpcoesJogo(getJogo());
    }
}
