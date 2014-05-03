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
    
    public void iniciaInterface() {
        int opInt;
        
        if (Jogo.debugMode) {
            System.out.println("DEBUG MODE");
            
            if (Jogo.debugShowMapa)
                listarMapa();
        }
        
        // Carregar todas as dependências do jogo
        try {
            jogo.verificarDependencias();
        } catch (IOException e) {
            System.err.println(e);
        }
        
        jogo.opcoesJogo();
    }
    
    public void executaInterface() throws IOException, InterruptedException {
        if(jogo.getEstadoActual().getClass() == AguardaPreparaJogo.class) {
            preparaJogo();
        }
        else
        if(jogo.getEstadoActual().getClass() == AguardaAposta.class) {
            menuAposta();
        }
        else
        if(jogo.getEstadoActual().getClass() == AguardaEscolheCarta.class) {
            menuEscolheCarta();
        }
        else
        if(jogo.getEstadoActual().getClass() == AguardaEscolheAccao.class) {
            menuEscolheAccao();
        }
        else
        if(jogo.getEstadoActual().getClass() == AguardaColocaCidade.class) {
            menuColocaCidade();
        }
        else
        if(jogo.getEstadoActual().getClass() == AguardaColocaExercito.class) {
            menuColocaExercito();
        }
        else
        if(jogo.getEstadoActual().getClass() == AguardaMoveExercito.class) {
            menuMoveExercitoTerra();
        }
        else
        if(jogo.getEstadoActual().getClass() == AguardaMoveExercitoAgua.class) {
            menuMoveExercitoAgua();
        }
        else
        if(jogo.getEstadoActual().getClass() == AguardaDestroiExercito.class) {
            menuDestroiExercito();
        }
        else
        if(jogo.getEstadoActual().getClass() == AguardaOpcoesJogo.class) {
            menuOpcoesJogo();
        }
        else
        if(jogo.getEstadoActual().getClass() == AguardaJokers.class) {
            menuPontuacaoJogo();
        }
    }

    public void preparaJogo(){
        menuDefineNumeroJogadores();
        menuDefineNomeJogadores(jogo.getNumJogadores());
    }
    
    private void listarMapa(){
        System.out.print("\nMAPA");
        // Para efeitos de teste
        for (Continente itemContinente : jogo.getMapa().getContinentes()){
            System.out.println("\n\n"+itemContinente.getNome());
            System.out.println("---------------------------------");
            for (Regiao itemRegiao : itemContinente.getRegioes()){
                System.out.println("\nRegião"+itemRegiao.getMapIndex()+": "+itemRegiao.getNome());
                //System.out.println("Vizinhos:");
                int i = 0;
                for (Regiao itemVizinho : itemRegiao.getRegioesVizinhas())
                    System.out.println("V"+ (++i) +": "+itemVizinho.getNome());
            }
        }
        System.out.print("\n");
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
        
        for (int i=0; i<numJogadores; i++) {
            System.out.println("Definir nome do jogador:");                      
            opStr = obterString();
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
    
    public void menuOpcoesJogo() throws InterruptedException{                
        int opInt;
        boolean isEstadoAnteriorEscolheCarta = jogo.isEstadoAnterior(AguardaEscolheCarta.class);
        
        // OPÇÕES
        System.out.println("#####################################");
        System.out.println("##Bem vindo ao eight minutes empire##");
        System.out.println("#####################################");
        System.out.println("1->Novo jogo");
        System.out.println("2->Retomar jogo");
        if (isEstadoAnteriorEscolheCarta)
            System.out.println("3->Gravar jogo");
        System.out.println("0->Terminar");
        System.out.println("Opção: ");
        
        if (isEstadoAnteriorEscolheCarta)
            opInt = obterNumero(0,3);
        else
            opInt = obterNumero(0,2);
        
        switch (opInt) {
            case 0:
                jogo.terminaJogo();
                break;
            case 1:
                jogo.novoJogo();
                break;
            case 2:
                // Retoma o jogo
                jogo = jogo.carregaJogo();
                // Debug
                if (Jogo.debugMode) {
                    System.out.println("DEBUG MODE: Jogo carregado");
                    System.out.println("Trajectos carregados: "+jogo.getMapa().getTrajectosCount());
                    if (Jogo.debugShowMapa)
                        listarMapa();
                }
                break;
            case 3:
                // Gravar jogo
                if (isEstadoAnteriorEscolheCarta)
                    jogo.gravaJogo();
                break;
            default:
                break;
        }
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
        System.out.println("Opções:0");
        for(int i=0; i<jogo.getCartasViradas().size();i++){
            System.out.println("Carta:"+(i+1));
            System.out.print(jogo.getCartasViradas().get(i));
        }
           
        System.out.println("Seleccione uma opcção(0-6):");           
        do {                                        
            opInt = obterNumero();                                    
       } while (opInt < 0 || opInt > 6);
        
        
       if (opInt == 0)
           jogo.opcoesJogo();
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
        int myCont=0;
        ArrayList<Continente> continentesAux = new ArrayList<>();
        ArrayList<Regiao> regioesAux = new ArrayList<>();
             
        System.out.println("########## Coloca Cidade ############"); 
        System.out.println("###Jogador:"+jogo.getJogadorActivo().getNome()+"(Moedas:"+jogo.getJogadorActivo().getMoedas()+") ###");
        System.out.println("Passar a vez:0");
        if(jogo.getEstadoAnterior().getClass() == AguardaEscolheAccao.class){
            System.out.println("Muda de acção:1");
            myCont++;
        }
        
        continentesAux = jogo.getMapa().getContinentesComRegiaoTemExercitosDoJogador(jogo.getJogadorActivo(),false);
        
        for(int i=0; i<continentesAux.size();i++){
            myCont++;
            System.out.print("Continente:"+myCont);                      
            System.out.print(continentesAux.get(i));
        }
        
        while(regioesAux.size()==0||opInt==0||opInt==1){
            System.out.println("\nEscolha uma opção(0-"+(myCont)+"):");           
            do {                                        
                opInt = obterNumero();                                    
            } while (opInt < 0 || opInt >continentesAux.size()+1);
            
            myIdxCont = opInt-myCont;

            continentesAux.get(myIdxCont).getListaRegioesComExercitosPorJogador(jogo.getMapa(),jogo.getJogadorActivo(),regioesAux,false);
        }
        
        if(opInt==0){
            jogo.passaVez();
        }
        else
        {
            if(opInt==1){
                jogo.mudaAccao();
            }
            else
            {    
                for(int i=0;i<regioesAux.size();i++)
                {
                    System.out.print("Região:"+i);                      
                    System.out.print(regioesAux.get(i));
                }

                System.out.println("\nEscolha uma Região(0-"+(regioesAux.size()-1)+"):");           
                do {                                        
                    opInt = obterNumero();                                    
                } while (opInt < 0 || opInt >continentesAux.get(myIdxCont).getRegioes().size()-1);

                jogo.colocaCidade(regioesAux.get(opInt)); 
            }
        }
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
        
        for(int i=0;i<jogo.getJogadorActivo().getListaCidades().size();i++){
            if(jogo.getJogadorActivo().getListaCidades().get(i)!=null){
                if(jogo.getJogadorActivo().getListaCidades().get(i).getRegiao()!=null){
                    myCont++;
                    System.out.print("Cidade:"+myCont);                      
                    System.out.print(jogo.getJogadorActivo().getListaCidades().get(i).getRegiao());   
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

            jogo.colocaExercito(myRegiao, opInt);   
        }
    }
    
    public void menuDestroiExercito(){                        
        int opInt=0;
        int myCont=1;
        ArrayList<Exercito> exercitos= new ArrayList<>();
        System.out.println("######## Destroi Exercito ########");  
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
        
        for(int i=0;i<jogo.getJogadorActivo().getListaExercitos().size();i++){
            if(jogo.getJogadorActivo().getListaExercitos().get(i)!=null){
                if(jogo.getJogadorActivo().getListaExercitos().get(i).getRegiao()!=null){
                    myCont++;
                    System.out.print("Exército:"+myCont);
                    System.out.print("  R"+jogo.getJogadorActivo().getListaExercitos().get(i).getRegiao());                           
                }                
            }
        }
          
        myAccao = jogo.getJogadorActivo().getCartaActiva().getAccaoActiva();
        
        System.out.println("\nSeleccione uma opcção:(0-"+myCont+"):");           
        do {                                        
            opInt = obterNumero();                                    
        } while (opInt < 0|| opInt >myCont);
        
        // Verificar opção tomada
        if(opInt == 0)
            jogo.passaVez();
        else
        if(opInt == 1)
            jogo.mudaAccao();
        else
        {
            // Criar lista de exercitos seleccionados
            // Obter região pertencem
            myCont=0;
            // ToDo: melhorar esta situação
            opInt = opInt-2; //retirados opções antes do exercito
            for(int i=0;i<jogo.getJogadorActivo().getListaExercitos().size();i++){                
                if(jogo.getJogadorActivo().getListaExercitos().get(i) != null){
                    if(jogo.getJogadorActivo().getListaExercitos().get(i).getRegiao() != null){
                        if(myCont == opInt){
                            exercitos.add((Exercito)jogo.getJogadorActivo().getListaExercitos().get(i));
                            myRegiao = jogo.getJogadorActivo().getListaExercitos().get(i).getRegiao();
                        } 
                        myCont++;
                    }
                }
            }
            
            // ToDo: validar número de movimentos
            
            // Neste caso: quantidade significa número de movimentos
            System.out.println("Pode mover "+myAccao.getQtd()+" vezes");

            // Listar movimentos possíveis
            ArrayList<Regiao> regioesPossiveis = new ArrayList<>();
            jogo.getRegioesPossiveisTerra(myRegiao, myAccao.getQtd(), regioesPossiveis);

            for(int i=0;i< regioesPossiveis.size();i++){
                System.out.print("Região:"+i);                                              
                System.out.println(regioesPossiveis.get(i));
            }

            // Escolha da região
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
        
        for(int i=0;i<jogo.getJogadorActivo().getListaExercitos().size();i++){
            if(jogo.getJogadorActivo().getListaExercitos().get(i)!=null){
                if(jogo.getJogadorActivo().getListaExercitos().get(i).getRegiao()!=null){               
                    myCont++;
                    System.out.print("Exército:"+myCont);                      
                    System.out.print("  R"+jogo.getJogadorActivo().getListaExercitos().get(i).getRegiao());                            
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
            for(int i=0;i<jogo.getJogadorActivo().getListaExercitos().size();i++){
                if(jogo.getJogadorActivo().getListaExercitos().get(i)!=null){
                    if(jogo.getJogadorActivo().getListaExercitos().get(i).getRegiao()!=null){                        
                        if(myCont==opInt){
                            exercitos.add((Exercito)jogo.getJogadorActivo().getListaExercitos().get(i));
                            myRegiao = jogo.getJogadorActivo().getListaExercitos().get(i).getRegiao();
                        } 
                        myCont++;
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
    
    private int obterNumero(int de, int ate) {
        Scanner s = new Scanner(System.in);
        int nr = de-1;
        while (!s.hasNextInt() && (nr < de || nr > ate)) {
            nr = s.nextInt();
        }
        return s.nextInt();
    }
    
    public Jogo getJogo() {
        return jogo;
    }
}
