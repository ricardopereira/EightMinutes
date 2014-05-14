/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

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
        
        // Container desta frame
        Container cp = getContentPane();
        cp.add(panelMapa);
    }
    
    protected void registerListeners()
    {
        
    }
        
    @Override
    public void update(Observable t, Object o) {

    }
    
    //Listeners
    
    
}
