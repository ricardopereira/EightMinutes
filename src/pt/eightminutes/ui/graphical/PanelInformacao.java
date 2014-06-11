/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.ui.graphical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import pt.eightminutes.states.*;

/**
 *
 * @author ricardopereira
 */
public class PanelInformacao extends PanelBase implements Observer {
    
    public PanelInformacao(DataController controller) {
        super(controller);
        
        // Teste
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.setBackground(new Color(219,219,219));
        this.setPreferredSize(new Dimension(300,500));
        this.setMinimumSize(new Dimension(300,500));
        this.setMaximumSize(new Dimension(300,500));
    }

    @Override
    public void update(Observable o, Object arg) {
        // Opções do Jogo
        if (getJogo().getEstadoActual().getClass() == AguardaOpcoesJogo.class) {
            
            JButton btNew = new JButton("Novo jogo");
            btNew.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getJogo().novoJogo();
                    getController().update();
                }
            });
            this.add(btNew, BorderLayout.CENTER);
            
            this.validate();
        }
        else
        {
            this.removeAll();
            
            this.validate();
            this.repaint();
        }
    }
    
}
