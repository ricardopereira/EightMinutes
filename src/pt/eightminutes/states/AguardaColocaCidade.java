/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.states;

import pt.eightminutes.logic.Accao;
import pt.eightminutes.logic.Jogo;
import pt.eightminutes.logic.Regiao;

public class AguardaColocaCidade extends EstadosAdapter{
        
    public AguardaColocaCidade(Jogo jogo) {
        super(jogo);
    }
    
    @Override
    public IEstados colocaCidade(Regiao regiao) { 
        getJogo().getJogadorActivo().colocaCidade(regiao); 
        if(getJogo().getEstadoAnterior() instanceof AguardaEscolheAccao)
            return new AguardaEscolheAccao(getJogo());
        else
        {
            getJogo().mudaJogador();
            return new AguardaEscolheCarta(getJogo());
        }
    }
    
    @Override
    public IEstados abandonaJogo() {
        return new AguardaFinalJogo(getJogo()); 
    }
    
    @Override
    public IEstados passaVez() {                
        return new AguardaEscolheCarta(getJogo()); 
    }
    
}
