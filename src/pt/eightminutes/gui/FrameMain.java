/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.gui;

import java.awt.BorderLayout;
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
        cp.setLayout(new BorderLayout());
        
        // Zona 1
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setBackground(Color.RED);
        panelCentral.setPreferredSize(new Dimension(800,500));
        panelCentral.setMinimumSize(new Dimension(800,500));
        panelCentral.setMaximumSize(new Dimension(800,500));
        cp.add(panelCentral,BorderLayout.CENTER);
        
        PanelOpcoes panelOpcoes = new PanelOpcoes(controller);
        cp.add(panelOpcoes,BorderLayout.SOUTH);
             
        // Zona 2
        JPanel panelJogo = new JPanel();
        panelJogo.setLayout(new BorderLayout());
        panelJogo.setBackground(Color.PINK);
        panelJogo.setPreferredSize(new Dimension(600,500));
        panelJogo.setMinimumSize(new Dimension(600,500));
        panelJogo.setMaximumSize(new Dimension(600,500));
        panelCentral.add(panelJogo,BorderLayout.CENTER);
        
        PanelInformacao panelInfo = new PanelInformacao(controller);
        panelCentral.add(panelInfo,BorderLayout.EAST);
        
        // Zona 3
        PanelMapa panelMapa = new PanelMapa(controller);
        panelJogo.add(panelMapa,BorderLayout.CENTER);
        
        PanelCartas panelCartas = new PanelCartas(controller);
        panelJogo.add(panelCartas,BorderLayout.SOUTH);
    }
    
    protected void registerListeners()
    {
        
    }
        
    @Override
    public void update(Observable t, Object o) {

    }
    
    //Listeners
    
    
}
