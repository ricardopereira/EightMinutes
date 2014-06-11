package pt.eightminutes.ui.graphical;

import java.awt.BorderLayout;
import javax.swing.JLabel;

public class PanelComandosEsperaAccao extends PanelBase {
    
    public PanelComandosEsperaAccao(PanelBase owner, DataController controller, String info) {
        super(owner,controller);
        
        this.add(new JLabel(info), BorderLayout.CENTER);
    }
    
}
