/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.states;

import java.util.ArrayList;
import pt.eightminutes.logic.AccaoMoveExercito;
import pt.eightminutes.logic.Exercito;
import pt.eightminutes.logic.Jogo;
import pt.eightminutes.logic.Regiao;


public class AguardaMoveExercito extends EstadosAdapter{

    public AguardaMoveExercito(Jogo jogo) {
        super(jogo);
    }
 
    @Override
    public IEstados moveExercito(Regiao regiao, ArrayList<Exercito> exercitos) {
        // Executa acção da carta
        // ToDo: Validar com o Serrano
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(regiao);
        params.add(exercitos);
        getJogo().getJogadorActivo().getCartaActiva().getAccaoActiva().executa(getJogo(),params);
        
        // ToDo: Testar proximo passo
        
        // Próximo estado
        if (getJogo().getEstadoAnterior() instanceof AguardaEscolheAccao)
            return new AguardaEscolheAccao(getJogo());
        else
        {
            getJogo().mudaJogador();
            return new AguardaEscolheCarta(getJogo());
        }
    }
    
    @Override
    public IEstados moveExercitoCont(Regiao regiao, ArrayList<Exercito> exercitos) {         
        getJogo().getJogadorActivo().moveExercito(regiao, exercitos);
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
