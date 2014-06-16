package pt.eightminutes.logic;

public class RecursoUtensilio extends Recurso {

    public RecursoUtensilio() {
        super("Utensilio");
    }

    @Override
    public int getPontuacao(int numCartas) {
        if (numCartas < 2)
            return 0;
        else
        if (numCartas >= 2 && numCartas <=3)
            return 1;
        else
        if (numCartas >= 4 && numCartas <=5)
            return 2;
        else
        if (numCartas == 6)
            return 3;
        else
            return 5; 
    }
    
}
