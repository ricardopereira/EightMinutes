package pt.eightminutes.logic;

import java.io.Serializable;
import java.util.ArrayList;

public class Regiao implements Serializable {
    
    private String nome;
    private int mapIndex;
    private Continente continente;
    private ArrayList<Regiao> regioesVizinhas = new ArrayList<>();
    private ArrayList<Peca> pecas = new ArrayList<>();
    
    public Regiao(String nome, int mapIndex, Continente continente){
        this.nome = nome;
        this.mapIndex = mapIndex;
        this.continente = continente;
    }
    
    public void adicionaRegiaoVizinha(ArrayList<Regiao> regioes){
        //fica assim até se decidir como definir quais são as regioes vizinhas
        for(int i=0;i<regioes.size();i++)
        {
            regioesVizinhas.add(regioes.get(i));
        }
    }
        
    public Jogador getJogadorControlaRegiao(Jogo jogo){
        int myQtdExerc = 0;
        int myQtdMax = 0;
        Jogador jogadorControla = null;
        
        for (int m = 0; m < jogo.getJogadores().size(); m++) {
            myQtdExerc = 0;
            for (int i = 0; i < getPecas().size(); i++) {
                if (getPecas().get(i) instanceof Exercito) {
                    if (getPecas().get(i).getJogador() == jogo.getJogadores().get(m)) {
                        myQtdExerc++;
                    }
                }
            }
            if (myQtdExerc > myQtdMax) {
                // se tiver mais do que o ultimo verificado passa a ser o jogador que controla a regiao
                jogadorControla = jogo.getJogadores().get(m);
            }
            else
            if (myQtdExerc == myQtdMax) {
                //se tiver o mesmo numero do jogador que controla deixam os dois de controlar
                jogadorControla = null;
            }
        }
        
        return jogadorControla;
    }
    
    public boolean RegiaoTemExercitosDoJogador(Jogador jogador){
        boolean myResult = false;
        for (int i = 0; i < getPecas().size(); i++) {
            if (getPecas().get(i).getClass() == Exercito.class &&
                getPecas().get(i).getJogador() == jogador) {
                myResult = true;
                break;
            }
        }

        return myResult;        
    }
    
    public boolean RegiaoTemCidadesDoJogador(Jogador jogador){
        boolean myResult = false;
        for (int i = 0; i < getPecas().size(); i++) {
            if (getPecas().get(i).getClass() == Cidade.class &&
                getPecas().get(i).getJogador() == jogador) {
                myResult = true;
                break;
            }
        }

        return myResult;        
    }
    
    public void getExercitosByJogador(Jogador jogador,ArrayList<Exercito> exercitoList){
        boolean myResult = false;
        for (int i = 0; i < getPecas().size(); i++) 
        {
            if (getPecas().get(i).getJogador() == jogador) 
            {
                if(getPecas().get(i) instanceof Exercito)
                    exercitoList.add((Exercito)getPecas().get(i));
            }
        }        
    }
    
    public String getAreaName() {
        return getContinente().getNome() + "-" + getNome();
    }
    
    /**
     * @return the continente
     */
    public Continente getContinente() {
        return continente;
    }

    /**
     * @param continente the continente to set
     */
    public void setContinente(Continente continente) {
        this.continente = continente;
    }
    
    /**
     * @return the cidades
     */
    public ArrayList<Cidade> getCidades() {
        ArrayList<Cidade> list = new ArrayList<>();
        
        for (Peca item : getPecas()) {
            if (item.getClass() == Cidade.class)
                list.add((Cidade)item);
        }
        
        return list;
    }
    
    /**
     * @return the jogadores
     */
    public ArrayList<Jogador> getJogadores() {
        ArrayList<Jogador> list = new ArrayList<>();
        
        boolean jaExiste = false;
        for (Peca peca : getPecas()) {
            if (list.isEmpty())
                list.add(peca.getJogador());
            else {
                jaExiste = false;
                for (Jogador jogador : list) {
                    if (peca.getJogador() == jogador) {
                        jaExiste = true;
                        break;
                    }
                }
                if (!jaExiste)
                    list.add(peca.getJogador());
            }
        }
        
        return list;
    }
    
    /**
     * @return the exercitos
     */
    public ArrayList<Exercito> getExercitos() {
        ArrayList<Exercito> list = new ArrayList<>();
        
        for (Peca item : getPecas()) {
            if (item.getClass() == Exercito.class)
                list.add((Exercito)item);
        }
        
        return list;
    }
    
    /**
     * @return the exercitos
     */
    public ArrayList<Exercito> getExercitosInimigos(Jogador jogador) {
        ArrayList<Exercito> list = new ArrayList<>();
        if (jogador == null)
            return list;
        
        for (Peca item : getPecas()) {
            if (item.getJogador() != jogador && item.getClass() == Exercito.class)
                list.add((Exercito)item);
        }
        
        return list;
    }

    /**
     * @return the pecas
     */
    public ArrayList<Peca> getPecas() {
        return pecas;
    }

    /**
     * @param pecas the pecas to set
     */
    public void setPecas(ArrayList<Peca> pecas) {
        this.pecas = pecas;
    }

    /**
     * @return the regioesVizinhas
     */
    public ArrayList<Regiao> getRegioesVizinhas() {
        return regioesVizinhas;
    }

    /**
     * @param regioes the regioesVizinhas to set
     */
    public void setRegioesVizinhas(ArrayList<Regiao> regioesVizinhas) {
        this.regioesVizinhas = regioesVizinhas;
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
    
    public int getMapIndex() {
        return mapIndex;
    }
    
    public int getIndex() {
        return mapIndex-1;
    }
    
    @Override
    public String toString() {
        String s = "";
        s+=" "+getNome()+"\n";        
        return s;
    } 
}
