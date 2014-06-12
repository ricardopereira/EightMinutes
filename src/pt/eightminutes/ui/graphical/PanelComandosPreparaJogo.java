package pt.eightminutes.ui.graphical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
            this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
            
            final List<JTextField> edits = new ArrayList<>();
            
            final JComboBox cores = new JComboBox();
            cores.addItem(Color.GREEN);
            cores.addItem(Color.CYAN);
            cores.addItem(Color.RED);
            cores.addItem(Color.ORANGE);
            cores.addItem(Color.BLUE);
            
            JPanel panelJogador;
            for (int i = 0; i < getJogo().getNumJogadores(); i++) {
                panelJogador = new JPanel();
                
                // Criar edit para o nome do jogador
                edits.add(new JTextField("nome jogador "+(i+1)));
                panelJogador.add(edits.get(edits.size()-1), BorderLayout.CENTER);
                
                JCheckBox ia = new JCheckBox("IA");
                panelJogador.add(ia);
                
                this.add(panelJogador);
            }

            JButton btApostas = new JButton("Iniciar apostas");
            btApostas.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i = 0;
                    for (JTextField item : edits) {
                        // ToDo: Cores
                        getJogo().criaJogador(item.getText(), (Color)cores.getItemAt(i++));
                    }
                    getJogo().comecaApostas();
                }
            });
            this.add(btApostas, BorderLayout.CENTER);
        }

    }

}
