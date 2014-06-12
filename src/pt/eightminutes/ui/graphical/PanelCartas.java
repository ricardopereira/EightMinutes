package pt.eightminutes.ui.graphical;

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
import javax.swing.JPanel;

import pt.eightminutes.logic.*;
import pt.eightminutes.states.AguardaEscolheCarta;
import pt.eightminutes.states.AguardaOpcoesJogo;

public class PanelCartas extends PanelBase implements Observer {
        
    public PanelCartas(PanelBase owner, DataController controller) {
        super(owner,controller);
        
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(600,100));
        this.setMinimumSize(new Dimension(600,100));
        this.setMaximumSize(new Dimension(600,100));
        
        showCartas();
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
    
    private ButtonCarta addButtonCarta(Carta carta)
    {
        ButtonCarta btCarta = new ButtonCarta(carta);
        
        // Nome da carta consoante o ID
        
        
        
        Image imgCarta = createImage("src/pt/eightminutes/ui/cards/card"+String.format("%03d",carta.getId())+".jpg");
        
        if (imgCarta != null) {
            // Formatar botão consoante a imagem
            btCarta.setMargin(new Insets(0,0,0,0));
            btCarta.setContentAreaFilled(false);
            btCarta.setFocusPainted(false);
            btCarta.setBorder(BorderFactory.createEmptyBorder());
            btCarta.setIcon(new ImageIcon(imgCarta));
        }
        else
            btCarta.setText("Carta "+String.format("%03d",carta.getId()));

        this.add(btCarta);
        this.add(Box.createRigidArea(new Dimension(15,15)));
        
        return btCarta;
    }
    
    public void showCartas() {        
        if (getJogo().getCartasViradas().isEmpty())
            return;
        
        // Cartas viradas
        Carta itemCarta;
        ButtonCarta btCarta;
        for (int i = 0; i < getJogo().getCartasViradas().size(); i++) {
            itemCarta = getJogo().getCartasViradas().get(i);
            // Cria botão
            btCarta = addButtonCarta(itemCarta);
            btCarta.setIndex(i);
            btCarta.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Clique na carta
                    int indexCarta = ((ButtonCarta)e.getSource()).getIndex();
                    System.out.println("Carta seleccionada " + indexCarta);
                    getJogo().escolheCarta(indexCarta);
                }
            });
        }
        
        // Refrescar
        validate();
        repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        // Limpar todas as cartas da mesa
        clear();
        showCartas();
        // Escolha da carta
        if (getJogo().getEstadoActual().getClass() == AguardaEscolheCarta.class) {
            setEnabled(true);
        }
        else {
            setEnabled(false);
        }
    }

}
