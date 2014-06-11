package pt.eightminutes.ui.graphical;

import java.awt.BorderLayout;
import javax.swing.JLabel;

public class PanelComandosEscolheCarta extends PanelBase {
    
    public PanelComandosEscolheCarta(PanelBase owner, DataController controller) {
        super(owner,controller);
        
        this.add(new JLabel("Escolha uma das cartas viradas"), BorderLayout.CENTER);        
    }
    
}
