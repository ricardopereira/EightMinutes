/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.logic;

import java.util.ArrayList;
import java.util.List;


public class AccaoColocaExercito extends Accao{

    public AccaoColocaExercito(int qtd) {
        super("Coloca Exército",qtd);
    }
    
    public int executa(Object sender, List params) {
        Integer qtd;
        if (sender == null) return 0;
        if (params == null) return 0;
        if (!(sender instanceof Jogo)) return 0;
        Jogo j = (Jogo)sender;
        // Obter parametros conforme o estado AguardaColocaExercito
        //ColocaExercito(Regiao regiao, ArrayList<Exercito> exercitos)
        Regiao regiao = (Regiao)params.get(0);
        if(params.get(1)!=null)
            qtd = (Integer)params.get(1);
        else
            qtd =0;
        // Execução
        internalColocaExercito(j.getJogadorActivo(),regiao,qtd);
        // Ok
        return 0;
    }
    
    private void internalColocaExercito(Jogador jogador, Regiao regiao, Integer qtd) {
        if(regiao!=null)
        {
            // Número de exercitos a colocar
            for (int i=0;i<qtd;i++){
                // Lista de exercitos do Jogador
                for (int m=0;m<jogador.getListaExercitos().size();m++){
                    // Encontrar o exército a colocar na lista de exercitos do Jogador
                    if (jogador.getListaExercitos().get(m).getRegiao()==null)
                    {                        
                        jogador.getListaExercitos().get(m).colocaExercito(regiao);
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
