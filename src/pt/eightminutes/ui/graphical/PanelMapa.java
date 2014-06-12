package pt.eightminutes.ui.graphical;

import java.awt.AWTException;
import pt.eightminutes.ui.map.MapData;
import pt.eightminutes.ui.map.IMapData;
import pt.eightminutes.ui.map.MapDataModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import pt.eightminutes.logic.Exercito;
import pt.eightminutes.logic.Peca;
import pt.eightminutes.logic.Regiao;

import pt.eightminutes.states.*;

// Classe auxiliar para o mapa
class MapBackground extends JPanel implements Observer {

    MapDataModel model;
    String nameRegiao = null;
    
    Robot robot;

    MapBackground(final MapDataModel model) throws AWTException {
        this.robot = new Robot();
        model.addObserver(this);
        this.model = model;
        
        this.setLayout(null);
        this.setPreferredSize(new Dimension(512,400));
        this.setMinimumSize(new Dimension(512,400));
        this.setMaximumSize(new Dimension(512,400));
        
        // Evento para o clique do rato
        addMouseListener(new MouseAdapter()  {
            public void mousePressed(MouseEvent ev) {
                
                //if (!isEnabled())
                //    return;
                
                Point center = ev.getPoint();
                
                center = model.getCenterPoint(model.getRegion(center));
                
                ButtonPeca btPeca;
                // Teste
                btPeca = new ButtonPeca(0,center,ButtonPeca.ButtonPecaType.CIDADE_INICIAL,null);
                add(btPeca);
                btPeca = new ButtonPeca(1,center,ButtonPeca.ButtonPecaType.EXERCITO,null);
                add(btPeca);
                btPeca = new ButtonPeca(2,center,ButtonPeca.ButtonPecaType.CIDADE_COM_EXERCITOS,null);
                add(btPeca);
                btPeca = new ButtonPeca(3,center,ButtonPeca.ButtonPecaType.CIDADE_COM_EXERCITOS,null);
                add(btPeca);
                btPeca = new ButtonPeca(4,center,ButtonPeca.ButtonPecaType.CIDADE_INICIAL,null);
                add(btPeca);
                                
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

    @Override
    public void update(Observable o, Object arg) {
        repaint();
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

        }

    }	
}

public class PanelMapa extends PanelBase implements Observer {
        
    private MapDataModel model = new MapDataModel(new MapData());
    private MapBackground mapPanel;
    
    public PanelMapa(PanelBase owner, DataController controller) {
        super(owner,controller);

        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(600,400));
        this.setMinimumSize(new Dimension(600,400));
        this.setMaximumSize(new Dimension(600,400));
        
        try {
            // Mapa
            mapPanel = new MapBackground(model);
        } catch (AWTException ex) {
            Logger.getLogger(PanelMapa.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        // ToDo: Obter caminho pelo getResources
        //poderá causar problemas com a criação do jar final
        loadMap(new File("src/pt/eightminutes/ui/graphical/resources/map/eightminutes.map"));
                
        add(mapPanel, BorderLayout.CENTER);
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
        
        updateLogic();
        
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
    }
    
    public void updateLogic() {
        
        // Fase de testes
        //ToDo: sem duplicação de dados na parte das regiões do mapa
        
        mapPanel.removeAll();
        
        Regiao regiaoInicial = getController().getJogo().getMapa().getRegiaoInicial();
        Point center = model.getCenterPoint(regiaoInicial.getAreaName());
        Peca itemPeca;
        for (int i = 0; i < regiaoInicial.getPecas().size(); i++) {
            itemPeca = regiaoInicial.getPecas().get(i);
            
            // ToDo: Várias cidades?

            if (itemPeca instanceof Exercito) {
                mapPanel.add(new ButtonPeca(i,center,ButtonPeca.ButtonPecaType.CIDADE_INICIAL,itemPeca.getJogador()));
            }
            
            break;
        }
        
        mapPanel.repaint();
        
    }

}
