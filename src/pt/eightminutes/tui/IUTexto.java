/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.tui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import pt.eightminutes.logic.*;
import pt.eightminutes.states.*;

public class IUTexto {
    
    Jogo jogo;
    
    public IUTexto(Jogo jogo){
        this.jogo = jogo;
    }
    
    public void executaInterface() throws IOException, InterruptedException{
        if(jogo.getEstadoActual() instanceof  AguardaPreparaJogo){
            preparaJogo();
        }
        else
        if(jogo.getEstadoActual() instanceof  AguardaAposta){
            menuAposta();
        }
        else
        if(jogo.getEstadoActual() instanceof  AguardaEscolheCarta){
            menuEscolheCarta();
        }
        else
        if(jogo.getEstadoActual() instanceof  AguardaEscolheAccao){
            menuEscolheAccao();
        }
        else
        if(jogo.getEstadoActual() instanceof  AguardaColocaCidade){
            menuColocaCidade();
        }
        else
        if(jogo.getEstadoActual() instanceof  AguardaColocaExercito){
            menuColocaExercito();
        }
        else
        if(jogo.getEstadoActual() instanceof  AguardaMoveExercito){
            menuMoveExercitoTerra();
        }
        else
        if(jogo.getEstadoActual() instanceof  AguardaMoveExercitoAgua){
            menuMoveExercitoAgua();
        }
        else
        if(jogo.getEstadoActual() instanceof  AguardaDestroiExercito){
            menuDestroiExercito();
        }
        else
        if(jogo.getEstadoActual() instanceof  AguardaFinalJogo){
            menuFinalJogo();
        }
        else
        if(jogo.getEstadoActual() instanceof  AguardaFicheiroJogo){
            menuFicheiroJogo();
        }
        else
        if(jogo.getEstadoActual() instanceof  AguardaPontuacao){
            menuPontuacaoJogo();
        }
    }

    public void iniciaJogo(){                
        int opInt;
       
        System.out.println("#####################################");
        System.out.println("##Bem vindo ao eight minutes empire##");
        System.out.println("#####################################");
        System.out.println("1->Jogar");
        System.out.println("2->Terminar");            
        System.out.println("Opção: ");
        opInt = obterNumero();
        if (opInt == 1) {               
            jogo.preparaJogo();
        }        
    }

    public void preparaJogo(){
        menuDefineNumeroJogadores();
        menuDefineNomeJogadores(jogo.getNumJogadores());
    }
    
    public void menuDefineNumeroJogadores(){                
        int opInt;
        
        do {
            System.out.println("########### Preparar Jogo ###########");
            System.out.println("Definir número de jogadores(2-5):");          
            
            opInt = obterNumero();
                        
        } while (opInt < 2 || opInt>5);
        
        jogo.defineNumJogadores(opInt);       
    }
    
    public void menuDefineNomeJogadores(int numJogadores){                
        String opStr;  
        int opInt;
        for(int i=0;i<numJogadores;i++){             
            System.out.println("Definir nome do jogador(Exit-para sair do jogo):");                      
            opStr = obterString();            
            if(opStr.compareTo("Exit")==0)
                iniciaJogo();           
            
            jogo.criaJogador(opStr, Cor.preto);
        }    
        
        jogo.comecaApostas();
    }
    
    public void menuAposta(){                
        int opInt;
                
        System.out.println("############## Apostar ##############");        
        for(int i=0;i<jogo.getJogadores().size();i++){
            System.out.println("### Jogador:"+jogo.getJogadores().get(i).getNome()+" ###");
            do {        
                System.out.println("Definir aposta(0-"+jogo.getMoedasPorJogador()+"):");                      
                opInt = obterNumero();                     
            } while (opInt < 0 || opInt > jogo.getMoedasPorJogador());
            
            jogo.apostaJogador(jogo.getJogadores().get(i), opInt);
        }                
        jogo.comecaJogo();
    }
    
    public void menuFicheiroJogo() throws InterruptedException{                
        int opInt;
                
        System.out.println("############## Ficheiro Jogo ##############");        
        Thread.sleep(1000);
        jogo.passaVez();
    }
    
    public void menuFinalJogo() throws InterruptedException{                
        int opInt;
                
        System.out.println("############## Final ##############");        
        Thread.sleep(1000);
        jogo.passaVez();
    }
    
    public void menuPontuacaoJogo() throws InterruptedException{                
        int opInt;
                
        System.out.println("############## Pontuação ##############");        
        Thread.sleep(1000);
        jogo.passaVez();
    }
    
    public void menuEscolheCarta() throws IOException{                
        int opInt=0;
           
        System.out.println("########## Escolher Carta ###########");
        System.out.println("### Jogador:"+jogo.getJogadorActivo().getNome()+"(Moedas:"+jogo.getJogadorActivo().getMoedas()+") ###");
        System.out.println("### Jogadas:"+jogo.getJogadorActivo().getCartas().size()+" ###");
        System.out.println("Gravar jogo:0");
        for(int i=0; i<jogo.getCartasViradas().size();i++){
            System.out.println("Carta:"+(i+1));
            System.out.print(jogo.getCartasViradas().get(i)); 
        }
           
        System.out.println("Seleccione uma opcção(0-6):");           
        do {                                        
            opInt = obterNumero();                                    
       } while (opInt < 0 || opInt > 6);
        
        
       if(opInt==0)
           jogo.ficheiroJogo();
       else               
           jogo.escolheCarta(opInt-1);
    }
    
    public void menuEscolheAccao(){                
        int opInt=0;
        int opStr;
        int accoesCount=0;
              
        System.out.println("########## Escolher Acção ###########");                                                
        System.out.println("### Jogador:"+jogo.getJogadorActivo().getNome()+"(Moedas:"+jogo.getJogadorActivo().getMoedas()+") ###");
        System.out.println("Passar a vez:0");
        
 
        for(int i=0; i<jogo.getJogadorActivo().getCartaActiva().getAccoes().size();i++){
            if(jogo.getJogadorActivo().getCartaActiva().getAccoes().get(i)!=null){
                if(!jogo.getJogadorActivo().getCartaActiva().getAccoes().get(i).isUsada()){
                    accoesCount++;
                    System.out.print("\nAcção:"+accoesCount);            
                    System.out.print(jogo.getJogadorActivo().getCartaActiva().getAccoes().get(i));                     
                }
            }
        }
        System.out.println("\nSeleccione uma opção(0-"+(accoesCount)+"):");
                   
        do {                                        
            opInt = obterNumero();                                                
        } while (opInt < 0 || opInt > accoesCount);
       
        if(opInt==0)
            jogo.passaVez();
        else
            jogo.escolheAccao(jogo.getJogadorActivo().getCartaActiva().getAccoes().get(opInt-1));
    }
    
    public void menuColocaCidade(){                
        int opInt=0;
        int myIdxCont=0;
             
        System.out.println("########## Coloca Cidade ############"); 
        System.out.println("###Jogador:"+jogo.getJogadorActivo().getNome()+"(Moedas:"+jogo.getJogadorActivo().getMoedas()+") ###");
        
        for(int i=0; i<jogo.getMapa().getContinentes().size();i++){
            System.out.print("Continente:"+i);                      
            System.out.print(jogo.getMapa().getContinentes().get(i));
        }
           
        System.out.println("\nEscolha um Continente(0-"+(jogo.getMapa().getContinentes().size()-1)+"):");           
        do {                                        
            opInt = obterNumero();                                    
        } while (opInt < 0 || opInt >jogo.getMapa().getContinentes().size()-1);
       
        myIdxCont = opInt;
        for(int i=0;i<jogo.getMapa().getContinentes().get(myIdxCont).getRegioes().size();i++)
        {
            System.out.print("Região:"+i);                      
            System.out.print(jogo.getMapa().getContinentes().get(myIdxCont).getRegioes().get(i));
        }
        
        System.out.println("\nEscolha uma Região(0-"+(jogo.getMapa().getContinentes().get(myIdxCont).getRegioes().size()-1)+"):");           
        do {                                        
            opInt = obterNumero();                                    
        } while (opInt < 0 || opInt >jogo.getMapa().getContinentes().get(myIdxCont).getRegioes().size()-1);
        
        jogo.colocaCidade(jogo.getMapa().getContinentes().get(myIdxCont).getRegioes().get(opInt));
    }
     
    public void menuColocaExercito(){                
        int opInt=0;
        int myCont=2;
        Regiao myRegiao=null; 
        Accao myAccao;
        ArrayList<Exercito> exercitos= new ArrayList<>();
            
        System.out.println("######### Coloca Exército ###########"); 
        System.out.println("### Jogador:"+jogo.getJogadorActivo().getNome()+"(Moedas:"+jogo.getJogadorActivo().getMoedas()+") ###");

        System.out.println("Passa vez:0");                      
        System.out.println("Muda acção:1");
        System.out.print("Cidade:2");                      
        System.out.print(jogo.getMapa().getRegiaoInicial());  
        
        for(int i=0;i<jogo.getJogadorActivo().getPecas().size();i++){
            if(jogo.getJogadorActivo().getPecas().get(i) instanceof Cidade){
                if(jogo.getJogadorActivo().getPecas().get(i)!=null){
                    if(jogo.getJogadorActivo().getPecas().get(i).getRegiao()!=null){
                        myCont++;
                        System.out.print("Cidade:"+myCont);                      
                        System.out.print(jogo.getJogadorActivo().getPecas().get(i).getRegiao());   
                    }
                }
            }
        }
          
        myAccao = jogo.getJogadorActivo().getCartaActiva().getAccaoActiva();
        
        System.out.println("\nSeleccione uma opcção(0-"+myCont+"):");           
        do {                                        
            opInt = obterNumero();                                    
        } while (opInt < 0 || opInt >myCont);
       
        if(opInt==0)
            jogo.passaVez();
        else
        if(opInt==1)
            jogo.mudaAccao();
        else
        {
            if(opInt==2)
                myRegiao = jogo.getMapa().getRegiaoInicial();
            else
                myRegiao = jogo.getJogadorActivo().getCidade((opInt-3)).getRegiao();

            System.out.println("Pode colocar "+myAccao.getQtd()+" exercitos");
            System.out.println("Quantos exercitos deseja colocar nesta cidade?");
            do {                                        
                opInt = obterNumero();                                    
            } while (opInt < 0|| opInt >myAccao.getQtd());


            for(int m=0;m<jogo.getJogadorActivo().getPecas().size();m++){
                if(opInt==0)
                    break;

                if(jogo.getJogadorActivo().getPecas().get(m) instanceof Exercito){
                    if(jogo.getJogadorActivo().getPecas().get(m)!=null){
                        exercitos.add((Exercito)jogo.getJogadorActivo().getPecas().get(m));
                        opInt--;
                    }
                }
            }

            jogo.colocaExercito(myRegiao, exercitos);   
        }
    }
    
    public void menuDestroiExercito(){                        
        int opInt=0;
        int myCont=1;
        ArrayList<Exercito> exercitos= new ArrayList<>();
        System.out.println("######## Move Exército Terra ########");  
        System.out.println("#### Jogador:"+jogo.getJogadorActivo().getNome()+"(Moedas:"+jogo.getJogadorActivo().getMoedas()+") ###");
        System.out.println("Passa vez:0");                      
        System.out.println("Muda acção:1");
        
        for(int i=0;i<jogo.getJogadores().size();i++){
            System.out.println("Exercitos do jogador:"+jogo.getJogadores().get(i).getNome());                
            exercitos = jogo.getJogadores().get(i).getListaExercitos();
            
            for(int m=0; m<exercitos.size();m++){
                if(exercitos.get(m).getRegiao()!=null){
                    myCont++;
                    System.out.println("Exercito:"+myCont);                     
                }
            }
        }
               
        System.out.println("Seleccione uma opcção:");
        do {                                        
            opInt = obterNumero();                                    
        } while (opInt < 0|| opInt >myCont);
         
        if(opInt==0)
            jogo.passaVez();
        else
        if(opInt==1)
            jogo.mudaAccao();
        else        
            jogo.destroiExercito(exercitos.get(opInt-2));        
    }
    
    public void menuMoveExercitoTerra(){                
        int opInt=0;
        int myCont=1;
        int myIdxPeca=0;
        Regiao myRegiao=null; 
        Accao myAccao;
        ArrayList<Exercito> exercitos= new ArrayList<>();
            
        System.out.println("######## Move Exército Terra ########");  
        System.out.println("#### Jogador:"+jogo.getJogadorActivo().getNome()+"(Moedas:"+jogo.getJogadorActivo().getMoedas()+") ###");
                 
        System.out.println("Passa vez:0");                      
        System.out.println("Muda acção:1");
        
        for(int i=0;i<jogo.getJogadorActivo().getPecas().size();i++){
            if(jogo.getJogadorActivo().getPecas().get(i) instanceof Exercito){
                if(jogo.getJogadorActivo().getPecas().get(i)!=null){
                    if(jogo.getJogadorActivo().getPecas().get(i).getRegiao()!=null){               
                        myCont++;
                        System.out.print("Exército:"+myCont);                      
                        System.out.print(jogo.getJogadorActivo().getPecas().get(i).getRegiao());                           
                    }
                }
            }
        }
          
        myAccao = jogo.getJogadorActivo().getCartaActiva().getAccaoActiva();
        
        System.out.println("\nSeleccione uma opcção:(0-"+myCont+"):");           
        do {                                        
            opInt = obterNumero();                                    
        } while (opInt < 0|| opInt >myCont);
        
        if(opInt==0)
            jogo.passaVez();
        else
        if(opInt==1)
            jogo.mudaAccao();
        else
        {

            myCont=0;
            opInt = opInt-2;//retirados opções antes do exercito
            for(int i=0;i<jogo.getJogadorActivo().getPecas().size();i++){
                if(jogo.getJogadorActivo().getPecas().get(i) instanceof Exercito){
                    if(jogo.getJogadorActivo().getPecas().get(i)!=null){
                        if(jogo.getJogadorActivo().getPecas().get(i).getRegiao()!=null){                        
                            if(myCont==opInt){
                                exercitos.add((Exercito)jogo.getJogadorActivo().getPecas().get(i));
                                myRegiao = jogo.getJogadorActivo().getPecas().get(i).getRegiao();
                            } 
                            myCont++;
                        }
                    }
                }
            }
            System.out.println("Pode mover "+myAccao.getQtd()+" vezes");

            ArrayList<Regiao> regioesPossiveis = new ArrayList<>();
            jogo.getRegioesPossiveisTerra(myRegiao, myAccao.getQtd(), regioesPossiveis);

            for(int i=0;i< regioesPossiveis.size();i++){
                System.out.print("Região:"+i);                                              
                System.out.println(regioesPossiveis.get(i));
            }

            System.out.println("\nEscolha uma Região(0-"+(regioesPossiveis.size()-1)+"):");           
            do {                                        
                opInt = obterNumero();                                    
            } while (opInt < 0 || opInt >(regioesPossiveis.size()-1));       

            myRegiao = regioesPossiveis.get(opInt);
            jogo.moveExercito(myRegiao, exercitos);
        }
    }
    
    public void menuMoveExercitoAgua(){                
        int opInt=0;
        int myCont=1;
        int myIdxPeca=0;
        Regiao myRegiao=null; 
        Accao myAccao;
        ArrayList<Exercito> exercitos= new ArrayList<>();
            
        System.out.println("######## Move Exército Terra/Água ########");  
        System.out.println("#### Jogador:"+jogo.getJogadorActivo().getNome()+"(Moedas:"+jogo.getJogadorActivo().getMoedas()+") ###");
                 
        System.out.println("Passa vez:0");                      
        System.out.println("Muda acção:1");
        
        for(int i=0;i<jogo.getJogadorActivo().getPecas().size();i++){
            if(jogo.getJogadorActivo().getPecas().get(i) instanceof Exercito){
                if(jogo.getJogadorActivo().getPecas().get(i)!=null){
                    if(jogo.getJogadorActivo().getPecas().get(i).getRegiao()!=null){               
                        myCont++;
                        System.out.print("Exército:"+myCont);                      
                        System.out.print(jogo.getJogadorActivo().getPecas().get(i).getRegiao());                           
                    }
                }
            }
        }
          
        myAccao = jogo.getJogadorActivo().getCartaActiva().getAccaoActiva();
        
        System.out.println("\nSeleccione uma opcção:(0-"+myCont+"):");           
        do {                                        
            opInt = obterNumero();                                    
        } while (opInt < 0|| opInt >myCont);
        
        if(opInt==0)
            jogo.passaVez();
        else
        if(opInt==1)
            jogo.mudaAccao();
        else
        {
            myCont=0;
            opInt = opInt-2;//retirados opções antes do exercito
            for(int i=0;i<jogo.getJogadorActivo().getPecas().size();i++){
                if(jogo.getJogadorActivo().getPecas().get(i) instanceof Exercito){
                    if(jogo.getJogadorActivo().getPecas().get(i)!=null){
                        if(jogo.getJogadorActivo().getPecas().get(i).getRegiao()!=null){                        
                            if(myCont==opInt){
                                exercitos.add((Exercito)jogo.getJogadorActivo().getPecas().get(i));
                                myRegiao = jogo.getJogadorActivo().getPecas().get(i).getRegiao();
                            } 
                            myCont++;
                        }
                    }
                }
            }
            System.out.println("Pode mover "+myAccao.getQtd()+" vezes");

            ArrayList<Regiao> regioesPossiveis = new ArrayList<>();
            jogo.getRegioesPossiveisAgua(myRegiao, myAccao.getQtd(), regioesPossiveis);

            for(int i=0;i< regioesPossiveis.size();i++){
                System.out.print("Região:"+i);                                              
                System.out.println(regioesPossiveis.get(i));
            }

            System.out.println("\nEscolha uma Região(0-"+(regioesPossiveis.size()-1)+"):");           
            do {                                        
                opInt = obterNumero();                                    
            } while (opInt < 0 || opInt >(regioesPossiveis.size()-1));       

            myRegiao = regioesPossiveis.get(opInt);
            jogo.moveExercitoAgua(myRegiao, exercitos);
        }
    }
    
    private String obterString() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    private int obterNumero() {
        Scanner s = new Scanner(System.in);   
        while (!s.hasNextInt()) {
            s.next();
        }
        return s.nextInt();
    }
}
