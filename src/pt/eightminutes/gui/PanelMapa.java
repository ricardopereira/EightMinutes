/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author ricardopereira
 */
public class PanelMapa extends JPanel {
    
    private DataController controller;
    
    public PanelMapa(DataController controller) {
        this.controller = controller;
        
        // Teste
        this.setBackground(Color.YELLOW);
        this.setPreferredSize(new Dimension(600,400));
        this.setMinimumSize(new Dimension(600,400));
        this.setMaximumSize(new Dimension(600,400));
    }

}
