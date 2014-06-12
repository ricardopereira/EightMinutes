package pt.eightminutes.logic;

import java.io.Serializable;

public class Exercito extends Peca{

    public Exercito(Jogador jogador) {
        super("Exercito", null, jogador);
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
        Regiao regiao = this.getRegiao();       
        setRegiao(null);        
        regiao.getPecas().remove(this);
    }
        
}
