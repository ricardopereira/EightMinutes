package pt.eightminutes.ui.graphical;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JButton;

import pt.eightminutes.logic.Peca;

public class ButtonPeca extends JButton {
    
    public enum ButtonPecaType {
        CIDADE_INICIAL, CIDADE_COM_EXERCITOS, EXERCITO
    }
    
    private ButtonPecaType type;
    private Peca peca;    
    
    public ButtonPeca(int index, Point center, ButtonPecaType type, Peca peca) {
        
        Point location = null;
        
        final int diameter = 10;
        final int radius = diameter/2;
        final int margin = 0;
        
        switch (index) {
            case 0: //Posicao 1
                location = new Point(center.x-diameter-margin,center.y-diameter-margin);
                break;
            case 1: //Posicao 2
                location = new Point(center.x-diameter-margin,center.y+diameter+margin);
                break;
            case 2: //Posicao 3
                location = new Point(center.x+diameter+margin,center.y-diameter-margin);
                break;
            case 3: //Posicao 4
                location = new Point(center.x+diameter+margin,center.y+diameter+margin);
                break;
            case 4: //Posicao 5
                location = new Point(center.x,center.y);
                break;
            default:
                // Inválido
                System.out.println("ButtonPeca: index inválido");
                return;
        }
        
        if (location == null) {
            System.out.println("ButtonPeca: index inválido");
            return;
        }
        
        this.peca = peca;
        this.type = type;
        
        // Posiçao e tamanho
        setBounds(location.x, location.y, diameter, diameter);
        
        switch (type) {
            case CIDADE_INICIAL:
                setToolTipText("<html>Cidade Inicial<br>Sem informação</html>");
                break;
            case CIDADE_COM_EXERCITOS:
                setToolTipText("<html>Cidade com Exércitos<br>Sem informação</html>");
                break;
            case EXERCITO:
                setToolTipText("<html>Exército<br>Sem informação</html>");
                break;
        }
        
    }
    
    public void paintComponent(Graphics g) {
        if (peca != null)
            g.setColor(peca.getJogador().getCor());
        else
            g.setColor(Color.RED);
                
        switch (type) {
            case CIDADE_INICIAL:
                g.drawOval(getHorizontalAlignment(), getVerticalAlignment(), getWidth()-1, getHeight()-1);
                break;
            case CIDADE_COM_EXERCITOS:
                g.fillOval(getHorizontalAlignment(), getVerticalAlignment(), getWidth(), getHeight());
                break;
            case EXERCITO:
                g.fillRect(getHorizontalAlignment(), getVerticalAlignment(), getWidth(), getHeight());
                break;
        }
    }
    
}
