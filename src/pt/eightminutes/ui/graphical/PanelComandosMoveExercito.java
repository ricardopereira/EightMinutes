package pt.eightminutes.ui.graphical;

import java.awt.BorderLayout;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import pt.eightminutes.logic.Accao;
import pt.eightminutes.logic.Exercito;
import pt.eightminutes.logic.Jogador;
import pt.eightminutes.logic.Regiao;
import pt.eightminutes.states.AguardaMoveExercito;
import pt.eightminutes.states.EstadosAdapter;

public class PanelComandosMoveExercito extends PanelBase {
    
    private JComboBox cboExercito;
    private Accao accao = null;
    private ArrayList<Exercito> exercitoList;
    private JLabel jLabelOrig;
    private JLabel jLabelDest;
    private JLabel jLabelQtd;
    private JLabel jLabelMov;
    private JButton btMoveExercito;
    private JSpinner spinner;
    private boolean moveTerra;
    private final JButton btPassaVez;
    
    public PanelComandosMoveExercito(PanelBase owner, DataController controller) {
        super(owner,controller);
        
        // Eventos
        controller.clearListeners();
        controller.addListener(new actionOnSelectRegiao());
        
        if(getJogo().getEstadoActual().getClass() == AguardaMoveExercito.class)
            moveTerra = true;
        
        Jogador jogador = getJogo().getJogadorActivo();                  
        // Regiões válidas
        ArrayList<Regiao> regioesValidas = jogador.getListaRegioesComExercito();
        controller.setFocusRegioes(regioesValidas);
        
        accao = getJogo().getJogadorActivo().getCartaActiva().getAccaoActiva();
        int count=0;
                                                                 
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(new JLabel("Acção Move Exercito"), BorderLayout.CENTER);        
        this.add(jLabelOrig = new JLabel("Escolha uma região origem"), BorderLayout.CENTER);   
        this.add(jLabelQtd = new JLabel());
        this.add(jLabelMov = new JLabel());                         
        
        jLabelQtd.setVisible(false);
        jLabelMov.setVisible(false);
        cboExercito = new JComboBox();
        
        btMoveExercito = new JButton("Move Exercito");
            btMoveExercito.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    Regiao regiaoSelectedDest = getController().getSelectedRegiao();
                    
                    int myQtd = (Integer)cboExercito.getSelectedItem();                  
                    if(regiaoSelectedDest !=null)
                    {
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
                    else
                    {
                        String msg= "Deve definir uma região destino";
                        JOptionPane.showMessageDialog(null,msg,"Região Destino",JOptionPane.WARNING_MESSAGE);
                    }  
                    
                    getController().setSelectedRegiao(null);
                    getController().setFocusRegioes(null);
                }
            });
       this.add(cboExercito, BorderLayout.CENTER);                                                                                                              
       this.add(jLabelDest = new JLabel("Escolha uma região destino"), BorderLayout.CENTER);                
       this.add(btMoveExercito, BorderLayout.CENTER);          
       
       btPassaVez = new JButton("Passa a vez");
            btPassaVez.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    getJogo().passaVez();
                    getController().setFocusRegioes(null);
                    getController().setSelectedRegiao(null);
                }
            });

       btMoveExercito.setVisible(false);
       cboExercito.setVisible(false);
       jLabelDest.setVisible(false);
       
       this.add(btPassaVez, BorderLayout.CENTER);
    }

    final public class actionOnSelectRegiao implements DataControllerListener {
        Regiao regiaoOrigem = null;
        @Override
        public void onSelectRegiao() {
            if(regiaoOrigem==null)
            {
                jLabelQtd.setVisible(true);                                
                jLabelOrig.setVisible(false);
                btMoveExercito.setVisible(false);
                cboExercito.setVisible(true);
                
                int mov = accao.getQtd();
                regiaoOrigem = getController().getSelectedRegiao();
                
                exercitoList = new ArrayList<>();
                
                if(regiaoOrigem!=null)
                    regiaoOrigem.getExercitosByJogador(getJogo().getJogadorActivo(),exercitoList);        

                ArrayList<Regiao> regioesValidas = new ArrayList<>();
                if(moveTerra)
                    getController().getJogo().getRegioesPossiveisTerra(regiaoOrigem, accao.getQtd(),regioesValidas);
                else
                    getController().getJogo().getRegioesPossiveisAgua(regiaoOrigem, accao.getQtd(),regioesValidas);
                getController().setFocusRegioes(regioesValidas);
                
                cboExercito.removeAllItems();
                if(cboExercito.getItemCount()==0)
                {
                    for (int i = 0; i < exercitoList.size(); i++)
                        cboExercito.addItem(i+1);
                }
                
                cboExercito.setSelectedIndex(0);   
                
                cboExercito.addActionListener (new ActionListener () {
                    public void actionPerformed(ActionEvent e) {
                         // Regiões válidas
                        int qtdMov = accao.getQtd()/(Integer)cboExercito.getSelectedItem();
                         ArrayList<Regiao> regioesValidas = new ArrayList<>();
                         if(moveTerra)
                             getController().getJogo().getRegioesPossiveisTerra(regiaoOrigem, qtdMov,regioesValidas);
                         else
                             getController().getJogo().getRegioesPossiveisAgua(regiaoOrigem, qtdMov,regioesValidas);
                         getController().setFocusRegioes(regioesValidas);     
                    }
                });                  
            }
            else
            {
                final Regiao regiaoSelectedDest=getController().getSelectedRegiao();   
                if(regiaoSelectedDest!=null)
                    btMoveExercito.setVisible(true);
                                
                regiaoOrigem = null;
                jLabelQtd.setVisible(false);
                jLabelMov.setVisible(false);
                cboExercito.setVisible(false);
            }
        }
        public void onFocusRegioes() {
            
        }
    }
    
}
