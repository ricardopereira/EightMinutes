package pt.eightminutes.ui.graphical;

import javax.swing.JButton;
import pt.eightminutes.logic.Accao;

public class ButtonAccao extends JButton {
    
    private Accao accao;
    
    public ButtonAccao(Accao accao) {
        this.accao = accao;
    }

    /**
     * @return the accao
     */
    public Accao getAccao() {
        return accao;
    }

    /**
     * @param accao the accao to set
     */
    public void setAccao(Accao accao) {
        this.accao = accao;
    }

}
