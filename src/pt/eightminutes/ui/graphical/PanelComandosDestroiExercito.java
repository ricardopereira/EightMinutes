package pt.eightminutes.ui.graphical;

import java.awt.BorderLayout;
import javax.swing.JLabel;

public class PanelComandosDestroiExercito extends PanelBase {
    
    public PanelComandosDestroiExercito(PanelBase owner, DataController controller) {
        super(owner,controller);
        
        this.add(new JLabel("Acções Destroi Exercito"), BorderLayout.CENTER);
    }

}
