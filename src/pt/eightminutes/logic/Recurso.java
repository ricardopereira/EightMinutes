package pt.eightminutes.logic;

import java.io.Serializable;

public abstract class Recurso implements Serializable {
    
    private String nome;
    
    public Recurso(String nome){
        this.nome = nome;
    }
    
    public abstract int getPontuacao(int numCartas);

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
