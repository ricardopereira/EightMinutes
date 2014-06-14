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
    private boolean moveTerra;
    private ArrayList<Regiao> regioesComExercito = null;
    
    private JLabel jLabelOrig;
    
    
    private ArrayList<Exercito> exercitoList;
    private JLabel jLabelDest;
    private JLabel jLabelQtd;
    private JLabel jLabelMov;
    private JSpinner spinner;
    
    private JButton btMoveExercito;
    private JButton btPassaVez;
    
    public PanelComandosMoveExercito(PanelBase owner, DataController controller) {
        super(owner,controller);
        
        // Eventos
        controller.clearListeners();
        controller.addListener(new actionOnSelectRegiao());
        
        accao = getJogo().getJogadorActivo().getCartaActiva().getAccaoActiva();
        
        if (getJogo().getEstadoActual().getClass() == AguardaMoveExercito.class)
            moveTerra = true;
        
        Jogador jogador = getJogo().getJogadorActivo();                
        // Regiões válidas
        regioesComExercito = jogador.getListaRegioesComExercito();
        controller.setFocusRegioes(regioesComExercito);
        
        // LAYOUT
                                                                 
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(new JLabel("Acção Move Exercito: "+accao.getNome()+" Qtd="+accao.getQtd()), BorderLayout.CENTER);        
        this.add(jLabelOrig = new JLabel("Escolha uma região origem"), BorderLayout.CENTER);   
        this.add(jLabelQtd = new JLabel());
        this.add(jLabelMov = new JLabel());                         
        
        cboExercito = new JComboBox();
        
        btMoveExercito = new JButton("Move Exercito");
        btMoveExercito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                Regiao regiaoSelectedDest = getController().getSelectedRegiao();

                int myQtd = (Integer)cboExercito.getSelectedItem();

                if (regiaoSelectedDest != null)
                {
                    if (myQtd >= 1 && myQtd <= exercitoList.size())
                    {   
                        ArrayList<Exercito> exercitoListAux = new ArrayList<>();
                        for (int i = 0; i < myQtd; i++) {
                            exercitoListAux.add(exercitoList.get(i));
                        }

                        if (moveTerra)
                            getJogo().moveExercito(regiaoSelectedDest, exercitoListAux);
                        else
                            getJogo().moveExercitoAgua(regiaoSelectedDest, exercitoListAux);
                        
                        getController().setSelectedRegiao(null);
                        if (accao.getQtd() == 0)
                            getController().setFocusRegioes(null);
                        else
                            getController().setFocusRegioes(regioesComExercito);
                        
                        btMoveExercito.setVisible(false);
                    }   
                    else
                    {                        
                        String msg= String.format("Deve definir uma quantidade entre 1 e %s", (accao.getQtd()/myQtd));
                        JOptionPane.showMessageDialog(null,msg,"Move Exército",JOptionPane.WARNING_MESSAGE);
                    }
                }
                else
                {
                    String msg= "Deve definir uma região destino";
                    JOptionPane.showMessageDialog(null,msg,"Região Destino",JOptionPane.WARNING_MESSAGE);
                }
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
        this.add(btPassaVez, BorderLayout.CENTER);

        jLabelQtd.setVisible(false);
        jLabelMov.setVisible(false);
        jLabelDest.setVisible(false);
        cboExercito.setVisible(false);
        btMoveExercito.setVisible(false);
    }

     final public class actionOnSelectRegiao implements DataControllerListener {
        public Regiao regiaoOrigem = null;
        public Regiao regiaoDestino;
        
        @Override
        public void onSelectRegiao() {
            Regiao regiao = getController().getSelectedRegiao();
            if (regiao == null)
                return;
            
            regiaoDestino = null;
            // Verificar regiao destino
            if (regiaoOrigem != null) {
                for (Regiao focusRegiao : getController().getFocusRegioes()) {
                    if (regiao == focusRegiao) {
                        regiaoDestino = regiao;
                    }
                }
            }
            
            // Já existe destino seleccionado
            if (regiaoDestino != null) {
                getController().setSelectedRegiao(regiaoDestino);
                btMoveExercito.setVisible(true);
                cboExercito.setVisible(false);
                return;
            }            
            
            // Exercitos
            exercitoList = new ArrayList<>();
            regiao.getExercitosByJogador(getJogo().getJogadorActivo(),exercitoList);
            
            if (exercitoList.isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Deve seleccionar uma região com exércitos.","Mover Exército",JOptionPane.WARNING_MESSAGE);
                getController().setSelectedRegiao(null);
                regiaoOrigem = null;
            }
            else
                regiaoOrigem = regiao;
            
            if (regiaoOrigem == null)
            {
                regiaoOrigem = null;
                jLabelQtd.setVisible(false);
                jLabelMov.setVisible(false);
                cboExercito.setVisible(false);
                btMoveExercito.setVisible(false);
                jLabelOrig.setText("Escolha uma região origem");
                getController().setFocusRegioes(regioesComExercito);
                return;
            }
            
            cboExercito.removeAllItems();
            for (int i = 0; i < exercitoList.size(); i++)
                cboExercito.addItem(i+1);
            
            //accao.getQtd()
                        
            cboExercito.addActionListener(new ActionListener () {
                public void actionPerformed(ActionEvent e) {
                    if (accao.getQtd() == 0)
                        return;
                    if (cboExercito.getSelectedItem() == null)
                        return;
                     // Regiões válidas
                    int qtdMov = accao.getQtd() / (cboExercito.getSelectedIndex()+1);

                    ArrayList<Regiao> regioesValidas = new ArrayList<>();
                    if (moveTerra)
                        getController().getJogo().getRegioesPossiveisTerra(regiaoOrigem, qtdMov, regioesValidas);
                    else
                        getController().getJogo().getRegioesPossiveisAgua(regiaoOrigem, qtdMov, regioesValidas);
                    getController().setFocusRegioes(regioesValidas);     
                }
            });
            
            // Refrescar regiões válidas
            cboExercito.setSelectedIndex(-1);
            cboExercito.setSelectedIndex(0);
            
            jLabelQtd.setVisible(true);
            jLabelMov.setVisible(true);
            cboExercito.setVisible(true);
            btMoveExercito.setVisible(false);
            jLabelOrig.setText("Decida quantos exércitos quer mover e escolha a região destino");
        }
        public void onFocusRegioes() {
            
        }
    }
    
}
