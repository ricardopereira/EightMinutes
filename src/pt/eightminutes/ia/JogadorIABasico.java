package pt.eightminutes.ia; 

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import pt.eightminutes.logic.Accao;
import pt.eightminutes.logic.Continente;
import pt.eightminutes.logic.Exercito;
import pt.eightminutes.logic.Jogador;
import pt.eightminutes.logic.Jogo;
import pt.eightminutes.logic.RecursoAlimento;
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
            
            ctrl.getJogo().defineApostasJogadores(ctrl.getJogo().getJogadorActivo(), x);
        }
        else
        if(estado.getClass() == AguardaEscolheCarta.class) {
            //escolhe aleatoriamente uma carta            
            int x= utils.randInt(0,5);
            
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
            //escolhe aleatoriament uma regiao dentro das possiveis(terra)
            Jogo jogo = ctrl.getJogo();
            Jogador jogador = jogo.getJogadorActivo();            
            ArrayList<Regiao> listRegioes = new ArrayList<>();
            
            //adiciona regiao inicial à lista
            listRegioes.add(jogo.getMapa().getRegiaoInicial());
            
            //percorre a lista de cidades com regioes e adiciona à lista
            for(int i=0;i<(jogador.getListaCidadeComRegiao().size());i++)
            {
               listRegioes.add(jogador.getListaCidadeComRegiao().get(i).getRegiao());
            }
            
            //escolhe aleatoriamente um indice para a lista de regiões         
            int x = utils.randInt(0, listRegioes.size()-1);
            
            int qtd = jogador.getCartaActiva().getAccaoActiva().getQtd();
            //escolhe aleatoriamente uma quantidade da acção seleccionada
            if (qtd > 0) {
                int c = utils.randInt(1, qtd);
                jogo.colocaExercito(listRegioes.get(x),c);
            }
            else
                jogo.passaVez();
        }
        else
        if(estado.getClass() == AguardaMoveExercito.class) {
            //Escolhe um exercito e uma região aleatoria dentro das possiveis(terra e agua)
            Jogador jogador = ctrl.getJogo().getJogadorActivo();
            int x= utils.randInt(0, jogador.getListaExercitoComRegiao().size()-1);            
            ArrayList<Regiao> listRegiao = new ArrayList<>();
            ArrayList<Exercito> listExercito = new ArrayList<>();
            listExercito.add(jogador.getListaExercitoComRegiao().get(x));
            
            Accao accao = jogador.getCartaActiva().getAccaoActiva();
            
            //O jogador IA Basico move o exercito a qtd total que esta disponivel na accao
            ctrl.getJogo().getRegioesPossiveisTerra(listExercito.get(0).getRegiao(),accao.getQtd(),listRegiao);
            
            int m= utils.randInt(0, listRegiao.size()-1);
            
            ctrl.getJogo().moveExercito(listRegiao.get(m),listExercito);
        }
        else
        if(estado.getClass() == AguardaMoveExercitoAgua.class) {
            //Escolhe um exercito e uma região aleatoria dentro das possiveis
            Jogador jogador = ctrl.getJogo().getJogadorActivo();
            int x= utils.randInt(0, jogador.getListaExercitoComRegiao().size()-1);            
            ArrayList<Regiao> listRegiao = new ArrayList<>();
            ArrayList<Exercito> listExercito = new ArrayList<>();
            listExercito.add(jogador.getListaExercitoComRegiao().get(x));
            
            Accao accao = jogador.getCartaActiva().getAccaoActiva();
            
            //O jogador IA Basico move o exercito a qtd total que esta disponivel na accao
            ctrl.getJogo().getRegioesPossiveisAgua(listExercito.get(0).getRegiao(),accao.getQtd(),listRegiao);
            
            int m= utils.randInt(0, listRegiao.size()-1);
            
            ctrl.getJogo().moveExercitoAgua(listRegiao.get(m),listExercito);
        }
        else
        if(estado.getClass() == AguardaDestroiExercito.class) {
            //Escolhe um exercito aleatoriamente e apaga
            ArrayList<Exercito> listExercito = new ArrayList<>();
            ctrl.getJogo().getListaExercitosTodosUtilizadores(listExercito);
            
            if (Jogo.debugMode)
                System.out.println("DEBUG: listExercito is empty!");
            
            if (listExercito.size() > 0) {
                int  x= utils.randInt(0, listExercito.size()-1);
                ctrl.getJogo().destroiExercito(listExercito.get(x));
            }
            else
                ctrl.getJogo().passaVez();
        }
        else
        if(estado.getClass() == AguardaOpcoesJogo.class) {
            //
        }
        else
        if(estado.getClass() == AguardaJokers.class) {
            for(int i=0;i<ctrl.getJogo().getJogadorActivo().getListaCartaJokers().size();i++)
            {                
                ctrl.getJogo().defineRecurso(ctrl.getJogo().getJogadorActivo().getListaCartaJokers().get(i), new RecursoAlimento());
            }
        }                
    }       
}
