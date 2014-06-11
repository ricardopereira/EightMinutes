package pt.eightminutes.ui.graphical;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JPanel;
import pt.eightminutes.logic.Jogo;

public class PanelBase extends JPanel {
    
    private DataController controller;
    private PanelBase owner;
    
    public PanelBase(PanelBase owner, DataController controller) {
        this.controller = controller;
        this.owner = owner;
    }

    public DataController getController() {
        return controller;
    }
    
    public PanelBase getOwner() {
        return owner;
    }
    
    public Jogo getJogo() {
        return controller.getJogo();
    }
    
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        for (Component component : getComponents())
            component.setEnabled(enabled);
    }
    
    public void showThis(PanelBase panel) {
        panel.setBackground(this.getBackground());
        add(panel, BorderLayout.CENTER);
        validate();
    }
    
    public void clear() {
        removeAll();
        validate();
        repaint();
    }
    
}
