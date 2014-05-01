/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.logic;

import java.io.Serializable;


public class Exercito extends Peca{

    public Exercito(Cor cor) {
        super("Exercito", null,cor);
    }
    
    public void moveExercito(Regiao regiao){
        setRegiao(regiao);
        regiao.getPecas().add(this);
    }
    
    public void colocaExercito(Regiao regiao){        
        setRegiao(regiao);
        regiao.getPecas().add(this);        
    }
    
    public void destroiExercito(){
        this.getRegiao().getPecas().remove(this);
        setRegiao(null);        
    }
        
}
