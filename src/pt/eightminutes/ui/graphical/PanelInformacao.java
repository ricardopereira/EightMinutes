package pt.eightminutes.ui.graphical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import pt.eightminutes.states.*;

public class PanelInformacao extends PanelBase implements Observer {
    
    public PanelInformacao(PanelBase owner, DataController controller) {
        super(owner,controller);
        
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.setBackground(new Color(219,219,219));
        this.setPreferredSize(new Dimension(300,500));
        this.setMinimumSize(new Dimension(300,500));
        this.setMaximumSize(new Dimension(300,500));
    }

    @Override
    public void update(Observable o, Object arg) {
        clear();
        
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        
        if (getJogo().getEstadoActual() == null)
            return;
        this.add(new JLabel(getJogo().getEstadoActual().getClass().getSimpleName()));  
        validate();
        
        if (getJogo().getEstadoActual().getClass() == AguardaOpcoesJogo.class) {
            
        }
        else if (getJogo().getEstadoActual().getClass() == AguardaPreparaJogo.class) {
            
        }
        else if (getJogo().getEstadoActual().getClass() == AguardaAposta.class) {
            
        }
        else {
            showThis(new PanelInformacaoJogadores(this,getController()));
        }

    }
    
}
