package pt.eightminutes.ui.graphical;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import pt.eightminutes.logic.*;

public class PanelComandosApostas extends PanelBase {
    
    public PanelComandosApostas(PanelBase owner, DataController controller) {
        super(owner,controller);
        
        Jogador jogador = null;
                        
        for (int i = 0; i < getJogo().getJogadores().size(); i++) {
            jogador = getJogo().getJogadores().get(i);
            if (jogador.getAposta() == -1) {
                break;
            }
            jogador = null;
        }
        
        final Jogador jogadorActivo = jogador;
        
        if (jogador != null) {
            
            this.add(new JLabel("Aposta para Jogador: "+jogador.getNome()), BorderLayout.CENTER);
            
            final JTextField edAposta = new JTextField("0");
            
            this.add(edAposta, BorderLayout.CENTER);
            
            JButton btAposta = new JButton("Apostar");
            btAposta.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Número de Jogadores
                    getJogo().defineApostasJogadores(jogadorActivo,Integer.parseInt(edAposta.getText()));
                }
            });
            this.add(btAposta, BorderLayout.CENTER);
        }
        else
            getJogo().comecaJogo();
        
    }
    
}
