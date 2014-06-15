package pt.eightminutes.ui.graphical;

import java.io.IOException;
import pt.eightminutes.logic.Jogo;

public class IUGrafico {
    
    final private DataController controller;
    
    public IUGrafico(Jogo jogo) {
        controller = new DataController(jogo);
        
        // Testes
        Jogo.debugMode = true;
    }
   
    public void start() {
        System.out.println("Starting GUI...");
        new FrameMain(controller);
    }

}
