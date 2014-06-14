package pt.eightminutes.ui.graphical;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import pt.eightminutes.states.AguardaEscolheCarta;

import pt.eightminutes.ui.map.MapData;
import pt.eightminutes.ui.map.MapDataModel;

public class FrameMain extends JFrame implements Observer {
    
    private DataController controller;
    private Container mainContainer;
    
    // Componentes
    private JMenuBar mainMenuBar;
    private JMenu menuFicheiro;
    private JMenuItem menuItemGravarJogo;
    private JMenuItem menuItemCarregarJogo;
    
    // Paineis
    private PanelMapa panelMapa;
    private PanelCartas panelCartas;
    private PanelInformacao panelInfo;
    private PanelComandos panelOpcoes;
    
    private static Font typo = new Font("Verdana", Font.PLAIN, 12);
    
    
    public FrameMain(DataController controller)
    {
        this(controller, 150, 0, 1000, 850);
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
    
    private MapDataModel model = new MapDataModel(new MapData());
    
    protected void init()
    {
        controller.init();
        
        // Zonas
        try {
            // Mapa
            panelMapa = new PanelMapa(null,controller,model);
        } catch (AWTException ex) {
            Logger.getLogger(PanelMapa.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Restante zonas
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

        menuItemGravarJogo = new JMenuItem("Gravar jogo", KeyEvent.VK_G);
        menuItemGravarJogo.setFont(typo);
        menuItemGravarJogo.setMnemonic(KeyEvent.VK_L);

        menuItemCarregarJogo = new JMenuItem("Carregar jogo");
        menuItemCarregarJogo.setFont(typo);
        
        setJMenuBar(mainMenuBar);
        mainMenuBar.add(menuFicheiro);
        menuFicheiro.add(menuItemGravarJogo);
        menuFicheiro.add(menuItemCarregarJogo);
        
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
        JPanel map = new JPanel();
        map.setBackground(panelMapa.getBackground());
        map.add(panelMapa, BorderLayout.CENTER);
        panelJogo.add(map, BorderLayout.CENTER);
        // Cartas
        panelJogo.add(panelCartas,BorderLayout.SOUTH);
    }
    
    protected void registerListeners()
    {
        menuItemGravarJogo.addActionListener(saveJogoListener);
        menuItemCarregarJogo.addActionListener(loadJogoListener);
    }
        
    @Override
    public void update(Observable t, Object o) {

    }
    
    //Listeners
    final private ActionListener saveJogoListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (controller.getJogo().getEstadoActual().getClass() != AguardaEscolheCarta.class) {
                JOptionPane.showMessageDialog(null,"Só é possível gravar na escolha de uma carta.","8 Minutes",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Gravar jogo");
            int result = jfc.showSaveDialog(FrameMain.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    controller.saveJogo(jfc.getSelectedFile().getPath());
                    JOptionPane.showMessageDialog(null,"Gravado com sucesso.","8 Minutes",JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    Logger.getLogger(FrameMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    };
    
    final private ActionListener loadJogoListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {            
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Carregar jogo");
            int result = jfc.showOpenDialog(FrameMain.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    controller.loadJogo(jfc.getSelectedFile().getPath());
                    JOptionPane.showMessageDialog(null,"Carregado com sucesso.","8 Minutes",JOptionPane.INFORMATION_MESSAGE);                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FrameMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    };
    
}
