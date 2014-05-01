/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.isec;

import pt.eightminutes.tui.IUTexto;
import java.io.IOException;
import pt.eightminutes.logic.*;


public class EightMinutes {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        Jogo jogo = new Jogo();       
        IUTexto iuTexto = new IUTexto(jogo);
        iuTexto.iniciaJogo();
        while(jogo.getEstadoActual() != null)
        {
           iuTexto.executaInterface();
        }
    }
    
}
