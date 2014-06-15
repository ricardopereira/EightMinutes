/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.states;

import pt.eightminutes.logic.Jogador;
import pt.eightminutes.logic.Jogo;


public class AguardaAposta extends EstadosAdapter{

    public AguardaAposta(Jogo jogo) {
        super(jogo);
    }
    
    @Override
    public IEstados defineApostasJogadores(Jogador jogador,int numMoedas) {
        boolean faltaApostar = false;
        getJogo().apostaJogador(jogador, numMoedas);    
        for(int i=0;i<getJogo().getJogadores().size();i++)
        {
            if(getJogo().getJogadores().get(i).getAposta() == -1)
            {
                faltaApostar = true;
                break;
            }
        }
        
        if (faltaApostar)
        {
            getJogo().passaVez();
            // Verificação por causa do IA
            if (getJogo().getEstadoActual().getClass() != AguardaEscolheCarta.class)
                return this;
            else
                // Manter o estado atual
                return getJogo().getEstadoActual();
        }
        else       
        {            
            getJogo().comecaJogo();
            // Verificação por causa do IA
            if (getJogo().getEstadoActual().getClass() != AguardaEscolheCarta.class)
                return new AguardaEscolheCarta(getJogo());
            else
                // Manter o estado atual
                return getJogo().getEstadoActual();
        }        
    }

    @Override
    public IEstados comecaJogo() {
        return new AguardaEscolheCarta(getJogo()); 
    }
    
}
