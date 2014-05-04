/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.states;

import pt.eightminutes.logic.Carta;
import pt.eightminutes.logic.Jogo;
import pt.eightminutes.logic.Recurso;

public class AguardaJokers extends EstadosAdapter{

    public AguardaJokers(Jogo jogo) {
        super(jogo);
    }
    
    
    @Override
    public IEstados defineRecurso(Carta carta, Recurso recurso) { 
        carta.setRecurso(recurso);
        return this; 
    }
    
    @Override
    public IEstados mostraPontuacao() {         
        return new AguardaOpcoesJogo(getJogo()); 
    }
}
