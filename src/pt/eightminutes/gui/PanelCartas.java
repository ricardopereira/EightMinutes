/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author ricardopereira
 */
public class PanelCartas extends JPanel {
    
    private DataController controller;
    
    public PanelCartas(DataController controller) {
        this.controller = controller;
        
        // Teste
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Color.ORANGE);
        this.setPreferredSize(new Dimension(600,100));
        this.setMinimumSize(new Dimension(600,100));
        this.setMaximumSize(new Dimension(600,100));
        
        createCartas();
    }
    
    protected static Image createImage(String path) {

        BufferedImage imagem = null;
        try {
            imagem = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println(" erro ao carregar as imagens");
        }
        return imagem;
    }
    
    public void createCartas() {
        // Teste
        JButton btCarta1 = new JButton();
        
        // Exemplo de carta
        btCarta1.setMargin(new Insets(0,0,0,0));
        btCarta1.setContentAreaFilled(false);
        btCarta1.setFocusPainted(false);
        btCarta1.setBorder(BorderFactory.createEmptyBorder());
        btCarta1.setIcon(new ImageIcon(createImage("src/pt/eightminutes/cards/Group.gif")));
        
        this.add(btCarta1);
        this.add(new JButton("Carta 2"));
        this.add(new JButton("Carta 3"));
        this.add(new JButton("Carta 4"));
        this.add(new JButton("Carta 5"));
        this.add(new JButton("Carta 6"));
    }

}
