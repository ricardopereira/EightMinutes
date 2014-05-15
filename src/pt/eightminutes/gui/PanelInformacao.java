/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.gui;

import javax.swing.JPanel;

/**
 *
 * @author ricardopereira
 */
public class PanelInformacao extends JPanel {
    
    private DataController controller;
    
    public PanelInformacao(DataController controller) {
        this.controller = controller;
    }
    
    @Override
    public int getWidth() {
        return 100;
    }
    
    @Override
    public int getHeight() {
        return 500;
    }
    
}
