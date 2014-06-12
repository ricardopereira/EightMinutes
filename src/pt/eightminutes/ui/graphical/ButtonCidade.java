package pt.eightminutes.ui.graphical;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;

public class ButtonCidade extends JButton {
    
    public void paintComponent(Graphics g)
    {
        //Graphics2D g2D = (Graphics2D) g;     
 
        //g2D.setColor(Color.BLUE);
        //g2D.setStroke(new BasicStroke(2F));  // set stroke width of 10
        //g2D.drawOval(getHorizontalAlignment(), getVerticalAlignment(), getWidth(), getHeight());
        
        g.setColor(Color.BLUE);
        g.drawOval(getHorizontalAlignment(), getVerticalAlignment(), getWidth()-1, getHeight()-1);
        //g.fillOval
    }
    
}
