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
import pt.eightminutes.logic.Exercito;
import pt.eightminutes.logic.Regiao;

public class PanelComandosMoveExercito extends PanelBase {
    
    public PanelComandosMoveExercito(PanelBase owner, DataController controller) {
        super(owner,controller);    
        
        final Accao accao = getJogo().getJogadorActivo().getCartaActiva().getAccaoActiva();
        int count=0;
                                                                 
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(new JLabel("Acção Move Exercito"), BorderLayout.CENTER);        
        this.add(new JLabel("Escolha uma região origem com a cor xpto"), BorderLayout.CENTER);
        //Todo- Selecionar a região no mapa
        Regiao regiaoSelectedOrig=null;
        final ArrayList<Exercito> exercitoList = new ArrayList<>();
        if(regiaoSelectedOrig!=null)
            regiaoSelectedOrig.getExercitosByJogador(getJogo().getJogadorActivo(),exercitoList);        

            this.add(new JLabel("Defina a quantidade de exercitos que deseja mover(entre 1 e "
                        +String.format("%d",exercitoList.size())+")"), BorderLayout.CENTER);

            this.add(new JLabel("Pode efectuar até ("+String.format("%d",accao.getQtd())+") movimentos para 1 exercito."), BorderLayout.CENTER);

            this.add(new JLabel("Escolha uma região destino com a cor xpto"), BorderLayout.CENTER);
            final Regiao regiaoSelectedDest=null;

            final JTextField edQtdExercitos = new JTextField("1");
            this.add(edQtdExercitos, BorderLayout.CENTER);
        
        JButton btMoveExercito = new JButton("Move Exercito");
            btMoveExercito.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    //Todo-ir buscar a região selecionada                   
                    int myQtd = Integer.parseInt(edQtdExercitos.getText());                  
                    
                    if (myQtd>=1 && myQtd<= exercitoList.size())
                    {   
                        ArrayList<Exercito> exercitoListAux=new ArrayList<>();
                        for (int i = 0; i < myQtd; i++) {
                            exercitoListAux.add(exercitoList.get(i));
                        }
                        
                        getJogo().moveExercito(regiaoSelectedDest, exercitoListAux);
                    }   
                    else
                    {                        
                        String msg= String.format("Deve definir uma quantidade entre 1 e %s", (accao.getQtd()/myQtd));
                        JOptionPane.showMessageDialog(null,msg,"Coloca Exercito",JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            this.add(btMoveExercito, BorderLayout.CENTER);                       
    }

}
