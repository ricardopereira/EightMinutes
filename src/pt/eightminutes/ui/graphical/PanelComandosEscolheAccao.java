package pt.eightminutes.ui.graphical;

import java.awt.BorderLayout;
import javax.swing.JLabel;

public class PanelComandosEscolheAccao extends PanelBase {
    
    public PanelComandosEscolheAccao(PanelBase owner, DataController controller) {
        super(owner,controller);
        
        this.add(new JLabel("Acções possíveis da carta"), BorderLayout.CENTER);
    }

}
