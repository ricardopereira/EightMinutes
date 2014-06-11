package pt.eightminutes.ui.graphical;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextField;

public class PanelComandosPreparaJogo extends PanelBase {
    
    public PanelComandosPreparaJogo(DataController controller) {
        super(controller);
        
        final List<JTextField> edits = new ArrayList<>();
        
        // ToDo: Duvida - IEstados defineNumJogadores(int numJogadores)
        //Estou a aceder directamente pelo getJogo()
        
        for (int i = 0; i < getJogo().getNumJogadores(); i++) {
            // Criar edit para o nome do jogador
            edits.add(new JTextField("nome jogador "+(i+1)));
            this.add(edits.get(edits.size()-1), BorderLayout.CENTER);
        }
        
        JButton btApostas = new JButton("Iniciar apostas");
        btApostas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JTextField item : edits) {
                    getJogo().criaJogador(item.getText());
                }
                getJogo().comecaApostas();
                getController().update();
            }
        });
        this.add(btApostas, BorderLayout.CENTER);

    }

}
