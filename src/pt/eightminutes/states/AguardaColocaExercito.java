/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.states;

import java.util.ArrayList;
import pt.eightminutes.logic.Exercito;
import pt.eightminutes.logic.Jogo;
import pt.eightminutes.logic.Regiao;
import pt.eightminutes.logic.AccaoColocaExercito;


public class AguardaColocaExercito extends EstadosAdapter{

    public AguardaColocaExercito(Jogo jogo) {
        super(jogo);
    }
    
    
    @Override
    public IEstados colocaExercito(Regiao regiao, ArrayList<Exercito> exercitos) {         
        getJogo().getJogadorActivo().colocaExercito(regiao, exercitos);  
        if(getJogo().getEstadoAnterior() instanceof AguardaEscolheAccao)
            return new AguardaEscolheAccao(getJogo());
        else
        {
            getJogo().mudaJogador();
            return new AguardaEscolheCarta(getJogo());
        }
    }
    
    @Override
    public IEstados colocaExercitoCont(Regiao regiao, ArrayList<Exercito> exercitos) {         
        getJogo().getJogadorActivo().colocaExercito(regiao, exercitos);  
        return this;
    }        
    
    @Override
    public IEstados abandonaJogo() {
        return new AguardaFinalJogo(getJogo()); 
    }
    
    @Override
    public IEstados passaVez() {                
        return new AguardaEscolheCarta(getJogo()); 
    }
    
    @Override
    public IEstados mudaAccao() {                
        return new AguardaEscolheAccao(getJogo()); 
    }
}
