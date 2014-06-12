package pt.eightminutes.ui.graphical;

import javax.swing.JButton;
import pt.eightminutes.logic.Carta;

public class ButtonCarta extends JButton {
    
    private int index;
    private Carta carta;
    
    public ButtonCarta(Carta carta) {
        this.carta = carta;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

}
