package pt.eightminutes.ia; 

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import pt.eightminutes.logic.Accao;
import pt.eightminutes.logic.Continente;
import pt.eightminutes.logic.Jogador;
import pt.eightminutes.logic.Jogo;
import pt.eightminutes.logic.Regiao;
import pt.eightminutes.states.*;
import pt.eightminutes.states.IEstados;
import pt.eightminutes.ui.graphical.DataController;
import pt.eightminutes.utils.utils;

public class JogadorIABasico extends JogadorIA{
    
    public JogadorIABasico() {
    }
           
    @Override
    public void update(Observable o, Object arg) {
       
        IEstados estado= ctrl.getJogo().getEstadoActual();

        if(ctrl.getJogo().getJogadorActivo()!=jogadorIA){
            return;
        }
               
        if(estado.getClass() == AguardaAposta.class) {     
            //define aleatoriamente qual é a aposta num intervalo definido entre 0 e o máximo das moedas
            int x = utils.randInt(0, ctrl.getJogo().getMoedasPorJogador());
            
            ctrl.getJogo().apostaJogador(jogadorIA, x);
        }
        else
        if(estado.getClass() == AguardaEscolheCarta.class) {
            //escolhe aleatoriamente uma carta            
            int x= utils.randInt(0,ctrl.getJogo().getNumCartasFinal());
            
            ctrl.getJogo().escolheCarta(x);
        }
        else
        if(estado.getClass() == AguardaEscolheAccao.class) {
            //escolhe aleatoriamente uma acção e executa
            ArrayList<Accao> listAccoes = ctrl.getJogo().getJogadorActivo().getCartaActiva().getAccoes();
            int x= utils.randInt(0, listAccoes.size()-1);
            
            ctrl.getJogo().escolheAccao(listAccoes.get(x));            
        }
        else
        if(estado.getClass() == AguardaColocaCidade.class) {         
            //escolhe aleatoriamente uma regiao dentro das possiveis
            Jogo jogo = ctrl.getJogo();
            Jogador jogador = jogo.getJogadorActivo();            
            ArrayList<Continente> listContinentes = jogo.getMapa().getContinentesOndeRegiaoTemExercitosDoJogador(jogador,false);
            ArrayList<Regiao> listRegioes = new ArrayList<>();
            
            int x= utils.randInt(0, listContinentes.size()-1);
            listContinentes.get(x).carregaListaRegioesComExercitosPorJogador(jogador, listRegioes, false);
            x= utils.randInt(0, listRegioes.size()-1);                      
            jogo.colocaCidade(listRegioes.get(x));
        }
        else
        if(estado.getClass() == AguardaColocaExercito.class) {
            //escolhe aleatoriament uma regiao dentro das possiveis
            Jogo jogo = ctrl.getJogo();
            Jogador jogador = jogo.getJogadorActivo();            
            ArrayList<Regiao> listRegioes = new ArrayList<>();
            
            //adiciona regiao inicial à lista
            listRegioes.add(jogo.getMapa().getRegiaoInicial());
            
            //percorre a lista de cidades com regioes e adiciona à lista
            for(int i=0;i<(jogador.getListaCidadeComRegiao().size()-1);i++)
            {
               listRegioes.add(jogador.getListaCidadeComRegiao().get(i).getRegiao());
            }
            
            //escolhe aleatoriamente um indice para a lista de regiões         
            int x= utils.randInt(0, listRegioes.size()-1);
            //escolhe aleatoriamente uma quantidade da acção seleccionada
            int c= utils.randInt(1, jogador.getCartaActiva().getAccaoActiva().getQtd());
            
            jogo.colocaExercito(listRegioes.get(x),c);
        }
        else
        if(estado.getClass() == AguardaMoveExercito.class) {
            //
        }
        else
        if(estado.getClass() == AguardaMoveExercitoAgua.class) {
            //
        }
        else
        if(estado.getClass() == AguardaDestroiExercito.class) {
            //
        }
        else
        if(estado.getClass() == AguardaOpcoesJogo.class) {
            //
        }
        else
        if(estado.getClass() == AguardaJokers.class) {
            //
        }                
    }       
}
