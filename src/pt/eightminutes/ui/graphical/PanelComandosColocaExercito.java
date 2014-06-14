package pt.eightminutes.ui.graphical;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import pt.eightminutes.logic.Accao;
import pt.eightminutes.logic.Jogador;
import pt.eightminutes.logic.Regiao;

public class PanelComandosColocaExercito extends PanelBase {
    
    public PanelComandosColocaExercito(PanelBase owner, DataController controller) {
        super(owner,controller);
        
        // Eventos
        controller.addListener(new actionOnSelectRegiao());
                
        Jogador jogador = getJogo().getJogadorActivo();        
        
        // Regiões válidas
        ArrayList<Regiao> regioesValidas = jogador.getListaRegioesComCidade();
        regioesValidas.add(getJogo().getMapa().getRegiaoInicial());
        controller.setFocusRegioes(regioesValidas);

        Accao accao = jogador.getCartaActiva().getAccaoActiva();
        
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        
        this.add(new JLabel("Acção Coloca Exercito"), BorderLayout.CENTER);
        this.add(new JLabel("Escolha uma região com a cor xpto"), BorderLayout.CENTER);
        this.add(new JLabel("Defina a quantidade de exercitos que deseja colocar(entre 1 e "+String.format("%d",accao.getQtd())+")"), BorderLayout.CENTER);
        final JTextField edQtdExercitos = new JTextField("1");    
        this.add(edQtdExercitos, BorderLayout.CENTER);

        JButton btColocaExercito = new JButton("Coloca Exército");        
        btColocaExercito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Região seleccionada
                Regiao regiao = getController().getSelectedRegiao();
                
                if (regiao == null)
                {
                   String msg = String.format("Deve definir uma região.");
                   JOptionPane.showMessageDialog(null,msg,"Coloca Exército",JOptionPane.WARNING_MESSAGE);
                   return;
                }
                
                // Acção activa
                Accao accao = getJogo().getJogadorActivo().getCartaActiva().getAccaoActiva();
                int myQtd = Integer.parseInt(edQtdExercitos.getText());

                if (myQtd >= 1 && myQtd <= accao.getQtd())
                {
                    getJogo().colocaExercito(regiao, myQtd);
                    getController().setSelectedRegiao(null);
                    getController().setFocusRegioes(null);
                }   
                else
                {                        
                    String msg = String.format("Deve definir uma quantidade entre 1 e %s", accao.getQtd());
                    JOptionPane.showMessageDialog(null,msg,"Coloca Exército",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton btPassaVez = new JButton("Passa vez");
        btPassaVez.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                getJogo().passaVez();
                getController().setSelectedRegiao(null);
                getController().setFocusRegioes(null);
            }
        });
        
        this.add(btColocaExercito, BorderLayout.CENTER);
        this.add(btPassaVez, BorderLayout.CENTER);
    }
    
    final public class actionOnSelectRegiao implements DataControllerListener {
        @Override
        public void onSelectRegiao() {
            Regiao regiao = getController().getSelectedRegiao();
            if (regiao == null) 
                return;
            
            boolean escolhaValida = false;
            if (getController().getFocusRegioes() != null)
                for (Regiao item : getController().getFocusRegioes()) {
                    if (item == regiao) {
                        escolhaValida = true;
                        break;
                    }
                }
            
            if (!escolhaValida)
                getController().setSelectedRegiao(null);
        }
        public void onFocusRegioes() {
            
        }
    }

}
