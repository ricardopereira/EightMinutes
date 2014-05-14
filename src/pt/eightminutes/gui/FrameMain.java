/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;

/**
 *
 * @author ricardopereira
 */
public class FrameMain extends JFrame {
    
    private ModeloGrafico model;
    private Container mainContainer;
    
    public FrameMain(ModeloGrafico model)
    {
        this(model, 150, 150, 800, 600);
    }
    
    public FrameMain(ModeloGrafico model, int x, int y, int width, int height)
    {
        // Cabeçalho
        super("Eight Minutes");
        this.model = model;
        
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
        //
    }
    
    protected void createLayout()
    {
        
    }
    
    protected void registerListeners()
    {
        
    }
    
}
