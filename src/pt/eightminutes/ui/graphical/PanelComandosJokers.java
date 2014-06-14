package pt.eightminutes.ui.graphical;

import java.awt.BorderLayout;
import javax.swing.JLabel;

public class PanelComandosJokers extends PanelBase {
    
    public PanelComandosJokers(PanelBase owner, DataController controller) {
        super(owner,controller);
                        
        this.add(new JLabel("Jokers"), BorderLayout.CENTER);
    }
    
}
