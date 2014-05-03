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
        Integer qtd;
        
        if (sender == null) return 0;
        if (params == null) return 0;
        if (!(sender instanceof Jogo)) return 0;
        Jogo j = (Jogo)sender;
        // Obter parametros conforme o estado AguardaMoveExercito
        //colocaCidade(Regiao regiao, ArrayList<Cidade> cidades)
        Regiao regiao = (Regiao)params.get(0);
        if(params.get(1)!=null)
           qtd  = (Integer)params.get(1);
        else
            qtd = 1;
        
        // Execução
        internalColocaCidades(j.getJogadorActivo(),regiao,qtd);
        // Ok
        return 0;
    }
    
    private void internalColocaCidades(Jogador jogador, Regiao regiao, Integer qtd) {
        if(regiao!=null)
        {            
            // Lista de cidades do Jogador
            for(int m=0;m<qtd;m++){
                for (int i=0;i<jogador.getListaCidades().size();i++){
                    // Encontrar o cidade sem região                
                    if(jogador.getListaCidades().get(i).getRegiao()==null){                
                        jogador.getListaCidades().get(i).colocaCidade(regiao);
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
