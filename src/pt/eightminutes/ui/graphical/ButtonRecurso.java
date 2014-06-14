package pt.eightminutes.ui.graphical;

import javax.swing.JButton;
import pt.eightminutes.logic.Recurso;

public class ButtonRecurso extends JButton {
    
    private Recurso recurso;
    
    public ButtonRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    /**
     * @return the recurso
     */
    public Recurso getRecurso() {
        return recurso;
    }

    /**
     * @param recurso the recurso to set
     */
    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

}
