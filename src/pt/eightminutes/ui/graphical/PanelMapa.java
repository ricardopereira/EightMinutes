package pt.eightminutes.ui.graphical;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import pt.eightminutes.ui.map.IMapData;
import pt.eightminutes.ui.map.MapDataModel;

import pt.eightminutes.logic.*;
import pt.eightminutes.states.*;

public class PanelMapa extends PanelBase implements Observer {
    
    final private MapDataModel model;
    
    private String nameRegiao = null;
    
    public PanelMapa(PanelBase owner, DataController controller, final MapDataModel model) throws AWTException {
        super(owner,controller);
        
        model.addObserver(this);
        this.model = model;

        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(512,400));
        this.setMinimumSize(new Dimension(512,400));
        this.setMaximumSize(new Dimension(512,400));
                
        // ToDo: Obter caminho pelo getResources
        //poderá causar problemas com a criação do jar final
        loadMap(new File("src/pt/eightminutes/ui/graphical/resources/map/eightminutes.map"));

        // Evento para o clique do rato
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent ev) {
                
                if (!isEnabled())
                    return;
                
                String areaName = model.getRegion(ev.getPoint());
                if (getController().getSelectedRegiao() != null && getController().getSelectedRegiao().getAreaName().equals(areaName))
                    return;
                getController().setSelectedRegiao(getController().getJogo().getMapa().getRegiaoByAreaName(areaName));
                repaint();
            }
        });

        // Evento MouseOver para apresentar nome da região
        addMouseMotionListener(new MouseMotionAdapter() {            
            @Override
            public void mouseMoved(MouseEvent ev) {
                String s = model.getRegion(ev.getPoint());
                if (s != nameRegiao){
                    nameRegiao = s;
                    repaint();
                }
            }
        });
    }
    
    public void loadMap(File f) {
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(f));
            IMapData mr = (IMapData)(oiStream.readObject());
            model.setMapData(mr);
            oiStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        updateJogadores();
        
        // Acção
        if (getJogo().getEstadoActual().getClass() == AguardaColocaCidade.class ||
            getJogo().getEstadoActual().getClass() == AguardaColocaExercito.class ||
            getJogo().getEstadoActual().getClass() == AguardaDestroiExercito.class ||
            getJogo().getEstadoActual().getClass() == AguardaMoveExercito.class ||
            getJogo().getEstadoActual().getClass() == AguardaMoveExercitoAgua.class)
        {
            setEnabled(true);
        }
        else {
            setEnabled(false);
        }
        
        repaint();
    }
    
    public void updateJogadores() {
        
        removeAll();
        
        ArrayList<ButtonPeca> buttons;
        
        Regiao itemRegiao;
        Peca itemPeca;
        Point centerRegiao;
        ButtonPeca currentButton = null;
        
        for (int i = 0; i < getController().getJogo().getMapa().getRegioes().size(); i++) {
            itemRegiao = getController().getJogo().getMapa().getRegioes().get(i);
            
            // Tem peças
            if (itemRegiao.getPecas().isEmpty())
                continue;
            
            buttons = new ArrayList<>();
            
            for (int j = 0; j < itemRegiao.getPecas().size(); j++) {
                itemPeca = itemRegiao.getPecas().get(j);
                
                // Apenas uma marca por jogador na região
                for (int k = 0; k < buttons.size(); k++) {
                    currentButton = buttons.get(k);
                    if (currentButton.getJogador() == itemPeca.getJogador())
                        break;
                    currentButton = null;
                }
                
                if (currentButton == null) {
                    // Nova marca
                    centerRegiao = model.getCenterPoint(itemRegiao.getAreaName());
                    currentButton = new ButtonPeca(itemPeca.getJogador().getIndex(),centerRegiao,ButtonPeca.ButtonPecaType.EXERCITO,itemRegiao,itemPeca.getJogador());
                    buttons.add(currentButton);
                    
                    if (itemPeca.getClass() == Cidade.class) {
                        currentButton.setType(ButtonPeca.ButtonPecaType.CIDADE_SEM_EXERCITOS);
                    }
                    else if (itemPeca.getClass() == Exercito.class) {
                        currentButton.setType(ButtonPeca.ButtonPecaType.EXERCITO);
                    }
                }
                else {
                    // Marca já existente, verificar se precisa de ser actualizado para outro tipo
                    if (currentButton.getType() == ButtonPeca.ButtonPecaType.CIDADE_SEM_EXERCITOS &&
                        itemPeca.getClass() == Exercito.class)
                    {
                        currentButton.setType(ButtonPeca.ButtonPecaType.CIDADE_COM_EXERCITOS);
                    }
                    else if (currentButton.getType() == ButtonPeca.ButtonPecaType.EXERCITO &&
                             itemPeca.getClass() == Cidade.class)
                    {
                        currentButton.setType(ButtonPeca.ButtonPecaType.CIDADE_COM_EXERCITOS);
                    }
                }
            }
            
            // Adicionar marcas à região
            for (int k = 0; k < buttons.size(); k++)
                add(buttons.get(k));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 0, y = 0;
        Image img = model.getMapBackground();

        // Desenha o mapa
        if (img != null)
            g.drawImage(img, x, y, null);
        else
            g.drawString("Sem mapa", x+10, y+10);

        if (nameRegiao != null) {
            g.drawString(nameRegiao, x+10, y+10);
        }

        for (Shape a : model.getRegions())
        {
            //Graphics2D g2d = (Graphics2D)g;
            //g2d.draw(a);
        }
        
        // Região inicial
        Regiao regiaoInicial = getController().getJogo().getMapa().getRegiaoInicial();
        paintRegiao(g,regiaoInicial,new Color(250,229,220));
        
        // Região seleccionada
        paintRegiao(g,getController().getSelectedRegiao(),Color.GREEN);
        
        // Focus nas regiões
        if (getController().getFocusRegioes() != null) {
            for (Regiao focus : getController().getFocusRegioes()) {
                paintRegiao(g,focus,Color.GRAY);
            }
        }
    }
    
    public void paintRegiao(Graphics g, Regiao regiao, Color cor) {
        if (regiao == null) return;
        Shape regionShape = model.getShape(regiao.getAreaName());
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(cor);
        g2d.fill(regionShape);
    }

}
