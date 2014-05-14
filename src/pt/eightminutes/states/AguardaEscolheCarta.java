/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.states;

import pt.eightminutes.logic.Accao;
import pt.eightminutes.logic.AccaoColocaCidade;
import pt.eightminutes.logic.AccaoColocaExercito;
import pt.eightminutes.logic.AccaoDestroiExercito;
import pt.eightminutes.logic.AccaoMoveExercito;
import pt.eightminutes.logic.AccaoMoveExercitoAgua;
import pt.eightminutes.logic.Carta;
import pt.eightminutes.logic.Jogo;

public class AguardaEscolheCarta extends EstadosAdapter{

    public AguardaEscolheCarta(Jogo jogo) {
        super(jogo);
    }
        
    @Override
    public IEstados escolheCartas(int idx) {
        if (getJogo().validaNumCartasFinal()) {
            return new AguardaJokers(getJogo());
        } else {
            Carta cartaAux;
            
            // Verificar número de moedas
            if(getJogo().getCustoPorIdx(idx) <= getJogo().getJogadorActivo().getMoedas()) {
                // Compra da carta
                getJogo().compraCarta(idx);
                
                cartaAux = getJogo().getJogadorActivo().getCartaActiva();

                if (cartaAux.getAccoes().get(1) == null) {
                    getJogo().getJogadorActivo().getCartaActiva().setAccaoActiva(cartaAux.getAccoes().get(0));

                    if(cartaAux.getAccoes().get(0).getClass() == AccaoMoveExercito.class)
                        return new AguardaMoveExercito(getJogo());
                    else
                    if(cartaAux.getAccoes().get(0).getClass() == AccaoMoveExercitoAgua.class)
                        return new AguardaMoveExercitoAgua(getJogo());
                    else
                    if(cartaAux.getAccoes().get(0).getClass() == AccaoDestroiExercito.class)
                        return new AguardaDestroiExercito(getJogo());
                    else
                    if(cartaAux.getAccoes().get(0).getClass() == AccaoColocaExercito.class)
                        return new AguardaColocaExercito(getJogo());
                    else
                    if(cartaAux.getAccoes().get(0).getClass() == AccaoColocaCidade.class)
                        return new AguardaColocaCidade(getJogo());
                    else
                        return new AguardaEscolheAccao(getJogo());
                }
                else
                    return new AguardaEscolheAccao(getJogo());
            }
            else {
                if (Jogo.debugMode)
                    System.out.println("DEBUG: Sem moedas");
                return this;
            }
        }
    }
    
    @Override
    public IEstados verificaPontuacao() {
        if (getJogo().validaNumCartasFinal())
            return new AguardaJokers(getJogo());
        else
            return this;
    }
    
    @Override
    public IEstados gravaJogo() {
        return new AguardaOpcoesJogo(getJogo());
    }
    
    @Override
    public IEstados abandonaJogo() {
        // ToDo: Verificar com o Serrano
        //Abandonar jogo vai para as Opções ou para a Pontuação?
        return new AguardaOpcoesJogo(getJogo());
    }
    
    @Override
    public IEstados passaVez() {      
        if(getJogo().verificaJogadoresFimDoJogo())
            return new AguardaJokers(getJogo());
        else
            return this;
    }
}
