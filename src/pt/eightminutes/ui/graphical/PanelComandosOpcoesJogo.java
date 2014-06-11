package pt.eightminutes.ui.graphical;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class PanelComandosOpcoesJogo extends PanelBase {
    
    public PanelComandosOpcoesJogo(DataController controller) {
        super(controller);
        
        JButton btNovo = new JButton("Novo jogo");
        btNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getJogo().novoJogo();
                // ToDo: Indicar o número de jogadores
                getJogo().defineNumJogadores(2);
                getController().update();
            }
        });
        this.add(btNovo, BorderLayout.CENTER);

    }
    
}