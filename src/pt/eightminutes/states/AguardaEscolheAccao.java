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
        if (accao != null)
        {            
            getJogo().getJogadorActivo().getCartaActiva().setAccaoActiva(accao);
            
            if (accao.getClass() == AccaoMoveExercito.class)
                return new AguardaMoveExercito(getJogo());
            else
            if (accao.getClass() == AccaoMoveExercitoAgua.class)
                return new AguardaMoveExercitoAgua(getJogo());
            else
            if (accao.getClass() == AccaoDestroiExercito.class)
                return new AguardaDestroiExercito(getJogo());
            else
            if (accao.getClass() == AccaoColocaExercito.class)
                return new AguardaColocaExercito(getJogo());
            else
            if (accao.getClass() == AccaoColocaCidade.class)
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
