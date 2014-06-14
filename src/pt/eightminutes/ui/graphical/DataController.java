package pt.eightminutes.ui.graphical;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import pt.eightminutes.states.EstadoListener;
import pt.eightminutes.logic.*;
import pt.eightminutes.states.AguardaEscolheCarta;

public class DataController extends Observable {
    
    private Jogo jogo;
    
    // Eventos
    final private List<DataControllerListener> persistentListeners = new ArrayList<>();
    final private List<DataControllerListener> listeners = new ArrayList<>();
    
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
    
    public void addListener(DataControllerListener toAdd) {
        listeners.add(toAdd);
    }
    
    public void addPersistentListener(DataControllerListener toAdd) {
        persistentListeners.add(toAdd);
    }
    
    public void clearListeners() {
        listeners.clear();
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
            for (DataControllerListener event : listeners)
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

        // Notifica a todos os listeners o setSelectedRegiao
        for (DataControllerListener event : persistentListeners)
            event.onFocusRegioes();
    }
    
    public void saveJogo(String filePath) throws IOException {
        getJogo().gravaInstanciaJogo(filePath);
    }
    
    public void loadJogo(String filePath) throws FileNotFoundException {
        jogo = getJogo().carregaInstanciaJogo(filePath);
        // Como o jogo foi gravado com o estado OpçõesJogo, é preciso forçar o novo estado        
        jogo.setEstadoActual(new AguardaEscolheCarta(jogo));
        update();
    }
    
}
