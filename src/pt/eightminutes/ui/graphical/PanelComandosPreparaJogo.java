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
import pt.eightminutes.ia.IA;
import pt.eightminutes.logic.Jogador;

public class PanelComandosPreparaJogo extends PanelBase {
    
    public PanelComandosPreparaJogo(PanelBase owner, final DataController controller) {
        super(owner,controller);
        
        setBackground(owner.getBackground());
        
        if (getJogo().getNumJogadores() == 0)
        {
            this.add(new JLabel("Número de Jogadores: "), BorderLayout.CENTER);
                        
            final JComboBox cboJogadores = new JComboBox();
            for (int i = 0; i < 5; i++)
                cboJogadores.addItem(i+1);
            cboJogadores.setSelectedIndex(1);
                        
            this.add(cboJogadores, BorderLayout.CENTER);
            
            JButton btJogadores = new JButton("Iniciar jogadores");
            btJogadores.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Número de Jogadores
                    getJogo().defineNumJogadores((Integer)cboJogadores.getSelectedItem());
                }
            });
            this.add(btJogadores, BorderLayout.CENTER);
        }
        else
        {
            JPanel coluna = new JPanel();
            coluna.setBackground(this.getBackground());
            coluna.setLayout(new BoxLayout(coluna,BoxLayout.Y_AXIS));
            this.add(coluna, BorderLayout.CENTER);
            
            final List<JTextField> listEdits = new ArrayList<>();
            final List<JCheckBox> listIAChecks = new ArrayList<>();
            
            JPanel panelJogador;
            for (int i = 0; i < getJogo().getNumJogadores(); i++) {
                panelJogador = new JPanel();
                panelJogador.setBackground(coluna.getBackground());
                
                // Criar edit para o nome do jogador
                JTextField edNome = new JTextField("nome jogador "+(i+1));
                listEdits.add(edNome);
                panelJogador.add(edNome, BorderLayout.CENTER);
                
                JCheckBox chkIA = new JCheckBox("IA");
                listIAChecks.add(chkIA);
                panelJogador.add(chkIA);
                
                if (i == 3) {
                    coluna = new JPanel();
                    coluna.setBackground(this.getBackground());
                    coluna.setLayout(new BoxLayout(coluna,BoxLayout.Y_AXIS));
                    this.add(coluna, BorderLayout.CENTER);
                }
                
                coluna.add(panelJogador);
            }

            JButton btApostas = new JButton("Iniciar apostas");
            btApostas.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    // ToDo: Cores num local adequado
                    ArrayList<Color> cores = new ArrayList<>();
                    cores.add(new Color(250,199,122));
                    cores.add(new Color(149,111,155));
                    cores.add(new Color(213,120,135));
                    cores.add(new Color(254,180,163));
                    cores.add(new Color(184,104,133));
                    
                    // Criar jogadores
                    Jogador jogador;
                    for (int i = 0; i < listEdits.size(); i++) {
                        jogador = getJogo().criaJogador(listEdits.get(i).getText(), cores.get(i));
                        // Jogador de IA
                        if (listIAChecks.get(i).isSelected())
                            IA.attachIA(jogador, controller);
                    }

                    // Jogadore criados...
                    getJogo().comecaApostas();
                }
            });
            this.add(btApostas, BorderLayout.CENTER);
        }

    }

}
