package pt.eightminutes.ui.graphical;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import pt.eightminutes.logic.Accao;
import pt.eightminutes.logic.Regiao;

public class PanelComandosColocaExercito extends PanelBase {
    
    public PanelComandosColocaExercito(PanelBase owner, DataController controller) {
        super(owner,controller);
        
        Accao accao = getJogo().getJogadorActivo().getCartaActiva().getAccaoActiva();
        
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(new JLabel("Acção Coloca Exercito"), BorderLayout.CENTER);
        this.add(new JLabel("Escolha uma região com a cor xpto"), BorderLayout.CENTER);
        this.add(new JLabel("Defina a quantidade de exercitos que deseja colocar(entre 1 e "+String.format("%d",accao.getQtd())+")"), BorderLayout.CENTER);
        final JTextField edQtdExercitos = new JTextField("1");
            
        this.add(edQtdExercitos, BorderLayout.CENTER);
        JButton btColocaExercito = new JButton("Coloca Exercito");
        btColocaExercito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Regiao regiao = getController().getSelectedRegiao();
                Accao accao = getJogo().getJogadorActivo().getCartaActiva().getAccaoActiva();
                int myQtd = Integer.parseInt(edQtdExercitos.getText());                  

                if (myQtd >= 1 && myQtd <= accao.getQtd())
                {
                    regiao = null;
                    getJogo().colocaExercito(regiao, Integer.parseInt(edQtdExercitos.getText()));
                }   
                else
                {                        
                    String msg = String.format("Deve definir uma quantidade entre 1 e %s", accao.getQtd());
                    JOptionPane.showMessageDialog(null,msg,"Coloca Exercito",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        this.add(btColocaExercito, BorderLayout.CENTER);                       
    }

}
