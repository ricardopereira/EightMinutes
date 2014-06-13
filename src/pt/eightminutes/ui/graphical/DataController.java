package pt.eightminutes.ui.graphical;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import pt.eightminutes.states.EstadoListener;
import pt.eightminutes.logic.Jogo;

public class DataController extends Observable {
    
    private Jogo jogo;
    
    final private class updateOnSetEstado implements EstadoListener {
        @Override
        public void onSetEstado() {
            update();
        }
    }
    
    public DataController(Jogo jogo) {
        this.jogo = jogo;        
        // Ao mudar de estado no jogo, irá refrescar o ambiente gráfico
        this.jogo.addListener(new updateOnSetEstado());
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
