package pt.eightminutes.ui.graphical;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import pt.eightminutes.logic.Jogador;

public class PanelInformacaoJogadores extends PanelBase {
    
    public PanelInformacaoJogadores(PanelBase owner, DataController controller) {
        super(owner,controller);
        
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        
        this.add(new JLabel("Número de Jogadores: " + getJogo().getNumJogadores()));
        
        this.add(Box.createRigidArea(new Dimension(5,5)));
        this.add(Box.createRigidArea(new Dimension(5,15)));
        
        JLabel label;
        Jogador itemJogador;
        for (int i = 0; i < getJogo().getJogadores().size(); i++) {
            itemJogador = getJogo().getJogadores().get(i);
                    
            label = new JLabel("Jogador " +(i+1)+ ": " + itemJogador.getNome());
            // Jogador activo
            if (getJogo().getJogadorActivo() == itemJogador) {
                label.setForeground(Color.red);
            }
            this.add(label);
            
            this.add(Box.createRigidArea(new Dimension(5,5)));
            
            label = new JLabel("  Moedas: " + itemJogador.getMoedas());
            this.add(label);
            
            this.add(Box.createRigidArea(new Dimension(5,5)));
            this.add(Box.createRigidArea(new Dimension(5,15)));
        }
    }
    
}
