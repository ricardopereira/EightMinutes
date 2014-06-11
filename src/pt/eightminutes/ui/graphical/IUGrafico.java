/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.ui.graphical;

import java.io.IOException;
import pt.eightminutes.logic.Jogo;

/**
 *
 * @author ricardopereira
 */
public class IUGrafico {
    
    final private DataController controller;
    
    public IUGrafico(Jogo jogo) {
        controller = new DataController(jogo);
    }
   
    public void start() {
        System.out.println("Starting GUI...");
        new FrameMain(controller);
    }

}
