package pt.eightminutes.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Baralho implements Serializable {
    
    private ArrayList<Carta> cartas = new ArrayList<>();
    private ArrayList<Accao> accoesAux = new ArrayList<>();//usado para adicionar acções às cartas
    
    public Baralho() {
        
    }
    
    public Baralho(int numJogadores) {
        defineBaralho(numJogadores);
    };
    
    public void defineBaralho(int numJogadores) {
        Recurso recursoJoia = new RecursoJoia();
        Recurso recursoAlimento = new RecursoAlimento();
        Recurso recursoMadeira = new RecursoMadeira();
        Recurso recursoFerro = new RecursoFerro();
        Recurso recursoUtensilio = new RecursoUtensilio();
        Recurso recursoJoker = new RecursoJoker();
        
        // Recurso, qtdRecurso,(and=true, or=false),accao 1, accao 2, numcartas
        adicionaCarta(28,recursoJoia,1,true,new AccaoColocaExercito(1),null,1);
        adicionaCarta(26,recursoJoia,1,true,new AccaoColocaExercito(2),null,2); //27
        adicionaCarta(7,recursoJoia,1,true,new AccaoMoveExercito(2),null,1);
        if (numJogadores >= 5)//não é suposto no máximo existirem 5 jogadores
            adicionaCarta(33,recursoJoia,1,true,new AccaoMoveExercito(2),null,1);
              
        adicionaCarta(3,recursoAlimento,1,true,new AccaoColocaCidade(1),null,2); //39
        adicionaCarta(35,recursoAlimento,1,true,new AccaoColocaExercito(3),null,1);
        adicionaCarta(37,recursoAlimento,2,true,new AccaoColocaExercito(3),null,1);
        adicionaCarta(11,recursoAlimento,1,true,new AccaoMoveExercito(4),null,2); //41
        adicionaCarta(17,recursoAlimento,1,true,new AccaoMoveExercito(5),null,1);
        adicionaCarta(5,recursoAlimento,1,true,new AccaoMoveExercitoAgua(3),null,1);
        if (numJogadores >= 5)//não é suposto no máximo existirem 5 jogadores
            adicionaCarta(32,recursoAlimento,1,false,new AccaoColocaExercito(4),new AccaoMoveExercito(2),1);        
        adicionaCarta(36,recursoAlimento,1,true,new AccaoColocaExercito(1),new AccaoDestroiExercito(1),1);
        
        adicionaCarta(18,recursoMadeira,1,true,new AccaoColocaCidade(1),null,1);
        adicionaCarta(38,recursoMadeira,1,true,new AccaoColocaExercito(3),null,1);
        adicionaCarta(20,recursoMadeira,1,true,new AccaoMoveExercito(3),null,1);
        adicionaCarta(42,recursoMadeira,1,false,new AccaoMoveExercito(2),new AccaoMoveExercito(3),1);// Esta carta nao faz sentido visto que o utilizador pode mover so dois se lhe apetecer
        adicionaCarta(12,recursoMadeira,1,true,new AccaoMoveExercitoAgua(3),null,1);
        adicionaCarta(40,recursoMadeira,1,true,new AccaoMoveExercitoAgua(4),null,1);
        if (numJogadores >= 5)//não é suposto no máximo existirem 5 jogadores
            adicionaCarta(34,recursoMadeira,1,true,new AccaoMoveExercito(6),null,1);
        adicionaCarta(19,recursoMadeira,1,false,new AccaoDestroiExercito(1),new AccaoColocaCidade(1),1);
        
        adicionaCarta(1,recursoFerro,1,true,new AccaoColocaExercito(2),null,1);
        adicionaCarta(2,recursoFerro,1,true,new AccaoColocaExercito(3),null,2); //23
        adicionaCarta(8,recursoFerro,1,true,new AccaoMoveExercito(2),null,1);
        adicionaCarta(13,recursoFerro,1,true,new AccaoMoveExercitoAgua(2),null,1); //31
        if (numJogadores >= 5)//não é suposto no máximo existirem 5 jogadores
            adicionaCarta(13,recursoFerro,1,true,new AccaoMoveExercitoAgua(2),null,1);
        adicionaCarta(22,recursoFerro,1,true,new AccaoMoveExercitoAgua(3),null,1);
        
        adicionaCarta(6,recursoUtensilio,1,true,new AccaoColocaCidade(1),null,1);
        adicionaCarta(15,recursoUtensilio,1,true,new AccaoColocaExercito(3),null,2); //24
        adicionaCarta(9,recursoUtensilio,1,true,new AccaoMoveExercitoAgua(3),null,1);
        adicionaCarta(16,recursoUtensilio,1,true,new AccaoMoveExercito(4),null,1);
        adicionaCarta(25,recursoUtensilio,1,false,new AccaoColocaExercito(3),new AccaoMoveExercito(3),1);
        adicionaCarta(10,recursoUtensilio,1,false,new AccaoColocaExercito(3),new AccaoMoveExercito(4),1);
        if (numJogadores >= 5)//não é suposto no máximo existirem 5 jogadores
            adicionaCarta(30,recursoUtensilio,2,true,new AccaoMoveExercito(4),null,1);
        adicionaCarta(4,recursoUtensilio,1,true,new AccaoMoveExercito(5),null,1);
        
        adicionaCarta(21,recursoJoker,1,true,new AccaoColocaExercito(2),null,1);
        adicionaCarta(14,recursoJoker,1,true,new AccaoMoveExercitoAgua(2),null,2); //29
        
        baralhaCartas();
    }
    
    private void adicionaCarta(int id, Recurso recurso, int qtdRecursos,boolean execTodasAccoes,Accao accao1, Accao accao2, int numCartas){
        accoesAux.clear();
        // Accao pode ser nula
        accoesAux.add(accao1);
        accoesAux.add(accao2);
        
        for(int i=0;i<numCartas;i++)
            getCartas().add(new Carta(id,recurso,qtdRecursos,execTodasAccoes,accoesAux));
    }
       
    private void baralhaCartas() {
        Collections.shuffle(getCartas());
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
