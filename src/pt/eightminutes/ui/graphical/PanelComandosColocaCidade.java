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
    
    public PanelComandosColocaCidade(PanelBase owner, final DataController controller) {
        super(owner,controller);
        
        // Eventos
        Jogador jogador = controller.getJogo().getJogadorActivo();        
        
        controller.addListener(new actionOnSelectRegiao());
        controller.setFocusRegioes(jogador.getListaRegioesComExercito());       
        
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(new JLabel("Acção Coloca Cidade"), BorderLayout.CENTER);
        this.add(new JLabel("Escolha uma região com a cor xpto"), BorderLayout.CENTER);
        
        JButton btColocaCidade = new JButton("Coloca Cidade");
            btColocaCidade.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    //Todo-ir buscar a região selecionada
                    Regiao regiao = controller.getSelectedRegiao();
                    if(regiao != getJogo().getMapa().getRegiaoInicial())
                    {
                        if(regiao!=null)
                        {
                            getJogo().colocaCidade(regiao);
                        }
                        else
                        {
                           String msg= String.format("Deve definir uma região.");
                           JOptionPane.showMessageDialog(null,msg,"Coloca Cidade",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    else
                    {
                       String msg = "Não pode colocar cidades na regiãi inicial!";
                       JOptionPane.showMessageDialog(null,msg,"Coloca Cidade",JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            this.add(btColocaCidade, BorderLayout.CENTER);                       
    }

    final public class actionOnSelectRegiao implements PanelMapaListener {
        @Override
        public void onSelectRegiao() {            
            System.out.println("Teste: seleccionado para colocar cidade");
        }
    }
    
}
