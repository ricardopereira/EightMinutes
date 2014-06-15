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
        
        final Jogador jogadorActivo = getJogo().getJogadorActivo();
        
        if (jogadorActivo != null) {
            
            this.add(new JLabel("Aposta para Jogador: "+jogadorActivo.getNome()), BorderLayout.CENTER);
            
            final JTextField edAposta = new JTextField("0");
            
            this.add(edAposta, BorderLayout.CENTER);
            
            JButton btAposta = new JButton("Apostar");
            btAposta.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (jogadorActivo.getAposta() == -1)
                        // Número de Jogadores
                        getJogo().defineApostasJogadores(jogadorActivo,Integer.parseInt(edAposta.getText()));
                }
            });
            this.add(btAposta, BorderLayout.CENTER);
        }      
    }
    
}
