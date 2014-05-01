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


public class AguardaMoveExercitoAgua extends EstadosAdapter{

    public AguardaMoveExercitoAgua(Jogo jogo) {
        super(jogo);
    }
    
    @Override
    public IEstados moveExercitoAgua(Regiao regiao, ArrayList<Exercito> exercitos) { 
        getJogo().getJogadorActivo().moveExercitoAgua(regiao, exercitos);
        if(getJogo().getEstadoAnterior() instanceof AguardaEscolheAccao)
            return new AguardaEscolheAccao(getJogo());
        else
        {
            getJogo().mudaJogador();
            return new AguardaEscolheCarta(getJogo());
        }        
    }
    
    @Override
    public IEstados moveExercitoAguaCont(Regiao regiao, ArrayList<Exercito> exercitos) { 
        getJogo().getJogadorActivo().moveExercitoAgua(regiao, exercitos);
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
