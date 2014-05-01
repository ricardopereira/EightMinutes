/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.states;

import pt.eightminutes.logic.*;

public class AguardaEscolheAccao extends EstadosAdapter{

    public AguardaEscolheAccao(Jogo jogo) {
        super(jogo);
    }
   
    @Override
    public IEstados escolheAccao(Accao accao) {                
        if(accao!=null)
        {            
            getJogo().getJogadorActivo().getCartaActiva().setAccaoActiva(accao);
            
            if(accao instanceof AccaoMoveExercito)
                return new AguardaMoveExercito(getJogo());
            else
            if(accao instanceof AccaoMoveExercitoAgua)
                return new AguardaMoveExercitoAgua(getJogo());
            else
            if(accao instanceof AccaoDestroiExercito)
                return new AguardaDestroiExercito(getJogo());
            else
            if(accao instanceof AccaoColocaExercito)
                return new AguardaColocaExercito(getJogo());
            else
            if(accao instanceof AccaoColocaCidade)
                return new AguardaColocaCidade(getJogo());
            else               
                return this;
        }
        else
            return this;
    }
    
    @Override
    public IEstados passaVez() {                
        return new AguardaEscolheCarta(getJogo()); 
    }
}
