/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.ui.graphical;

import pt.eightminutes.ui.map.MapData;
import pt.eightminutes.ui.map.IMapData;
import pt.eightminutes.ui.map.MapDataModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;


class MapBackground extends JPanel implements Observer {

    MapDataModel model;
    String overLocation = null;
    Shape highlight = null;

    MapBackground(MapDataModel themodel) {
        themodel.addObserver(this);
        this.model = themodel;
        
        this.setPreferredSize(new Dimension(512,400));
        this.setMinimumSize(new Dimension(512,400));
        this.setMaximumSize(new Dimension(512,400));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent ev){
                model.addNewPoint(getMousePosition());
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent ev) {
                String s = model.getRegion(ev.getPoint());
                if (s != overLocation){
                    overLocation=s;
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

        if (img != null)
            g.drawImage(img, x, y, null);
        else
            g.drawString("Abra uma imagem antes de continuar", x+10, y+10);

        if (overLocation != null) {
            g.drawString(overLocation, x+10, y+10);
        }

        List<Point> lst = model.getPoints();

        if (lst.size() >= 1) {
            Point p1 = lst.get(0);
            int diameter = 10;
            int x1 = (int)(p1.getX());
            int y1 = (int)(p1.getY());
            g.drawOval(x1-diameter/2, y1-diameter/2, diameter, diameter);
        }

        for (int i = 0; i < lst.size()-1; i++)
        {
            Point p1 = lst.get(i);
            Point p2 = lst.get(i+1);

            int x1 = (int)(p1.getX());
            int x2 = (int)(p2.getX());
            int y1 = (int)(p1.getY());
            int y2 = (int)(p2.getY());

            g.drawLine(x1, y1, x2, y2);
            int diameter = 10;
            g.drawOval(x2-diameter/2, y2-diameter/2, diameter, diameter);	
        }

        for (Shape a : model.getRegions())
        {
            Graphics2D g2d = (Graphics2D)g;
            g2d.draw(a);
        }

        if (!model.getRegions().isEmpty())
            highlight = model.getRegions().get(model.getRegions().size()-1);

        if (highlight != null)
        {
            Graphics2D g2d = (Graphics2D)g;
            g2d.fill(highlight);
        }
    }	
}

public class PanelMapa extends JPanel {
    
    private DataController controller;
    
    MapDataModel model = new MapDataModel(new MapData());
    private MapBackground mapPanel;
    
    //private BufferedImage mapa;
    
    public PanelMapa(DataController controller) {
        this.controller = controller;
        
        // Teste
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(600,400));
        this.setMinimumSize(new Dimension(600,400));
        this.setMaximumSize(new Dimension(600,400));
                
        //try {
        //   mapa = ImageIO.read(new File("src/pt/eightminutes/map/Map.png"));
        //} catch (IOException err) {

        //}
        
        //JLabel mapaContainer = new JLabel(new ImageIcon(mapa));
        //add(mapaContainer);
        
        mapPanel = new MapBackground(model);
        
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

}
