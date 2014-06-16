package pt.eightminutes.logic;

public class RecursoMadeira extends Recurso {

    public RecursoMadeira() {
        super("Madeira");
    }

    @Override
    public int getPontuacao(int numCartas) {
        if (numCartas < 2)
            return 0;
        else
        if (numCartas >= 2 && numCartas <=3)
            return 1;
        else
        if (numCartas == 4)
            return 2;
        else
        if (numCartas == 5)
            return 3;
        else
            return 6; 
    }
    
}
