/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.logic;

import java.io.Serializable;
import java.util.ArrayList;

public class Carta implements Serializable{
    
    private int id;
    private Recurso recurso;
    private int qtdRecurso;
    private boolean comprada;
    private boolean executaTodasAccoes;// Carta com duas acções
    private ArrayList<Accao> accoes = new ArrayList<>();
    private Accao accaoActiva;
    
    public Carta(int id, Recurso recurso, int qtdRecurso, boolean execTodas, ArrayList<Accao> accoes) {
        this.id = id;
        this.recurso = recurso;
        this.qtdRecurso = qtdRecurso;
        this.executaTodasAccoes = execTodas;
        this.accoes.addAll(accoes);
    }

    public boolean isTodasAccoesUsadas() {
        boolean myResult = true;
        for (int i = 0; i < accoes.size(); i++){
            if (!accoes.get(i).isUsada())
                myResult = false;
        }
        return myResult;
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
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

    /**
     * @return the qtdRecurso
     */
    public int getQtdRecurso() {
        return qtdRecurso;
    }

    /**
     * @param qtdRecurso the qtdRecurso to set
     */
    public void setQtdRecurso(int qtdRecurso) {
        this.qtdRecurso = qtdRecurso;
    }

    /**
     * @return the executaTodasAccoes
     */
    public boolean isExecutaTodasAccoes() {
        return executaTodasAccoes;
    }

    /**
     * @param executaTodasAccoes the executaTodasAccoes to set
     */
    public void setExecutaTodasAccoes(boolean executaTodasAccoes) {
        this.executaTodasAccoes = executaTodasAccoes;
    }

    /**
     * @return the accoes
     */
    public ArrayList<Accao> getAccoes() {
        return accoes;
    }

    /**
     * @param accoes the accoes to set
     */
    public void setAccoes(ArrayList<Accao> accoes) {
        this.accoes = accoes;
    }
    
    @Override
    public String toString() {
        String s = "";
        s += "  Recurso("+ qtdRecurso +"):"+ getRecurso().getNome() +"\n";        
        s += "  Acções:";        
        for(int i=0;i<getAccoes().size();i++)
        {
            if(getAccoes().get(i)!=null)
                s += "  "+getAccoes().get(i).getNome() +"\n";            
        }
        return s;
    }          

    /**
     * @return the accaoActiva
     */
    public Accao getAccaoActiva() {
        return accaoActiva;
    }

    /**
     * @param accaoActiva the accaoActiva to set
     */
    public void setAccaoActiva(Accao accaoActiva) {
        this.accaoActiva = accaoActiva;
    }

    /**
     * @return the comprada
     */
    public boolean isComprada() {
        return comprada;
    }

    /**
     * @param comprada the comprada to set
     */
    public void setComprada(boolean comprada) {
        this.comprada = comprada;
    }
}
