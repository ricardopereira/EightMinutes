/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.tui;

import java.io.FileNotFoundException;
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
            menuJokersJogo();
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
        
        for (int i=0; i<numJogadores; ) {
            System.out.println("Definir nome do jogador:"+i);                      
            opStr = obterString();
            if(!opStr.isEmpty()){
                jogo.criaJogador(opStr, Cor.preto);
                i++;
            }
        }    
        
        jogo.comecaApostas();
    }
    
    public void menuAposta(){                
        int opInt=-1;
        Jogador jogador=null;
        boolean existeAposta=false;
                
        System.out.println("############## Apostar ##############");        
        for(int i=0;i<jogo.getJogadores().size();i++){
            jogador = jogo.getJogadores().get(i);
            if(jogador.getAposta()== -1){
                existeAposta=true;
                System.out.println("### Jogador:"+jogador.getNome()+" ###");
                do {        
                    System.out.println("Definir aposta(0-"+jogo.getMoedasPorJogador()+"):");                      
                    opInt = obterNumero();                     
                } while (opInt < 0 || opInt > jogo.getMoedasPorJogador());
                break;
            }           
        } 
        if(jogador!=null && existeAposta){
            jogo.defineApostasJogadores(jogador, opInt);
        }
        else
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
                try {
                    jogo = jogo.carregaJogo();
                }
                catch (FileNotFoundException e) {
                    System.out.println("Não existe ficheiro gravado\n");
                }
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
    
    public void menuJokersJogo() throws InterruptedException{                
        int opInt;
        Jogador jogador;
        Carta carta;        
                
        System.out.println("############## Escolher Jokers ##############");        
        if(jogo.isJokersAtribuidos()){
            jogador = jogo.getListaJogadoresJokers().get(0);
            System.out.println("### Jogador:"+jogador.getNome()+" ###");
            for(int i=0;i<jogador.getListaCartaJokers().size();i++){
                System.out.println("Carta joker:"+i);    
            }  
            System.out.println("Seleccione uma opcção(0-"+(jogador.getListaCartaJokers().size()-1)+"):");           
            do {                                        
                opInt = obterNumero();                                    
            } while (opInt < 0 || opInt > jogador.getListaCartaJokers().size()-1);

            carta  =jogador.getListaCartaJokers().get(opInt);

            System.out.println("Recurso Alimento("+jogador.getQtdRecurso(new RecursoAlimento())+"):0");
            System.out.println("Recurso Ferro("+jogador.getQtdRecurso(new RecursoFerro())+"):1");
            System.out.println("Recurso Joia("+jogador.getQtdRecurso(new RecursoJoia())+"):2");
            System.out.println("Recurso Madeira("+jogador.getQtdRecurso(new RecursoMadeira())+"):3");
            System.out.println("Recurso Utensilio("+jogador.getQtdRecurso(new RecursoUtensilio())+"):4");

            do {                                        
                opInt = obterNumero();                                    
            } while (opInt < 0 || opInt > 4);  
            
            switch (opInt) {
            case 0:
                jogo.defineRecurso(carta, new RecursoAlimento());
                break;
            case 1:
                jogo.defineRecurso(carta, new RecursoFerro());
                break;
            case 2:
                jogo.defineRecurso(carta, new RecursoJoia());
                break;
            case 3:
                jogo.defineRecurso(carta, new RecursoMadeira());
                break;
            case 4:
                jogo.defineRecurso(carta, new RecursoUtensilio());
                break;
            default:
                break;
            }
        }
        else
        {
            System.out.println("E o VENCEDOR é........ "+jogo.getJogadorVencedor().getNome());
            jogo.mostraPontuacao();
        }
    }
    
    public void menuEscolheCarta() throws IOException{                
        int opInt=0;
        
        System.out.println("########## Escolher Carta ###########");
        System.out.println("### Jogador:"+jogo.getJogadorActivo().getNome()+"(Moedas:"+jogo.getJogadorActivo().getMoedas()+") ###");
        System.out.println("### Jogadas:"+jogo.getJogadorActivo().getCartas().size()+" ###");
        System.out.println("Opções:0");
        for(int i=0; i<jogo.getCartasViradas().size();i++){
            
            System.out.print("Carta:"+(i+1));
            //verifica se tem duas acções
            if(jogo.getCartasViradas().get(i).getAccoes().get(1)!=null){
                if(jogo.getCartasViradas().get(i).isExecutaTodasAccoes())
                    System.out.print("(E)");
                else
                    System.out.print("(OU)");
            }
            System.out.print("\n"+jogo.getCartasViradas().get(i));
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
        int opcoes=1;
        int totalOpcoes;
        Continente continenteAux;
        ArrayList<Continente> listaContinentesAux;
        ArrayList<Regiao> listaRegioesAux = new ArrayList<>();
             
        System.out.println("########## Coloca Cidade ############");
        System.out.println("###Jogador:"+jogo.getJogadorActivo().getNome()+"(Moedas:"+jogo.getJogadorActivo().getMoedas()+") ###");
        System.out.println("Passar a vez:0");
        if(jogo.getEstadoAnterior().getClass() == AguardaEscolheAccao.class){
            System.out.println("Muda de acção:1");
            opcoes++;
        }
        
        listaContinentesAux = jogo.getMapa().getContinentesOndeRegiaoTemExercitosDoJogador(jogo.getJogadorActivo(),false);      
        // Mostra os continentes
        if (!listaContinentesAux.isEmpty()) {
            for(int i=0; i<listaContinentesAux.size();i++){
                System.out.print("Continente:"+(i+opcoes));
                System.out.println(" - "+listaContinentesAux.get(i).getNome());
            }
            totalOpcoes = opcoes + listaContinentesAux.size();
        }
        else {
            totalOpcoes = opcoes;
            System.out.print("Sem continentes");
        }
        
        // Obter a escolha do Continente do jogador
        while (listaRegioesAux.isEmpty()) {
            System.out.println("\n\nEscolha uma opção(0-"+(totalOpcoes-1)+"):");
            do {
                opInt = obterNumero();
            } while (opInt < 0 || opInt > totalOpcoes-1);
            
            if (opInt == 0)
                break;
            if(jogo.getEstadoAnterior().getClass() == AguardaEscolheAccao.class){
                if (opInt == 1)
                    break;
            }

            // Obter indice do continente
            myIdxCont = opInt - opcoes;
            // Lista de regioes do continente escolhido
            if (listaContinentesAux != null && myIdxCont >= 0) {
                continenteAux = listaContinentesAux.get(myIdxCont);
                if (continenteAux != null)
                    continenteAux.carregaListaRegioesComExercitosPorJogador(jogo.getJogadorActivo(),listaRegioesAux,false);
            }
        }
        
        //acrescente 1 ao opção senao tiver que escolher a acçã0
        if(jogo.getEstadoAnterior().getClass() != AguardaEscolheAccao.class && opInt!=0)
            opInt++;
        
        switch (opInt) {
            case 0:
                jogo.passaVez();
                break;
            case 1:
                jogo.mudaAccao();
                break;
            default:
                //retira um acrescentado anteriormente
                if(jogo.getEstadoAnterior().getClass() != AguardaEscolheAccao.class)
                    opInt--;
                
                for (int i=0;i<listaRegioesAux.size();i++)
                {
                    System.out.print("Região:"+i);                      
                    System.out.print(listaRegioesAux.get(i));
                }

                System.out.println("\nEscolha uma Região(0-"+(listaRegioesAux.size()-1)+"):");           
                do {                                        
                    opInt = obterNumero();                                    
                } while (opInt < 0 || opInt > listaRegioesAux.size()-1);

                jogo.colocaCidade(listaRegioesAux.get(opInt)); 
                break;
        }
    }
     
    public void menuColocaExercito(){                
        int opInt=0;
        int myCont=0;
        int opcoes=1;
        Regiao myRegiao=null; 
        Accao myAccao;
        ArrayList<Exercito> exercitos= new ArrayList<>();
            
        System.out.println("######### Coloca Exército ###########"); 
        System.out.println("### Jogador:"+jogo.getJogadorActivo().getNome()+"(Moedas:"+jogo.getJogadorActivo().getMoedas()+") ###");

        System.out.println("Passa vez:0");  
        if(jogo.getEstadoAnterior().getClass() == AguardaEscolheAccao.class){
            opcoes++;
            myCont++;
            System.out.println("Muda acção:"+myCont);      
        }
        
        for(int i=0;i<jogo.getJogadorActivo().getListaCidades().size();i++){
            if(jogo.getJogadorActivo().getListaCidades().get(i)!=null){
                if(jogo.getJogadorActivo().getListaCidades().get(i).getRegiao()!=null){
                    myCont++;
                    System.out.print("Cidade:"+myCont);                      
                    System.out.print(jogo.getJogadorActivo().getListaCidades().get(i).getRegiao());   
                }
            }
        }
        myCont++;
        System.out.print("Cidade:"+myCont);                      
        System.out.print(jogo.getMapa().getRegiaoInicial());  
          
        myAccao = jogo.getJogadorActivo().getCartaActiva().getAccaoActiva();
        
        System.out.println("\nSeleccione uma opcção(0-"+myCont+"):");           
        do {                                        
            opInt = obterNumero();                                    
        } while (opInt < 0 || opInt >myCont);
       
        if(jogo.getEstadoAnterior().getClass() != AguardaEscolheAccao.class && opInt!=0)
            opInt++;
            
        if(opInt==0)
            jogo.passaVez();
        else
        if(opInt==1)
            jogo.mudaAccao();
        else
        {
            if(jogo.getEstadoAnterior().getClass() != AguardaEscolheAccao.class)
                    opInt--;
            
            if(opInt==myCont)
                myRegiao = jogo.getMapa().getRegiaoInicial();
            else            
                myRegiao = jogo.getJogadorActivo().getCidade((opInt-(opcoes))).getRegiao();           

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
        int myCont=0;
        int opcoes=1;
        ArrayList<Exercito> exercitos= new ArrayList<>();
        ArrayList<Exercito> exercitosAux= new ArrayList<>();
        System.out.println("######## Destroi Exercito ########");  
        System.out.println("#### Jogador:"+jogo.getJogadorActivo().getNome()+"(Moedas:"+jogo.getJogadorActivo().getMoedas()+") ###");
        System.out.println("Passa vez:0");   
        if(jogo.getEstadoAnterior().getClass() == AguardaEscolheAccao.class){
            opcoes++;
            myCont++;
            System.out.println("Muda acção:"+myCont);
        }
        
        for(int i=0;i<jogo.getJogadores().size();i++){
            System.out.println("Exercitos do jogador:"+jogo.getJogadores().get(i).getNome());                
            exercitos = jogo.getJogadores().get(i).getListaExercitoComRegiao();
            
            for(int m=0; m<exercitos.size();m++){
                if(exercitos.get(m).getRegiao()!=null){
                    myCont++;
                    System.out.println("Exercito:"+myCont+" R:"+(exercitos.get(m).getRegiao().getNome()));  
                    exercitosAux.add(exercitos.get(m));
                }
            }
        }
        
        if(jogo.getEstadoAnterior().getClass() != AguardaEscolheAccao.class && opInt!=0)
            opInt++;
            
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
        {
            if(jogo.getEstadoAnterior().getClass() == AguardaEscolheAccao.class)
                opInt--;
            jogo.destroiExercito(exercitosAux.get(opInt-(opcoes)));        
        }
    }
    
    public void menuMoveExercitoTerra(){                
        int opInt=0;
        int myCont=0;
        int opcoes=1;
        int myIdxPeca=0;
        
        Regiao myRegiao=null; 
        Accao myAccao;
        ArrayList<Exercito> exercitos= new ArrayList<>();
            
        System.out.println("######## Move Exército Terra ########");  
        System.out.println("#### Jogador:"+jogo.getJogadorActivo().getNome()+"(Moedas:"+jogo.getJogadorActivo().getMoedas()+") ###");
                 
        System.out.println("Passa vez:0");                      
        if(jogo.getEstadoAnterior().getClass() == AguardaEscolheAccao.class){
            opcoes++;
            myCont++;
            System.out.println("Muda acção:"+myCont);
        }
              
        for(int i=0;i<jogo.getJogadorActivo().getListaExercitoComRegiao().size();i++){
            if(jogo.getJogadorActivo().getListaExercitoComRegiao().get(i)!=null){
                if(jogo.getJogadorActivo().getListaExercitoComRegiao().get(i).getRegiao()!=null){
                    myCont++;
                    System.out.print("Exército:"+myCont);
                    System.out.print("  R"+jogo.getJogadorActivo().getListaExercitoComRegiao().get(i).getRegiao());                           
                }                
            }
        }
          
        myAccao = jogo.getJogadorActivo().getCartaActiva().getAccaoActiva();
        // Neste caso: quantidade significa número de movimentos
        System.out.println("\nPode mover "+myAccao.getQtd()+" vezes");
                
        System.out.println("Seleccione uma opcção:(0-"+myCont+"):");
        do {                                        
            opInt = obterNumero();                                    
        } while (opInt < 0|| opInt >myCont);
        
        if(jogo.getEstadoAnterior().getClass() != AguardaEscolheAccao.class && opInt!=0)
            opInt++;
        // Verificar opção tomada
        if(opInt == 0)
            jogo.passaVez();
        else
        if(opInt == 1)
            jogo.mudaAccao();
        else
        {
            if(jogo.getEstadoAnterior().getClass() != AguardaEscolheAccao.class)
                opInt--;
            // Criar lista de exercitos seleccionados
            // Obter região pertencem
            // ToDo: melhorar esta situação
            opInt = opInt-(opcoes);//retirados opções antes do exercito
            
            exercitos.add((Exercito)jogo.getJogadorActivo().getListaExercitoComRegiao().get(opInt));
            myRegiao = jogo.getJogadorActivo().getListaExercitoComRegiao().get(opInt).getRegiao();             
            
            // ToDo: validar número de movimentos
            
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
        int myCont=0;
        int myIdxPeca=0;
        int opcoes=1;
        Regiao myRegiao=null; 
        Accao myAccao;
        ArrayList<Exercito> exercitos= new ArrayList<>();
            
        System.out.println("######## Move Exército Terra/Água ########");  
        System.out.println("#### Jogador:"+jogo.getJogadorActivo().getNome()+"(Moedas:"+jogo.getJogadorActivo().getMoedas()+") ###");
                 
        System.out.println("Passa vez:0"); 
        if(jogo.getEstadoAnterior().getClass() == AguardaEscolheAccao.class){
            opcoes++;
            myCont++;
            System.out.println("Muda acção:1");
        }
        
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
        // Neste caso: quantidade significa número de movimentos
        System.out.println("\nPode mover "+myAccao.getQtd()+" vezes");
        
        System.out.println("Seleccione uma opcção:(0-"+myCont+"):");           
        do {                                        
            opInt = obterNumero();                                    
        } while (opInt < 0|| opInt >myCont);
        
        
        if(jogo.getEstadoAnterior().getClass() != AguardaEscolheAccao.class && opInt!=0)
            opInt++;
            
        if(opInt==0)
            jogo.passaVez();
        else
        if(opInt==1)
            jogo.mudaAccao();
        else
        {
            if(jogo.getEstadoAnterior().getClass() != AguardaEscolheAccao.class)
                opInt--;
           
            opInt = opInt-(opcoes);//retirados opções antes do exercito

            exercitos.add((Exercito)jogo.getJogadorActivo().getListaExercitos().get(opInt));
            myRegiao = jogo.getJogadorActivo().getListaExercitos().get(opInt).getRegiao();
                       

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
