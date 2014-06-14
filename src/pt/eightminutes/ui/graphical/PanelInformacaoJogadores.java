package pt.eightminutes.ui.graphical;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
            
            // Grupo da cor e do nome do jogador
            JPanel group = new JPanel();
            group.setPreferredSize(new Dimension(15,25));
            group.setMaximumSize(new Dimension(300,25));
            
            JPanel cor = new JPanel();
            cor.setBackground(itemJogador.getCor());
            cor.setPreferredSize(new Dimension(15,15));
            cor.setMinimumSize(new Dimension(15,15));
            cor.setMaximumSize(new Dimension(15,15));
            group.add(cor);
        
            label = new JLabel("Jogador " +(i+1)+ ": " + itemJogador.getNome());
            // Jogador activo
            if (getJogo().getJogadorActivo() == itemJogador) {
                label.setForeground(Color.red);
            }
            group.add(label);
            
            this.add(group);
            
            this.add(Box.createRigidArea(new Dimension(5,5)));
            
            label = new JLabel("  Moedas: " + itemJogador.getMoedas());
            this.add(label);
            
            this.add(Box.createRigidArea(new Dimension(5,5)));
            this.add(Box.createRigidArea(new Dimension(5,15)));
        }
    }
    
}
