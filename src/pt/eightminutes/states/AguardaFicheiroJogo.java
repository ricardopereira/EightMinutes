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

public class AguardaFicheiroJogo extends EstadosAdapter{

    public AguardaFicheiroJogo(Jogo jogo) {
        super(jogo);
    }
    
    @Override
    public IEstados retomarJogo() { 
        try {
            setJogo(getJogo().carregaJogo());
        } catch (IOException ex) {
            Logger.getLogger(AguardaFicheiroJogo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AguardaFicheiroJogo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new AguardaEscolheCarta(getJogo());           
    }        
    
}
