package pt.eightminutes.ui.graphical;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import pt.eightminutes.logic.*;

public class PanelComandosDestroiExercito extends PanelBase {
    
    JButton btDestroiExercito;
    JButton btPassaVez;
    JLabel lblInfo;
    JComboBox cboJogadores;
    
    public PanelComandosDestroiExercito(PanelBase owner, DataController controller) {
        super(owner,controller);
        
        // Eventos
        controller.addListener(new actionOnSelectRegiao());
        
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        
        final Jogador jogador = getJogo().getJogadorActivo();
        
        this.add(new JLabel("Acções Destrói Exército"), BorderLayout.CENTER);
        lblInfo = new JLabel("Escolha uma região com exércitos");
        this.add(lblInfo, BorderLayout.CENTER);
        
        cboJogadores = new JComboBox();
        this.add(cboJogadores);
        cboJogadores.setVisible(false);
        
        ArrayList<Regiao> regioesValidas;
        regioesValidas = getJogo().getMapa().getRegioesComExercitos();
        // Focus das regiões válidas
        controller.setFocusRegioes(regioesValidas);
        
        btDestroiExercito = new JButton("Destrói Exército");
        btDestroiExercito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Região seleccionada
                Regiao regiao = getController().getSelectedRegiao();
                
                if (regiao == null)
                {
                   JOptionPane.showMessageDialog(null,"Deve seleccionar uma região.","Destrói Exército",JOptionPane.WARNING_MESSAGE);
                   return;
                }
                
                if (regiao.getExercitos().isEmpty())
                {
                   JOptionPane.showMessageDialog(null,"Deve seleccionar uma região com exércitos.","Destrói Exército",JOptionPane.WARNING_MESSAGE);
                   return;
                }
                
                if (cboJogadores == null)
                    return;
                
                if (cboJogadores.getSelectedItem() == null)
                {
                   JOptionPane.showMessageDialog(null,"Escolha um dos jogadores.","Destrói Exército",JOptionPane.WARNING_MESSAGE);
                   return;
                }
                
                // DESTRUIÇÃO!
                Jogador selectedJogador = (Jogador)cboJogadores.getSelectedItem();
                
                ArrayList<Exercito> exercitos = new ArrayList<>();

                regiao.getExercitosByJogador(selectedJogador, exercitos);
                
                if (exercitos.isEmpty())
                    return;
                
                getJogo().destroiExercito(exercitos.get(exercitos.size()-1));
                getController().setSelectedRegiao(null);
                getController().setFocusRegioes(null);
            }
        });
        
        btPassaVez = new JButton("Passa vez");
        btPassaVez.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                getJogo().passaVez();
                getController().setSelectedRegiao(null);
                getController().setFocusRegioes(null);
            }
        });
        
        this.add(btDestroiExercito, BorderLayout.CENTER);
        this.add(btPassaVez, BorderLayout.CENTER);
        
        btDestroiExercito.setVisible(false);
    }
    
    final public class actionOnSelectRegiao implements DataControllerListener {
        @Override
        public void onSelectRegiao() {
            Regiao regiao = getController().getSelectedRegiao();
            if (regiao == null)
                return;
            
            if (regiao.getExercitos().isEmpty())
            {
               JOptionPane.showMessageDialog(null,"Deve seleccionar uma região com exércitos.","Destrói Exército",JOptionPane.WARNING_MESSAGE);
               getController().setSelectedRegiao(null);
               return;
            }
            
            // Jogadores
            for (Jogador itemJogador : regiao.getJogadores()) {
                cboJogadores.addItem(itemJogador);
            }
            cboJogadores.setVisible(true);
            
            lblInfo.setText("Escolha um dos jogadores");
            btDestroiExercito.setVisible(true);
        }
        public void onFocusRegioes() {
            
        }
    }

}
