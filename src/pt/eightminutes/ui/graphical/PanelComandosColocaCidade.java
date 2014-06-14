package pt.eightminutes.ui.graphical;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import pt.eightminutes.logic.Exercito;
import pt.eightminutes.logic.Jogador;
import pt.eightminutes.logic.Regiao;

public class PanelComandosColocaCidade extends PanelBase {
    
    public PanelComandosColocaCidade(PanelBase owner, DataController controller) {
        super(owner,controller);
        
        // Eventos
        controller.clearListeners();
        controller.addListener(new actionOnSelectRegiao());
        
        Jogador jogador = controller.getJogo().getJogadorActivo();
        
        controller.setFocusRegioes(jogador.getListaRegioesComExercito());
        
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        
        this.add(new JLabel("Acção Coloca Cidade"), BorderLayout.CENTER);
        this.add(new JLabel("Escolha uma região com a cor xpto"), BorderLayout.CENTER);
        
        JButton btColocaCidade = new JButton("Coloca Cidade");
        btColocaCidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Regiao regiao = getController().getSelectedRegiao();
                
                if (regiao != getJogo().getMapa().getRegiaoInicial())
                {
                    if (regiao != null)
                    {
                        getJogo().colocaCidade(regiao);
                        getController().setSelectedRegiao(null);
                        getController().setFocusRegioes(null);
                    }
                    else
                    {
                       String msg = String.format("Deve definir uma região.");
                       JOptionPane.showMessageDialog(null,msg,"Coloca Cidade",JOptionPane.WARNING_MESSAGE);
                    }
                }
                else
                {
                   String msg = "Não pode colocar cidades na região inicial!";
                   JOptionPane.showMessageDialog(null,msg,"Coloca Cidade",JOptionPane.WARNING_MESSAGE);
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
        
        this.add(btColocaCidade, BorderLayout.CENTER);
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
