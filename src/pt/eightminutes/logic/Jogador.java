/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.logic;

import java.io.Serializable;
import java.util.ArrayList;


public class Jogador extends Base implements Serializable{
    private String nome;
    private int moedas;
    private int aposta;
    private ArrayList<Carta> cartas = new ArrayList<>();
    private ArrayList<Exercito> listaExercitos = new ArrayList<>();
    private ArrayList<Cidade> listaCidades = new ArrayList<>();    
    private Carta cartaActiva;
    
    public Jogador(){
        
    }
    
    public Jogador(String nome,Cor cor, int moedas, int qtdExercito, int qtdCidades){
        this.nome = nome;
        this.moedas = moedas;
        for(int i=0;i<qtdExercito;i++)
            listaExercitos.add(new Exercito(cor,this));
        
        for(int m=0;m<qtdCidades;m++)
            listaCidades.add(new Cidade(cor,this));                   
    }
    
    //Pontuação= pontuação de recursos + Pontuação do mapa(regioes e continentes)
    public int getPontuacao(Jogo jogo){
        int myPontos=0;
        
        myPontos = getPontuacaoRecursos();
        myPontos = myPontos + getPontosMapa(jogo);
        
        return myPontos;
    }
    
    private int getPontosMapa(Jogo jogo){
        Continente continenteAux=null;
        int myPontos=0;
        
        for(int i=0;i< jogo.getMapa().getContinentes().size();i++){
            continenteAux = jogo.getMapa().getContinentes().get(i);
            if(continenteAux.getJogadorControlaContinente(jogo) == this){
                myPontos++;
            }
             
            for(int m=0;m<continenteAux.getRegioes().size();i++){
                if(continenteAux.getRegioes().get(m).getJogadorControlaRegiao(jogo)==this){
                    myPontos ++;
                }
            }
        }
        
        return myPontos;
    }
    
    private int getPontuacaoRecursos(){
        
        ArrayList<Recurso> recursos = new ArrayList<>();
        int myPontos=0;
        
        //adiciona os recursos que tem na cartas ignorando os duplicados
        for(int i=0;i< getCartas().size();i++){
            if(recursos.indexOf(getCartas().get(i).getRecurso())==-1){
                recursos.add(getCartas().get(i).getRecurso());
            }
        }
        
        for(int i=0;i< recursos.size();i++){
            //pergunta ao recurso qual é a sua pontuação consoante o número de cartas que tem daquele recurso
            myPontos = myPontos + recursos.get(i).getPontuacao(getQtdCartasRecurso(recursos.get(i)));
        }
        
        return myPontos;
    }
    
    //vai buscar o numero de cartas com o recurso XPTO e tem atenção pois existem cartas com mais do que um recurso
    private int getQtdCartasRecurso(Recurso recurso){
        int myQtd=0;
        
        for(int i=0;i<getCartas().size();i++){
            if(getCartas().get(i).getRecurso() == recurso){
                myQtd = myQtd +getCartas().get(i).getQtdRecurso();
            }
        }      
        
        return myQtd;
    }
    
    public void bloqueiaAccaoExtra(){
        if(getCartaActiva()!=null){
            if(!getCartaActiva().isExecutaTodasAccoes())
            {
                for(int i=0;i<getCartaActiva().getAccoes().size();i++){
                    if(getCartaActiva().getAccoes().indexOf(getCartaActiva().getAccaoActiva())==-1)
                        getCartaActiva().getAccoes().get(i).setUsada(true);
                }
            }
        }
    }
    
    public void colocaExercito(Regiao regiao, ArrayList<Exercito> exercitos){
        if(regiao!=null)
        {
            for (int i=0;i<exercitos.size();i++){
                for (int m=0;m<getListaExercitos().size();m++){
                    if (getListaExercitos().get(m)==exercitos.get(i))
                    {
                        getListaExercitos().get(m).colocaExercito(regiao);
                        if(getCartaActiva()!=null)
                            getCartaActiva().getAccaoActiva().setQtd(getCartaActiva().getAccaoActiva().getQtd()-1);                                                    
                        
                        bloqueiaAccaoExtra();
                        break;
                    }
                }                           
            }
        }        
    }
    
    public void moveExercito(Regiao regiao, ArrayList<Exercito> exercitos){
        // Lista de exercitos a mover
        for(int i=0;i<exercitos.size();i++){
            // Lista de exercitos do Jogador
            for(int m=0;m<getListaExercitos().size();m++){
                // Encontrar o exército a mover na lista de exercitos do Jogador
                Exercito exercito = getListaExercitos().get(m);
                if (exercito == exercitos.get(i)){
                    // Encontrou exercito
                    
                    //getMenorMovimento
                    //ArrayList<Regiao> r = new ArrayList<Regiao>();
                    
                    // Origem
                    Regiao origem = exercito.getRegiao();
                    // Destino
                    Regiao destino = regiao;
                    
                    boolean isVizinho = false;
                    // Verificar se o destino é um vizinho da Origem
                    for (Regiao item : origem.getRegioesVizinhas()){
                        if (item == regiao){
                            isVizinho = true;
                        }
                    }
                    
                    if (!isVizinho){
                        
                    }

                    //if (getCartaActiva() != null && getCartaActiva().getAccaoActiva() instanceof AccaoMoveExercito)
                    //    getCartaActiva().getAccaoActiva().setQtd(getCartaActiva().getAccaoActiva().getQtd()-1);
                    
                    // Efectua a alteração
                    exercito.moveExercito(regiao);
                    
                    bloqueiaAccaoExtra();
                    break;
                }
            }                           
        }       
    }
    
    public void moveExercitoAgua(Regiao regiao, ArrayList<Exercito> exercitos){
        for(int i=0;i<exercitos.size();i++){
            for(int m=0;m<getListaExercitos().size();m++){
                if(getListaExercitos().get(m)==exercitos.get(i)){
                    getListaExercitos().get(m).moveExercito(regiao);
                    if(getCartaActiva()!=null)
                        getCartaActiva().getAccaoActiva().setQtd(getCartaActiva().getAccaoActiva().getQtd()-1);
                    
                    bloqueiaAccaoExtra();
                    break;
                }
            }                           
        }       
    }
    
    public void destroiExercito(Exercito exercito){
        int myIdx=getListaExercitos().indexOf(exercito);
        
        if(myIdx!=-1){           
            getListaExercitos().get(myIdx).destroiExercito();
            if(getCartaActiva()!=null)
                getCartaActiva().getAccaoActiva().setQtd(getCartaActiva().getAccaoActiva().getQtd()-1);
                
                bloqueiaAccaoExtra();
        }                                   
    }
    
    public void colocaCidade(Regiao regiao){
        
        for(int m=0;m<getListaCidades().size();m++){
            if(getListaCidades().get(m).getRegiao()==null){
                getListaCidades().get(m).colocaCidade(regiao);
                
                if(getCartaActiva()!=null)
                    getCartaActiva().getAccaoActiva().setQtd(getCartaActiva().getAccaoActiva().getQtd()-1);
                
                bloqueiaAccaoExtra();
                break;
            }
        }                                 
    }             
            
    public void gastaMoedas(int qtd){      
        setMoedas(getMoedas()-qtd);
    }
          
    public void adicionaCarta(Carta carta, int qtdMoedas){        
        getCartas().add(carta);       
        gastaMoedas(qtdMoedas);
        setCartaActiva(carta);
    }

    public Accao getProximaAccao(){
        Accao accao=null;
        for(int i=0;i<getCartaActiva().getAccoes().size();i++){            
            if(getCartaActiva().getAccoes().get(i)!=null){                
                if(!getCartaActiva().getAccoes().get(i).isUsada()){
                    accao = getCartaActiva().getAccoes().get(i);
                }                
            } 
        }
           
        return accao;              
    }
    
    public Cidade getCidade(int idx){
        int cont=0;
        for(int m=0;m<getListaCidades().size();m++){
            if(getListaCidades().get(m).getRegiao()!=null){
                if(cont==idx)
                    return getListaCidades().get(m);
                else
                    cont++;
            }
        }
        
        return null;
    }
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the moedas
     */
    public int getMoedas() {
        return moedas;
    }

    /**
     * @param moedas the moedas to set
     */
    public void setMoedas(int moedas) {
        this.moedas = moedas;
    }

    /**
     * @return the cartas
     */
    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    /**
     * @param cartas the cartas to set
     */
    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }


    /**
     * @return the aposta
     */
    public int getAposta() {
        return aposta;
    }

    /**
     * @param aposta the aposta to set
     */
    public void setAposta(int aposta) {
        this.aposta = aposta;
    }

    /**
     * @return the cartaActiva
     */
    public Carta getCartaActiva() {
        return cartaActiva;
    }

    /**
     * @param cartaActiva the cartaActiva to set
     */
    public void setCartaActiva(Carta cartaActiva) {
        this.cartaActiva = cartaActiva;
    }

    /**
     * @return the listaExercitos
     */
    public ArrayList<Exercito> getListaExercitos() {
        return listaExercitos;
    }

    /**
     * @param listaExercitos the listaExercitos to set
     */
    public void setListaExercitos(ArrayList<Exercito> listaExercitos) {
        this.listaExercitos = listaExercitos;
    }

    /**
     * @return the listaCidades
     */
    public ArrayList<Cidade> getListaCidades() {
        return listaCidades;
    }

    /**
     * @param listaCidades the listaCidades to set
     */
    public void setListaCidades(ArrayList<Cidade> listaCidades) {
        this.listaCidades = listaCidades;
    }
}
