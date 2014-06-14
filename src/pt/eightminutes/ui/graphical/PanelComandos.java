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
    
    public PanelComandos(PanelBase owner, DataController controller) {
        super(owner,controller);
        
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(new Color(232,232,232));
        this.setPreferredSize(new Dimension(800,170));
        this.setMinimumSize(new Dimension(800,170));
        this.setMaximumSize(new Dimension(800,170));
    }

    @Override
    public void update(Observable o, Object arg) {
        clear();
        // Opções do Jogo
        if (getJogo().getEstadoActual().getClass() == AguardaOpcoesJogo.class) {
            showThis(new PanelComandosOpcoesJogo(this,getController()));
        }
        else if (getJogo().getEstadoActual().getClass() == AguardaPreparaJogo.class) {
            showThis(new PanelComandosPreparaJogo(this,getController()));
        }
        else if (getJogo().getEstadoActual().getClass() == AguardaAposta.class) {
            showThis(new PanelComandosApostas(this,getController()));
        }
        else if (getJogo().getEstadoActual().getClass() == AguardaEscolheCarta.class) {
            showThis(new PanelComandosEscolheCarta(this,getController()));
        }
        else if (getJogo().getEstadoActual().getClass() == AguardaEscolheAccao.class) {
            showThis(new PanelComandosEscolheAccao(this,getController()));
        }
        else if (getJogo().getEstadoActual().getClass() == AguardaColocaCidade.class) {
            showThis(new PanelComandosColocaCidade(this,getController()));
        }
        else if (getJogo().getEstadoActual().getClass() == AguardaColocaExercito.class) {
            showThis(new PanelComandosColocaExercito(this,getController()));
        }
        else if (getJogo().getEstadoActual().getClass() == AguardaDestroiExercito.class) {
            showThis(new PanelComandosDestroiExercito(this,getController()));
        }
        else if (getJogo().getEstadoActual().getClass() == AguardaMoveExercito.class) {
            showThis(new PanelComandosMoveExercito(this,getController()));
        }
        else if (getJogo().getEstadoActual().getClass() == AguardaMoveExercitoAgua.class) {
            showThis(new PanelComandosMoveExercito(this,getController()));
        }
        else if (getJogo().getEstadoActual().getClass() == AguardaJokers.class) {
            showThis(new PanelComandosJokers(this,getController()));
        }

    }
    
}
