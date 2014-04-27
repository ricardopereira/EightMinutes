/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;


public class Baralho implements Serializable{
    
    private ArrayList<Carta> cartas = new ArrayList<>();
    private ArrayList<Accao> accoesAux = new ArrayList<>();//usado para adicionar acções às cartas
    
    public Baralho(){
        
    }
    
    public Baralho(int numJogadores){
        defineBaralho(numJogadores);
    };
    
    public void defineBaralho(int numJogadores){  
        Recurso recursoJoia = new RecursoJoia();
        Recurso recursoAlimento = new RecursoAlimento();
        Recurso recursoMadeira = new RecursoMadeira();
        Recurso recursoFerro = new RecursoFerro();
        Recurso recursoUtensilio = new RecursoUtensilio();
        Recurso recursoJoker = new RecursoJoker();
        
        // Recurso, qtdRecurso,(and=true, or=false),accao 1, accao 2, numcartas
        adicionaCarta(recursoJoia,1,true,new AccaoColocaExercito(1),null,1);
        adicionaCarta(recursoJoia,1,true,new AccaoColocaExercito(2),null,2);
        adicionaCarta(recursoJoia,1,true,new AccaoMoveExercito(2),null,1);
        if(numJogadores >=5)//não é suposto no máximo existirem 5 jogadores
            adicionaCarta(recursoJoia,1,true,new AccaoMoveExercito(2),null,1);
              
        adicionaCarta(recursoAlimento,1,true,new AccaoColocaCidade(1),null,2);
        adicionaCarta(recursoAlimento,1,true,new AccaoMoveExercito(3),null,1);      
        adicionaCarta(recursoAlimento,2,true,new AccaoMoveExercito(3),null,1);
        adicionaCarta(recursoAlimento,1,true,new AccaoMoveExercito(4),null,2);
        adicionaCarta(recursoAlimento,1,true,new AccaoMoveExercito(5),null,1);
        adicionaCarta(recursoAlimento,1,true,new AccaoMoveExercitoAgua(3),null,1);
        if(numJogadores >=5)//não é suposto no máximo existirem 5 jogadores
            adicionaCarta(recursoAlimento,1,false,new AccaoColocaExercito(4),new AccaoMoveExercito(2),1);        
        adicionaCarta(recursoAlimento,1,true,new AccaoColocaExercito(1),new AccaoDestroiExercito(1),1);
        
        adicionaCarta(recursoMadeira,1,true,new AccaoColocaCidade(1),null,1);
        adicionaCarta(recursoMadeira,1,true,new AccaoColocaExercito(3),null,1);
        adicionaCarta(recursoMadeira,1,true,new AccaoMoveExercito(3),null,1);
        adicionaCarta(recursoMadeira,1,false,new AccaoMoveExercito(2),new AccaoMoveExercito(3),1);// Esta carta nao faz sentido visto que o utilizador pode mover so dois se lhe apetecer
        adicionaCarta(recursoMadeira,1,true,new AccaoMoveExercitoAgua(3),null,1);
        adicionaCarta(recursoMadeira,1,true,new AccaoMoveExercitoAgua(4),null,1);
        if(numJogadores >=5)//não é suposto no máximo existirem 5 jogadores
            adicionaCarta(recursoMadeira,1,true,new AccaoMoveExercito(6),null,1);
        adicionaCarta(recursoMadeira,1,false,new AccaoDestroiExercito(1),new AccaoColocaCidade(1),1);
        
        adicionaCarta(recursoFerro,1,true,new AccaoColocaExercito(2),null,1);
        adicionaCarta(recursoFerro,1,true,new AccaoColocaExercito(3),null,2);
        adicionaCarta(recursoFerro,1,true,new AccaoMoveExercito(2),null,1);
        adicionaCarta(recursoFerro,1,true,new AccaoMoveExercitoAgua(2),null,1);
        if(numJogadores >=5)//não é suposto no máximo existirem 5 jogadores
            adicionaCarta(recursoFerro,1,true,new AccaoMoveExercitoAgua(2),null,1);
        adicionaCarta(recursoFerro,1,true,new AccaoMoveExercitoAgua(3),null,1);
        
        adicionaCarta(recursoUtensilio,1,true,new AccaoColocaCidade(1),null,1);
        adicionaCarta(recursoUtensilio,1,true,new AccaoColocaExercito(3),null,2);
        adicionaCarta(recursoUtensilio,1,true,new AccaoMoveExercitoAgua(3),null,1);
        adicionaCarta(recursoUtensilio,1,true,new AccaoMoveExercito(4),null,1);
        adicionaCarta(recursoUtensilio,1,false,new AccaoColocaExercito(2),new AccaoMoveExercito(3),1);
        adicionaCarta(recursoUtensilio,1,false,new AccaoColocaExercito(2),new AccaoMoveExercito(4),1);
        if(numJogadores >=5)//não é suposto no máximo existirem 5 jogadores
            adicionaCarta(recursoUtensilio,2,true,new AccaoMoveExercito(4),null,1);
        adicionaCarta(recursoUtensilio,1,true,new AccaoMoveExercito(5),null,1);
        
        adicionaCarta(recursoJoker,1,true,new AccaoColocaExercito(2),null,1);
        adicionaCarta(recursoJoker,1,true,new AccaoMoveExercitoAgua(2),null,2);
        
        baralhaCartas();
    }
    
    private void adicionaCarta(Recurso recurso, int qtdRecursos,boolean execTodasAccoes,Accao accao1, Accao accao2, int numCartas){
        accoesAux.clear();      
        accoesAux.add(accao1);
        accoesAux.add(accao2);
        
        for(int i=0;i<numCartas;i++)
            getCartas().add(new Carta(recurso,qtdRecursos,execTodasAccoes,accoesAux));
    }
       
    private void baralhaCartas(){
        Collections.shuffle(getCartas());
    }

    public int getCustoPorIdx(int idx){
        int myCusto = 0;
        
        switch (idx){
            case 0: myCusto = 0;
                    break;
            case 1: myCusto = 1;
                    break;
            case 2: myCusto = 1;
                    break;
            case 3: myCusto = 2;
                    break;
            case 4: myCusto = 2;
                    break;
            case 5: myCusto = 3;
                    break;
            default: myCusto= 0;
                     break;
        }
            
        return myCusto;        
    }
    
    
    /**
     * @return the cartas
     */
    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    /**
     * @param cartas the cartas to set
     */
    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }
    
    @Override
    public String toString() {
        String s = "";
        s += "Número de cartas no Baralho :" + getCartas().size()+"\n";
        for(int i=0;i<getCartas().size();i++)
        {
            s += "  Carta: "+i+"\n Caracteristicas:\n"+
                 "      Recurso:"+getCartas().get(i).getRecurso().getNome() +"\n"+
                 "      Qtd Recurso:"+getCartas().get(i).getQtdRecurso()+"\n";
                    
            for(int m=0;m<getCartas().get(i).getAccoes().size();m++)
            {
               if(getCartas().get(i).getAccoes().get(m) != null) 
                   s+="      Acções:"+getCartas().get(i).getAccoes().get(m).getNome() +"\n";
            }           
        }
        return s;
    }  
}
