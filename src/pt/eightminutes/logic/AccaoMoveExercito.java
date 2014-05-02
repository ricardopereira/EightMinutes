/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.logic;

import java.util.ArrayList;
import java.util.List;


public class AccaoMoveExercito extends Accao{

    public AccaoMoveExercito(int qtd) {
        super("Move Exército",qtd);
    }
    
    public int executa(Object sender, List params) {
        if (sender == null) return 0;
        if (params == null) return 0;
        if (!(sender instanceof Jogo)) return 0;
        Jogo j = (Jogo)sender;
        // Obter parametros conforme o estado AguardaMoveExercito
        //moveExercito(Regiao regiao, ArrayList<Exercito> exercitos)
        Regiao regiao = (Regiao)params.get(0);
        ArrayList<Exercito> exercitos = (ArrayList<Exercito>)params.get(1);
        // Execução
        j.getJogadorActivo().moveExercito(regiao,exercitos);
        // Ok
        return 0;
    }
   
}
