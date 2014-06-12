package pt.eightminutes.ui.graphical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import pt.eightminutes.logic.Regiao;

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
    private PanelInformacao panelInfo;
    private PanelComandos panelOpcoes;
    
    private static Font typo = new Font("Verdana", Font.PLAIN, 12);
    
    
    public FrameMain(DataController controller)
    {
        this(controller, 150, 150, 1000, 800);
    }
    
    public FrameMain(DataController controller, int x, int y, int width, int height)
    {
        // Cabeçalho
        super("Eight Minutes Empire");
        this.controller = controller;
        
        mainContainer = getContentPane();
        
        init();
        validate();
        
        setLocation(x,y);
        setSize(width, height);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // READY
        controller.notifyObservers();
    }
    
    protected void init()
    {
        controller.init();
        
        // Zonas
        panelMapa = new PanelMapa(null,controller);
        panelCartas = new PanelCartas(null,controller);
        panelInfo = new PanelInformacao(null,controller);
        panelOpcoes = new PanelComandos(null,controller);
        
        // Observers
        controller.addObserver(panelMapa);
        controller.addObserver(panelCartas);
        controller.addObserver(panelInfo);
        controller.addObserver(panelOpcoes);
        
        // Layout
        createLayout();
        // Listeners
        registerListeners();
    }
    
    protected void createLayout()
    {
        mainMenuBar = new JMenuBar();
        
        // ToDo: criar classe para Menu?
        menuFicheiro = new JMenu("Ficheiro");
        menuFicheiro.setFont(typo);
        menuFicheiro.setMnemonic(KeyEvent.VK_F);

        menuItemGravar = new JMenuItem("Gravar jogo", KeyEvent.VK_G);
        menuItemGravar.setFont(typo);
        menuItemGravar.setMnemonic(KeyEvent.VK_L);

        setJMenuBar(mainMenuBar);
        mainMenuBar.add(menuFicheiro);
        menuFicheiro.add(menuItemGravar);
        
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        
        // Central
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setBackground(Color.RED);
        panelCentral.setPreferredSize(new Dimension(800,500));
        panelCentral.setMinimumSize(new Dimension(800,500));
        panelCentral.setMaximumSize(new Dimension(800,500));
        cp.add(panelCentral,BorderLayout.CENTER);
        
        // Rodapé
        cp.add(panelOpcoes,BorderLayout.SOUTH);
             
        // Central - mapa e cartas viradas
        JPanel panelJogo = new JPanel();
        panelJogo.setLayout(new BorderLayout());
        panelJogo.setBackground(Color.PINK);
        panelJogo.setPreferredSize(new Dimension(600,500));
        panelJogo.setMinimumSize(new Dimension(600,500));
        panelJogo.setMaximumSize(new Dimension(600,500));
        panelCentral.add(panelJogo,BorderLayout.CENTER);
        
        // Informação da direita
        panelCentral.add(panelInfo,BorderLayout.EAST);
        
        // Mapa
        panelJogo.add(panelMapa,BorderLayout.CENTER);
        // Cartas
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
