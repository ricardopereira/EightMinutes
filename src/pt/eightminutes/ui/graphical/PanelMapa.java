package pt.eightminutes.ui.graphical;

import java.awt.AWTException;
import pt.eightminutes.ui.map.MapData;
import pt.eightminutes.ui.map.IMapData;
import pt.eightminutes.ui.map.MapDataModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
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
import javax.swing.JButton;
import javax.swing.JPanel;

import pt.eightminutes.states.*;

// Classe auxiliar para o mapa
class MapBackground extends JPanel implements Observer {

    MapDataModel model;
    String overLocation = null;
    String selected = null;
    Shape highlight = null;
    
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
                Point center = ev.getPoint();
                String region = model.getRegion(center);
                
                Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
                Color color = robot.getPixelColor(mouseLocation.x, mouseLocation.y);
                System.out.println("Cor "+color.getRed()+"."+color.getGreen()+"."+color.getBlue());
                
                ButtonCidade btnAddFlight = new ButtonCidade();
                btnAddFlight.setBounds(center.x, center.y, 10, 10);
                btnAddFlight.setToolTipText("<html>This is the first line<br>This is the second line</html>");
                //btnAddFlight.setBackground(Color.GREEN);
                //btnAddFlight.setOpaque(true);
                //btnAddFlight.setBorderPainted(false);
                //btnAddFlight.setEnabled(false);
                add(btnAddFlight);
                
                highlight = null;
                for (Shape a: model.getRegions())
                    if (a.contains(center))
                        highlight = a;
                
                repaint();
            }
        });

        // Evento MouseOver para apresentar nome da região
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent ev) {
                String s = model.getRegion(ev.getPoint());
                if (s != overLocation){
                    overLocation = s;
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

        if (overLocation != null) {
            g.drawString(overLocation, x+10, y+10);
        }

        for (Shape a : model.getRegions())
        {
            Graphics2D g2d = (Graphics2D)g;
            //g2d.draw(a);
        }

        //if (!model.getRegions().isEmpty())
        //    highlight = model.getRegions().get(model.getRegions().size()-1);
        
        if (highlight != null)
        {
            //Graphics2D g2d = (Graphics2D)g;
            //g2d.fill(highlight);
            
            if (selected == null)
                selected = overLocation;
            
            if (selected != null && !selected.equals("")) {
                Point center = model.getCenterPoint(selected);
                g.setColor(Color.GREEN);
                int cx = (int)center.getX();
                int cy = (int)center.getY();
                g.fillOval(cx-5,cy-5,10,10);
            }
            
            //g.setColor(Color.GREEN);
            //int cx = (int)center.getX();
            //int cy = (int)center.getY();
            //g.fillOval(cx-5,cy-5,10,10);
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

}
