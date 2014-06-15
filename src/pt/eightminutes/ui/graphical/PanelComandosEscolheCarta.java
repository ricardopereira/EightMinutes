package pt.eightminutes.ui.graphical;

import java.awt.BorderLayout;
import javax.swing.JLabel;

public class PanelComandosEscolheCarta extends PanelBase {
    
    public PanelComandosEscolheCarta(PanelBase owner, DataController controller) {
        super(owner,controller);
        
        controller.setSelectedRegiao(null);
        controller.setFocusRegioes(null);
        
        this.add(new JLabel("Escolha uma das cartas viradas"), BorderLayout.CENTER);        
    }
    
}
