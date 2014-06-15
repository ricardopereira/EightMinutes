package pt.eightminutes.ia;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import pt.eightminutes.logic.Jogador;
import pt.eightminutes.ui.graphical.DataController;

public abstract class JogadorIA implements Serializable, Observer{
    
    DataController ctrl;
    Jogador jogadorIA;
   
    public JogadorIA(){
        
    }    
    
    public void setIA(Jogador jogador, DataController ctrl) {
        this.ctrl = ctrl;
        jogadorIA = jogador;
    }
        
    public abstract void update(Observable o, Object arg);
}
