package pt.eightminutes.ia;

import pt.eightminutes.logic.Jogador;
import pt.eightminutes.ui.graphical.DataController;

public class IA {
    
    private static JogadorIABasico jogadorIA=new JogadorIABasico();
    
    public static void attachIA(Jogador jogador,DataController ctrl){
        ctrl.deleteObserver(jogadorIA);
        jogadorIA.setIA(jogador,ctrl);
    }
    
}
