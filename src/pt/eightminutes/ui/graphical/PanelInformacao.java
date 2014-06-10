/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.ui.graphical;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 *
 * @author ricardopereira
 */
public class PanelInformacao extends JPanel {
    
    private DataController controller;
    
    public PanelInformacao(DataController controller) {
        this.controller = controller;
        
        // Teste
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.setBackground(new Color(219,219,219));
        this.setPreferredSize(new Dimension(300,500));
        this.setMinimumSize(new Dimension(300,500));
        this.setMaximumSize(new Dimension(300,500));
    }
    
}
