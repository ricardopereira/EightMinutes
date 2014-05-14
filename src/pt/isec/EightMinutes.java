/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.isec;

import pt.eightminutes.tui.IUTexto;
import pt.eightminutes.gui.*;
import java.io.IOException;
import pt.eightminutes.logic.*;

public class EightMinutes {
    
    public static void main(String[] args) throws IOException, InterruptedException {

        modoGrafico();
    }
    
    public static void modoGrafico()
    {
        IUGrafico iuGrafico = new IUGrafico(new Jogo());
        iuGrafico.start();
    }
    
    public static void modoTexto()
    {
        // Fase de Desenvolvimento
        Jogo.debugMode = false;
        Jogo.debugShowMapa = false;
        Jogo.debugShowMapaTrajectos = false;
        
        IUTexto iuTexto = new IUTexto(new Jogo());
        // Start
        iuTexto.iniciaInterface();
        while (iuTexto.getJogo().getEstadoActual() != null)
        {
            try {
                iuTexto.executaInterface();
            }
            catch (InterruptedException e) {
                System.err.print(e.getMessage());
            }
            catch (IOException e) {
                System.err.print(e.getMessage());                
            }
        }        
    }
    
}
