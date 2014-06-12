package pt.eightminutes.logic;

import java.io.Serializable;

public abstract class Peca implements Serializable {
    
    private String nome;
    private Regiao regiao;
    private Jogador jogador;

    public Peca(String nome,Regiao regiao, Jogador jogador) {
        this.nome = nome;
        this.regiao = regiao;
        this.jogador = jogador;
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
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the regiao
     */
    public Regiao getRegiao() {
        return regiao;
    }

    /**
     * @param regiao the regiao to set
     */
    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }

    /**
     * @return the jogador
     */
    public Jogador getJogador() {
        return jogador;
    }

    /**
     * @param jogador the jogador to set
     */
    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }
    
}
