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
        ArrayList<Object> params = new ArrayList<>();
        params.add(regiao);
        params.add(exercitos);
        getJogo().getJogadorActivo().getCartaActiva().getAccaoActiva().executa(getJogo(),params);
        
        // ToDo: Testar proximo passo
        
        // Próximo estado
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
    
    @Override
    public IEstados passaVez() {                
        return new AguardaEscolheCarta(getJogo()); 
    }
    
    @Override
    public IEstados mudaAccao() {                
        return new AguardaEscolheAccao(getJogo()); 
    } 
}
