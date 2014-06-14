package pt.eightminutes.ui.graphical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import pt.eightminutes.logic.Carta;
import pt.eightminutes.logic.Jogador;
import pt.eightminutes.logic.Recurso;
import pt.eightminutes.logic.RecursoAlimento;
import pt.eightminutes.logic.RecursoFerro;
import pt.eightminutes.logic.RecursoJoia;
import pt.eightminutes.logic.RecursoMadeira;
import pt.eightminutes.logic.RecursoUtensilio;
import pt.eightminutes.states.AguardaJokers;

public class PanelComandosJokers extends PanelBase implements Observer{
    Carta joker;
    
    public PanelComandosJokers(PanelBase owner, DataController controller) {
        super(owner,controller);
        
        Jogador jogador = getJogo().getJogadorActivo();
        
        this.setLayout(new FlowLayout());
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(600,100));
        this.setMinimumSize(new Dimension(600,100));
        this.setMaximumSize(new Dimension(600,100));
        
        if(!getJogo().isJokersAtribuidos())
        {
                    
            if(!jogador.getListaCartaJokers().isEmpty())
            {
              this.add(new JLabel("Escolha de Jokers"), BorderLayout.CENTER);
              this.add(new JLabel("Escolha o Joker:"+ String.format("%s", jogador.getListaCartaJokers().get(0))), BorderLayout.CENTER);
              joker = jogador.getListaCartaJokers().get(0);
              showRecursos();
            } 
            else
            {
                getJogo().passaVez();
            }
        }
        else
        {            
            String msg = "E o VENCEDOR é........ "+String.format("%s",getJogo().getJogadorVencedor().getNome());            
            JOptionPane.showMessageDialog(null,msg,"Vencedor",JOptionPane.WARNING_MESSAGE);
            getJogo().mostraPontuacao();
        }
    }
    
    public void showRecursos() {               
        // Lista de acções da carta seleccionada        
        ButtonRecurso btRecurso;
        Jogador jogador= getJogo().getJogadorActivo();
        Carta carta = jogador.getCartaActiva();
        Recurso itemRecurso;
        
        JPanel coluna = new JPanel();
        coluna.setBackground(this.getBackground());
        coluna.setLayout(new BoxLayout(coluna,BoxLayout.Y_AXIS));
        this.add(coluna, BorderLayout.CENTER);
        
        JPanel panelJogador;
        panelJogador = new JPanel();
        panelJogador.setBackground(coluna.getBackground());
        coluna.add(panelJogador, BorderLayout.EAST); 

        //Alimento
        itemRecurso = new RecursoAlimento();
        btRecurso = addButtonRecurso(panelJogador,itemRecurso);            
        btRecurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clique na recurso               
                Recurso recurso= ((ButtonRecurso)e.getSource()).getRecurso(); 
                
                getJogo().defineRecurso(joker, recurso);
            }
        });
        
        //Ferro
        itemRecurso = new RecursoFerro();
        btRecurso = addButtonRecurso(panelJogador,itemRecurso);            
        btRecurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clique na recurso               
                Recurso recurso= ((ButtonRecurso)e.getSource()).getRecurso(); 
                getJogo().defineRecurso(joker, recurso);
            }
        });

        panelJogador = new JPanel();
        panelJogador.setBackground(coluna.getBackground());
        coluna.add(panelJogador, BorderLayout.CENTER);         
        
        
        //Joia
        itemRecurso = new RecursoJoia();
        btRecurso = addButtonRecurso(panelJogador,itemRecurso);            
        btRecurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clique na recurso               
                Recurso recurso= ((ButtonRecurso)e.getSource()).getRecurso(); 
                getJogo().defineRecurso(joker, recurso);
            }
        });
        
        //Madeira
        itemRecurso = new RecursoMadeira();
        btRecurso = addButtonRecurso(panelJogador,itemRecurso);            
        btRecurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clique na recurso               
                Recurso recurso= ((ButtonRecurso)e.getSource()).getRecurso(); 
                getJogo().defineRecurso(joker, recurso);
            }
        });
        
        panelJogador = new JPanel();
        panelJogador.setBackground(coluna.getBackground());
        coluna.add(panelJogador, BorderLayout.WEST);     
        
        //Utensilios
        itemRecurso = new RecursoUtensilio();
        btRecurso = addButtonRecurso(panelJogador,itemRecurso);            
        btRecurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clique na recurso               
                Recurso recurso= ((ButtonRecurso)e.getSource()).getRecurso(); 
                getJogo().defineRecurso(joker, recurso);
            }
        });
            
        
        
        // Refrescar
        validate();
        repaint();
    }

    protected static Image createImage(String path) {

        BufferedImage imagem = null;
        try {
            imagem = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println(" erro ao carregar as imagens");
        }
        return imagem;
    }
    
    private ButtonRecurso addButtonRecurso(JPanel panel,Recurso recurso)
    {
        ButtonRecurso btRecurso = new ButtonRecurso(recurso);
        
        
        Image imgRecurso = createImage("src/pt/eightminutes/ui/recursos/rec"+String.format("%s",recurso.getNome())+".jpg");
        
        if (imgRecurso != null) {
            // Formatar botão consoante a imagem
            btRecurso.setMargin(new Insets(0,0,0,0));
            btRecurso.setContentAreaFilled(false);
            btRecurso.setFocusPainted(false);
            btRecurso.setBorder(BorderFactory.createEmptyBorder());
            btRecurso.setIcon(new ImageIcon(imgRecurso));
            btRecurso.setText(String.format("Qtd: %d",getJogo().getJogadorActivo().getQtdRecurso(recurso)));
        }
        else
            btRecurso.setText("Recurso "+String.format("%s",recurso.getNome()));

        panel.add(btRecurso);
        panel.add(Box.createRigidArea(new Dimension(15,15)));
        
        return btRecurso;
    }

    @Override
    public void update(Observable o, Object arg) 
    {
        clear();
        showRecursos();
        // Escolha da carta
        if (getJogo().getEstadoActual().getClass() == AguardaJokers.class) {
            setEnabled(true);
        }
        else {
            setEnabled(false);
        }
    }
    
}
