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
        //destroiExercito(Regiao regiao, ArrayList<Exercito> exercitos)
        Regiao regiao = (Regiao)params.get(0);
        ArrayList<Exercito> exercitos = (ArrayList<Exercito>)params.get(1);
        // Execução
        internalDestroiExercito(j.getJogadorActivo(),regiao,exercitos);
        // Ok
        return 0;
    }
    
    private void internalDestroiExercito(Jogador jogador, Regiao regiao, ArrayList<Exercito> exercitos) {
        if(regiao!=null)
        {
            // Lista de exercitos a colocar
            for (int i=0;i<exercitos.size();i++){
                // Lista de exercitos do Jogador
                for (int m=0;m<jogador.getListaExercitos().size();m++){
                    // Encontrar o exército a destruir na lista de exercitos do Jogador
                    if (jogador.getListaExercitos().get(m)==exercitos.get(i))
                    {
                        jogador.getListaExercitos().get(m).destroiExercito();
                        decrementaQtd();
                        break;
                    }
                }                           
                if(isUsada())
                    break;
            }
        }  
    }
}
