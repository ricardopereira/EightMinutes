/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.logic;


public class RecursoJoia extends Recurso{

    public RecursoJoia() {
        super("Joia");
    }

    @Override
    public int getPontuacao(int numCartas) {
        if (numCartas < 0)
            return 0;
        else
        if (numCartas == 0)
            return 0;
        else
        if (numCartas == 1) 
            return 1;
        else
        if (numCartas == 2)
            return 3;
        else
        if (numCartas == 3)
            return 4;
        else
            return 5;                  
    }
    
}
