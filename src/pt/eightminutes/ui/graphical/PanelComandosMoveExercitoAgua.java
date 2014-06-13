package pt.eightminutes.ui.graphical;

import java.awt.BorderLayout;
import javax.swing.JLabel;

public class PanelComandosMoveExercitoAgua extends PanelBase {
    
    public PanelComandosMoveExercitoAgua(PanelBase owner, DataController controller) {
        super(owner,controller);
        
        this.add(new JLabel("Acções Move Exercito Água"), BorderLayout.CENTER);
    }

}
