package pt.eightminutes.logic;

import java.io.Serializable;
import java.util.List;

public abstract class Accao extends Base implements Serializable {
    
    private String nome;
    private int qtd;
    private boolean usada;
    private Carta carta;
    
    public Accao(String nome, int qtd){
        this.nome = nome;
        this.qtd = qtd;
    } 

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    protected void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the qtd
     */
    public int getQtd() {
        return qtd;
    }

    /**
     * @param qtd the qtd to set
     */
    public void setQtd(int qtd) {
        this.qtd = qtd;
        if(qtd==0)
            setUsada(true);
    }

    /**
     * @return the usada
     */
    public boolean isUsada() {
        return usada;
    }

    /**
     * @param usada the usada to set
     */
    public void setUsada(boolean usada) {
        this.usada = usada;
    }
    
    @Override
    public String toString() {
        String s = "";
        s += "  "+ getNome()+" "+getQtd()+" vezes";        
        return s;
    }
    
    public abstract int executa(Object sender, List params);
    
    public void decrementaQtd() {
        setQtd(getQtd() - 1);
    }

    /**
     * @return the carta
     */
    public Carta getCarta() {
        return carta;
    }

    /**
     * @param carta the carta to set
     */
    public void setCarta(Carta carta) {
        this.carta = carta;
    }

}
