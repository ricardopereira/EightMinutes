package pt.eightminutes.ui.graphical;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import static javax.swing.BoxLayout.Y_AXIS;
import javax.swing.JLabel;

public class PanelInformacaoJogadores extends PanelBase {
    
    public PanelInformacaoJogadores(PanelBase owner, DataController controller) {
        super(owner,controller);
        
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        
        this.add(new JLabel("Número de Jogadores: " + getJogo().getNumJogadores()));
        
        this.add(Box.createRigidArea(new Dimension(5,5)));
        this.add(Box.createRigidArea(new Dimension(5,15)));
        
        JLabel label;
        for (int i = 0; i < getJogo().getJogadores().size(); i++) {
            label = new JLabel("Jogador " +(i+1)+ ": " + getJogo().getJogadores().get(i).getNome());
            this.add(label);
            
            this.add(Box.createRigidArea(new Dimension(5,5)));
            
            label = new JLabel("  Moedas: " + getJogo().getJogadores().get(i).getMoedas());
            this.add(label);
            
            this.add(Box.createRigidArea(new Dimension(5,5)));
            this.add(Box.createRigidArea(new Dimension(5,15)));
        }
    }
    
}
