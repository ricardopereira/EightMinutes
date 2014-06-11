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
import javax.swing.JPanel;
import pt.eightminutes.states.*;

public class PanelComandos extends PanelBase implements Observer {
    
    public PanelComandos(DataController controller) {
        super(controller);
        
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(new Color(232,232,232));
        this.setPreferredSize(new Dimension(800,220));
        this.setMinimumSize(new Dimension(800,220));
        this.setMaximumSize(new Dimension(800,220));
    }

    @Override
    public void update(Observable o, Object arg) {
        clear();
        // Opções do Jogo
        if (getJogo().getEstadoActual().getClass() == AguardaOpcoesJogo.class)
        {
            showThis(new PanelComandosOpcoesJogo(getController()));
        }
        else if (getJogo().getEstadoActual().getClass() == AguardaPreparaJogo.class)
        {
            showThis(new PanelComandosPreparaJogo(getController()));
        }
    }
    
}
