/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.logic;


public class RecursoAlimento extends Recurso{

    public RecursoAlimento() {
        super("Alimento");
    }

    @Override
    public int getPontuacao(int numCartas) {
        if (numCartas < 3)
            return 0;
        else
        if (numCartas >= 3 && numCartas <=4)
            return 1;
        else
        if (numCartas >= 5 && numCartas <=6)
            return 2;
        else
        if (numCartas == 7)
            return 3;
        else
            return 5; 
    }
    
}
