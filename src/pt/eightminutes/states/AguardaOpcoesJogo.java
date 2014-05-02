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
    public IEstados retomarJogo() {
        try {
            setJogo(getJogo().carregaJogo());
        } catch (IOException ex) {
            Logger.getLogger(AguardaOpcoesJogo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AguardaOpcoesJogo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new AguardaEscolheCarta(getJogo());
    }

}
