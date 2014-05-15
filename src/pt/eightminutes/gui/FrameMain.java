/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author ricardopereira
 */
public class FrameMain extends JFrame implements Observer {
    
    private DataController controller;
    private Container mainContainer;
    
    // Componentes
    private JMenuBar mainMenuBar;
    private JMenu menuFicheiro;
    private JMenuItem menuItemGravar;
    
    // Paineis
    private PanelMapa panelMapa;
    private PanelCartas panelCartas;
    
    private static Font typo = new Font("Verdana", Font.PLAIN, 12);
    
    
    public FrameMain(DataController controller)
    {
        this(controller, 150, 150, 800, 600);
    }
    
    public FrameMain(DataController controller, int x, int y, int width, int height)
    {
        // Cabeçalho
        super("Eight Minutes Empire");
        this.controller = controller;
        
        mainContainer = getContentPane();
        
        initialize();
        createLayout();
        registerListeners();
        
        setLocation(x,y);
        setSize(width, height);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        validate();
    }
    
    protected void initialize()
    {
        mainMenuBar = new JMenuBar();
        
        // ToDo: criar classe para Menu?
        menuFicheiro = new JMenu("Ficheiro");
        menuFicheiro.setFont(typo);
        menuFicheiro.setMnemonic(KeyEvent.VK_F);

        menuItemGravar = new JMenuItem("Gravar jogo", KeyEvent.VK_G);
        menuItemGravar.setFont(typo);
        menuItemGravar.setMnemonic(KeyEvent.VK_L);
        
        // Zonas
        panelMapa = new PanelMapa(controller);
        panelCartas = new PanelCartas(controller);
    }
    
    protected void createLayout()
    {
        setJMenuBar(mainMenuBar);
        mainMenuBar.add(menuFicheiro);
        menuFicheiro.add(menuItemGravar);
        
        Container cp = getContentPane();
        cp.setLayout(new BoxLayout(cp,BoxLayout.Y_AXIS));
        
        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(Color.RED);
        cp.add(panelCentral);
        
        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBottom.setBackground(Color.BLUE);
        //ToDo: panelBottom.setMinimumSize(new Dimension(0, 50));
        cp.add(panelBottom);
        
        // Central
        panelCentral.setLayout(new BoxLayout(panelCentral,BoxLayout.X_AXIS));        
        
        JPanel panelJogo = new JPanel();
        panelJogo.setBackground(Color.YELLOW);
        panelCentral.add(panelJogo);
        
        JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.LEADING));
        panelInfo.setBackground(Color.GREEN);
        panelCentral.add(panelInfo);
        
        // Jogo
        panelJogo.setLayout(new BoxLayout(panelJogo,BoxLayout.Y_AXIS));
        
        JPanel panelMapa = new JPanel();
        panelMapa.setBackground(Color.ORANGE);
        panelJogo.add(panelMapa);
        
        JPanel panelCartas = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCartas.setBackground(Color.LIGHT_GRAY);
        //panelCartas.setMinimumSize(new Dimension(0, 100));
        panelJogo.add(panelCartas);
                
        // Teste
        panelCartas.add(Box.createRigidArea(new Dimension(10,0)));
        panelCartas.add(new JButton("Carta"));
        panelCartas.add(Box.createRigidArea(new Dimension(10,0)));
        panelCartas.add(new JButton("Carta"));
        panelCartas.add(Box.createRigidArea(new Dimension(10,0)));
        panelCartas.add(new JButton("Carta"));
        panelCartas.add(Box.createRigidArea(new Dimension(10,0)));
        panelCartas.add(new JButton("Carta"));
        panelCartas.add(Box.createRigidArea(new Dimension(10,0)));
        panelCartas.add(new JButton("Carta"));
        panelCartas.add(Box.createRigidArea(new Dimension(10,0)));
        panelCartas.add(new JButton("Carta"));
        
        // Container desta frame
        //Container cp = getContentPane();
        //cp.add(new JButton("Teste"));
    }
    
    protected void registerListeners()
    {
        
    }
        
    @Override
    public void update(Observable t, Object o) {

    }
    
    //Listeners
    
    
}
