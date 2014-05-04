/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.logic;

import java.util.ArrayList;
import java.util.List;


public class AccaoColocaCidade extends Accao{

    public AccaoColocaCidade(int qtd) {
        super("Coloca Cidade",qtd);
    }
    
    public int executa(Object sender, List params) {  
        if (sender == null) return 0;
        if (params == null) return 0;
        if (!(sender instanceof Jogo)) return 0;
        Jogo j = (Jogo)sender;
        // Obter parametros conforme o estado AguardaMoveExercito
        //colocaCidade(Regiao regiao)
        Regiao regiao = (Regiao)params.get(0);
        
        // Execução
        internalColocaCidades(j.getJogadorActivo(),regiao);
        // Ok
        return 0;
    }
    
    private void internalColocaCidades(Jogador jogador, Regiao regiao) {
        if(regiao!=null)
        {                
            if(regiao.RegiaoTemExercitosDoJogador(jogador)){
                if(jogador.getListaCidadeSemRegiao() != null){                
                    jogador.getListaCidadeSemRegiao().get(0).colocaCidade(regiao);
                    decrementaQtd();                   
                }                
            }                    
        }  
    }
    
}
