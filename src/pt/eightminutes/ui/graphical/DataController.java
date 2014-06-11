package pt.eightminutes.ui.graphical;

import java.io.IOException;
import java.util.Observable;
import pt.eightminutes.logic.Jogo;

public class DataController extends Observable {
    
    private Jogo jogo;
    
    public DataController(Jogo jogo) {
        this.jogo = jogo;
    }

    public Jogo getJogo() {
        return jogo;
    }
    
    protected void init() {
        // Carregar todas as dependências do jogo
        try {
            jogo.verificarDependencias();
        } catch (IOException e) {
            System.err.println(e);
        }
        reinit();
    }
    
    protected void reinit() {
        // Iniciar estado
        jogo.opcoesJogo();
        // Novos dados
        setChanged();
    }
    
    public void update() {
        setChanged();
        notifyObservers();
    }
    
}
