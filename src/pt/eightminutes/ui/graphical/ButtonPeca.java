package pt.eightminutes.ui.graphical;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JButton;

import pt.eightminutes.logic.*;

public class ButtonPeca extends JButton {
    
    public enum ButtonPecaType {
        CIDADE_SEM_EXERCITOS, CIDADE_COM_EXERCITOS, EXERCITO
    }
    
    private ButtonPecaType type;
    private Jogador jogador;
    private Regiao regiao;
    
    public ButtonPeca(int index, Point center, ButtonPecaType type, Regiao regiao, Jogador jogador) {
        
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
        
        this.jogador = jogador;
        this.type = type;
        this.regiao = regiao;
        
        // Posiçao e tamanho
        setBounds(location.x, location.y, diameter, diameter);
                
        setToolTipText("<html>"+jogador.getNome()+
                "<br>Cidades: "+jogador.getListaCidades(regiao).size()+
                "<br>Exércitos: "+jogador.getListaExercitos(regiao).size()+
                "</html>");

        switch (type) {
            case CIDADE_SEM_EXERCITOS:

                break;
            case CIDADE_COM_EXERCITOS:

                break;
            case EXERCITO:

                break;
        }
        
    }
    
    public void paintComponent(Graphics g) {
        if (getJogador() != null)
            g.setColor(getJogador().getCor());
        else
            g.setColor(Color.BLACK);
                
        switch (getType()) {
            case CIDADE_SEM_EXERCITOS:
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
    
    public Jogador getJogador() {
        return jogador;
    }
    
    public ButtonPecaType getType() {
        return type;
    }

    public void setType(ButtonPecaType type) {
        this.type = type;
    }
    
}
