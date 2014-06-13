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

public class PanelComandosPreparaJogo extends PanelBase {
    
    public PanelComandosPreparaJogo(PanelBase owner, final DataController controller) {
        super(owner,controller);
        
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
            JPanel coluna1 = new JPanel();
            coluna1.setBackground(this.getBackground());
            coluna1.setLayout(new BoxLayout(coluna1,BoxLayout.Y_AXIS));
            
            this.add(coluna1, BorderLayout.CENTER);
            
            final List<JTextField> edits = new ArrayList<>();        
            
            JPanel panelJogador;
            for (int i = 0; i < getJogo().getNumJogadores(); i++) {
                panelJogador = new JPanel();
                panelJogador.setBackground(this.getBackground());
                
                // Criar edit para o nome do jogador
                edits.add(new JTextField("nome jogador "+(i+1)));
                panelJogador.add(edits.get(edits.size()-1), BorderLayout.CENTER);
                
                JCheckBox ia = new JCheckBox("IA");
                panelJogador.add(ia);
                
                coluna1.add(panelJogador);
            }

            JButton btApostas = new JButton("Iniciar apostas");
            btApostas.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    // ToDo: Cores num local adequado
                    ArrayList<Color> cores = new ArrayList<Color>();
                    cores.add(new Color(250,199,122));
                    cores.add(new Color(149,111,155));
                    cores.add(new Color(213,120,135));
                    cores.add(new Color(254,180,163));
                    cores.add(new Color(184,104,133));
                    
                    for (JTextField item : edits) {
                        getJogo().criaJogador(item.getText(), cores.get(getJogo().getJogadores().size()));
                    }
                    
                    //TODO- colocar sem ser fixo
                    IA.attachIA(getJogo().getJogadores().get(1), controller);
                    getJogo().comecaApostas();
                }
            });
            this.add(btApostas, BorderLayout.CENTER);
        }

    }

}
