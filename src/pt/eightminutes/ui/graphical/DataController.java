package pt.eightminutes.ui.graphical;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import pt.eightminutes.states.EstadoListener;
import pt.eightminutes.logic.*;

public class DataController extends Observable {
    
    private Jogo jogo;
    
    // Eventos
    List<PanelMapaListener> listeners = new ArrayList<PanelMapaListener>();
    
    // Dados partilhados
    private Regiao selectedRegiao = null;
    private ArrayList<Regiao> focusRegioes = null;
    
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
    
    public void addListener(PanelMapaListener toAdd) {
        listeners.add(toAdd);
    }
    
    /**
     * @return the selectedRegiao
     */
    public Regiao getSelectedRegiao() {
        return selectedRegiao;
    }

    /**
     * @param selectedRegiao the selectedRegiao to set
     */
    public void setSelectedRegiao(Regiao selectedRegiao) {
        if (selectedRegiao != this.selectedRegiao) {
            this.selectedRegiao = selectedRegiao;
            
            // Notifica a todos os listeners o setSelectedRegiao
            for (PanelMapaListener event : listeners)
                event.onSelectRegiao();
        }
    }
    
    /**
     * @return the focusRegioes
     */
    public ArrayList<Regiao> getFocusRegioes() {
        return focusRegioes;
    }

    /**
     * @param focusRegioes the focusRegioes to set
     */
    public void setFocusRegioes(ArrayList<Regiao> focusRegioes) {
        this.focusRegioes = focusRegioes;
    }
    
}
