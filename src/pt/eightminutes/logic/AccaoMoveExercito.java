/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.logic;

import java.util.ArrayList;
import java.util.List;


public class AccaoMoveExercito extends Accao {

    public AccaoMoveExercito(int qtd) {
        super("Move Exército",qtd);
    }
    
    @Override
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
        internalMoveExercito(j.getJogadorActivo(),j.getMapa(),regiao,exercitos);
        // Ok
        return 0;
    }
    
    private void internalMoveExercito(Jogador jogador, Mapa mapa, Regiao regiao, ArrayList<Exercito> exercitos) {
        // ToDo: Implementar Hashing e Iterators
        // Lista de exercitos a mover
        for (int i=0; i<exercitos.size(); i++) {
            // Lista de exercitos do Jogador
            for (int m=0; m<jogador.getListaExercitos().size(); m++) {
                // Encontrar o exército a mover na lista de exercitos do Jogador
                Exercito exercito = jogador.getListaExercitos().get(m);
                // Encontrar exercito
                if (exercito == exercitos.get(i)) {
                    // Trajecto
                    ArrayList<Regiao> trajecto = null;
                    // Origem
                    Regiao origem = exercito.getRegiao();
                    // Destino
                    Regiao destino = regiao;
                    
                    // ToDo: Usar equals?
                    if (origem == destino) {
                        // Verificar se o destino é igual à origem
                        break;
                    } else {
                        // Verificar se o destino é um vizinho da Origem
                        for (Regiao item : origem.getRegioesVizinhas()){
                            if (item == destino) {
                                trajecto = new ArrayList<>();
                                // Movimento directo
                                trajecto.add(destino);
                                break;
                            }
                        }
                    }
                    
                    if (trajecto == null) {
                        // As regioes só sao criadas uma vez, logo podemos usar o ==
                        // Sao as mesmas instancias para todo o programa
                        trajecto = mapa.getTrajecto(origem, destino);
                        
                        
                        if (debugMode)
                        if (trajecto == null || trajecto.size() == 0) {
                            // Bug
                            System.out.println("Bug: origem="+ origem.getNome() +" destino="+ destino.getNome() +" trajecto a null");
                        }
                        
                        
                        // Encontrou trajecto?
                        if (trajecto != null && trajecto.size() > 0) {
                            //Ultima regiao do trajecto
                            Regiao lastRegiao = trajecto.get(trajecto.size()-1);
                            // Verificar se já NÃO está em cache
                            if (lastRegiao != null && lastRegiao != destino) {
                                for (Regiao item : lastRegiao.getRegioesVizinhas()) {
                                    if (item == destino) {
                                        // Adicionar para evitar uma nova pesquisa da proxima vez: cache
                                        trajecto.add(destino);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    
                    // Efectuar os movimentos
                    if (trajecto != null) {
                        int idx = 0;
                        for (Regiao item : trajecto) {
                            // ToDo: É necessario validar o numero de movimentos?!
                            
                            // Efectua a alteração
                            exercito.moveExercito(regiao);
                            // Decrementa movimentos possiveis
                            decrementaQtd();
                            
                            // Escrever trajecto a fazer
                            if (debugMode)
                                System.out.println("Movimento "+ (++idx) +": "+ item.getNome());
                        }
                    }
                    
                    // Para terminar o ciclo de procura do exercito do jogador
                    break;
                }
            }                           
        }
    }        
}
