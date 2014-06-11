package pt.eightminutes.ui.graphical;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PanelComandosPreparaJogo extends PanelBase {
    
    public PanelComandosPreparaJogo(PanelBase owner, DataController controller) {
        super(owner,controller);
        
        if (getJogo().getNumJogadores() == 0)
        {
            this.add(new JLabel("Número de Jogadores: "), BorderLayout.CENTER);
            
            final JTextField edNrJogadores = new JTextField("0");
            
            this.add(edNrJogadores, BorderLayout.CENTER);
            
            JButton btJogadores = new JButton("Iniciar jogadores");
            btJogadores.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Número de Jogadores
                    getJogo().defineNumJogadores(Integer.parseInt(edNrJogadores.getText()));
                }
            });
            this.add(btJogadores, BorderLayout.CENTER);
        }
        else
        {
            final List<JTextField> edits = new ArrayList<>();

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
                }
            });
            this.add(btApostas, BorderLayout.CENTER);
        }

    }

}
