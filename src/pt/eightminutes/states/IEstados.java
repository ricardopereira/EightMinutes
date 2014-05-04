package pt.eightminutes.states;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import pt.eightminutes.logic.*;

public interface IEstados {
    Jogo getJogo();
    
    IEstados defineNumJogadores(int numJogadores);
    IEstados defineDadosJogadores(String nome, Cor cor);
    IEstados comecaApostas();
    IEstados defineApostasJogadores(int numMoedas);
    IEstados comecaJogo();  
    IEstados escolheCartas(int idx);    
    IEstados verificaPontuacao();
    IEstados compraCarta();
    IEstados escolheAccao(Accao accao);
    IEstados mudaAccao();
    IEstados passaVez();
    IEstados colocaCidade(Regiao regiao);
    IEstados colocaExercito(Regiao regiao, Integer qtd);
    IEstados moveExercito(Regiao regiao, ArrayList<Exercito> exercitos);
    IEstados destroiExercito(Exercito exercito);
    IEstados moveExercitoAgua(Regiao regiao, ArrayList<Exercito> exercitos);
    IEstados novoJogo();
    IEstados carregaJogo() throws FileNotFoundException, IOException;
    IEstados gravaJogo();
    IEstados abandonaJogo();
    IEstados defineRecurso(Carta carta, Recurso recurso);
    IEstados mostraPontuacao();
}