/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.ui.graphical;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JPanel;
import pt.eightminutes.logic.Jogo;

/**
 *
 * @author ricardopereira
 */
public class PanelBase extends JPanel {
    
    private DataController controller;
    
    public PanelBase(DataController controller) {
        this.controller = controller;
    }

    public DataController getController() {
        return controller;
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
        add(panel, BorderLayout.CENTER);
        validate();
    }
    
    public void clear() {
        removeAll();
        validate();
        repaint();
    }
    
}
