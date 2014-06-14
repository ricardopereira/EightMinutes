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
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import pt.eightminutes.logic.Accao;
import pt.eightminutes.logic.Carta;
import pt.eightminutes.logic.Jogador;
import pt.eightminutes.states.AguardaEscolheAccao;
import static pt.eightminutes.ui.graphical.PanelCartas.createImage;

public class PanelComandosEscolheAccao extends PanelBase implements Observer{
    
    public PanelComandosEscolheAccao(PanelBase owner, DataController controller) {
        super(owner,controller);
        
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(600,100));
        this.setMinimumSize(new Dimension(600,100));
        this.setMaximumSize(new Dimension(600,100));
        
        String tipo;
        if (getJogo().getJogadorActivo().getCartaActiva().isExecutaTodasAccoes())
            tipo = "Escolha uma acção(E)";
        else
            tipo = "Escolha uma das acções(OU)";
        this.add(new JLabel(tipo), BorderLayout.CENTER);
        showAccoes();
        
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
        
        this.add(btPassaVez, BorderLayout.CENTER);
    }
    
    public void showAccoes() {               
        // Lista de acções da carta seleccionada        
        ButtonAccao btAccao;
        Jogador jogador= getJogo().getJogadorActivo();
        Carta carta = jogador.getCartaActiva();
        Accao itemAccao;
        
        for(int i=0;i<carta.getAccoes().size();i++)
        {       
            itemAccao = carta.getAccoes().get(i);
            // Cria botão
            if(!itemAccao.isUsada())
            {
                btAccao = addButtonAccao(itemAccao);            
                btAccao.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Clique na carta
                        Accao accao= ((ButtonAccao)e.getSource()).getAccao();                    
                        getJogo().escolheAccao(accao);
                    }
                });
            }
        }
        
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
    
    private ButtonAccao addButtonAccao(Accao accao)
    {
        ButtonAccao btAccao = new ButtonAccao(accao);
        
        
        Image imgAccao = createImage("src/pt/eightminutes/ui/actions/action"+String.format("%s",accao.getNome())+".jpg");
        
        if (imgAccao != null) {
            // Formatar botão consoante a imagem
            btAccao.setMargin(new Insets(0,0,0,0));
            btAccao.setContentAreaFilled(false);
            btAccao.setFocusPainted(false);
            btAccao.setBorder(BorderFactory.createEmptyBorder());
            btAccao.setIcon(new ImageIcon(imgAccao));
        }
        else
            btAccao.setText("Acção "+String.format("%s",accao.getNome()));

        this.add(btAccao);
        this.add(Box.createRigidArea(new Dimension(15,15)));
        
        return btAccao;
    }

    @Override
    public void update(Observable o, Object arg) 
    {
        clear();
        showAccoes();
        // Escolha da carta
        if (getJogo().getEstadoActual().getClass() == AguardaEscolheAccao.class) {
            setEnabled(true);
        }
        else {
            setEnabled(false);
        }
    }
}
