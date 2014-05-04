/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.logic;

import java.util.ArrayList;
import java.util.List;


public class AccaoDestroiExercito extends Accao{

    public AccaoDestroiExercito(int qtd) {
        super("Destrói Exército",qtd);
    }
    
    public int executa(Object sender, List params) {
         if (sender == null) return 0;
        if (params == null) return 0;
        if (!(sender instanceof Jogo)) return 0;
        Jogo j = (Jogo)sender;
        // Obter parametros conforme o estado AguardaMoveExercito
        //destroiExercito(Exercito)
        Exercito exercito = (Exercito)params.get(0);
        // Execução
        internalDestroiExercito(j.getJogadorActivo(),exercito);
        // Ok
        return 0;
    }
    
    private void internalDestroiExercito(Jogador jogador, Exercito exercito) {
        if(exercito!=null)
        {
            // destroi exercito            
            exercito.destroiExercito();
            decrementaQtd();            
        }  
    }
}
