/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.states;

import java.util.ArrayList;
import pt.eightminutes.logic.Accao;
import pt.eightminutes.logic.Exercito;
import pt.eightminutes.logic.Jogo;


public class AguardaDestroiExercito extends EstadosAdapter{
    private Object Exercito;

    public AguardaDestroiExercito(Jogo jogo) {
        super(jogo);
    }
    
    
    @Override
    public IEstados destroiExercito(Exercito exercito) { 
        ArrayList<Object> params = new ArrayList<>();        
        params.add(exercito);
        Accao accao = getJogo().getJogadorActivo().getCartaActiva().getAccaoActiva();
        if(accao==null)
            return this;
        
        accao.executa(getJogo(),params);
        //Próximo passo
        if(!accao.isUsada())
            return this;
        else
        if (getJogo().getEstadoAnterior().getClass() == AguardaEscolheAccao.class)
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
