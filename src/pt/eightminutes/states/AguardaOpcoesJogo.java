/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.states;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.eightminutes.logic.Jogo;

public class AguardaOpcoesJogo extends EstadosAdapter {

    public AguardaOpcoesJogo(Jogo jogo) {
        super(jogo);
    }
    
    @Override
    public IEstados novoJogo() {
        return new AguardaPreparaJogo(getJogo());
    }
    
    @Override
    public IEstados carregaJogo() {
        try {
            setJogo(getJogo().carregaInstanciaJogo());
        } catch (IOException | ClassNotFoundException ex) {
            // ToDo: Deverá mostrar erros no interface
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        // Como o jogo foi gravado com o estado OpçõesJogo, é preciso forçar o novo estado
        getJogo().setEstadoActual(new AguardaEscolheCarta(getJogo()));
        // Retorno não interessa porque o Jogo vai ser substituído
        return this;
    }
    
    @Override
    public IEstados gravaJogo() {
        try {
            getJogo().gravaInstanciaJogo();
        } catch (IOException ex) {
            // ToDo: Deverá mostrar erros no interface
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);            
        }
        // Fica onde estava
        return this;
    }

}
