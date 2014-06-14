/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.logic;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;


public class Jogador extends Base implements Serializable {
    
    private int index;
    private Object owner;
    private String nome;
    private int moedas;
    private int aposta;
    private ArrayList<Carta> cartas = new ArrayList<>();
    private ArrayList<Exercito> listaExercitos = new ArrayList<>();
    private ArrayList<Cidade> listaCidades = new ArrayList<>();    
    private Carta cartaActiva;
    private Color cor;
    
    public Jogador(Object owner, String nome, Color cor, int moedas, int qtdExercito, int qtdCidades, int index) {
        super();
        
        this.owner = owner;
        this.nome = nome;
        this.moedas = moedas;
        this.aposta = -1;
        this.cor = cor;
        this.index = index;
        
        for (int i = 0; i < qtdExercito; i++)
            listaExercitos.add(new Exercito(this));
        
        for (int m = 0; m < qtdCidades; m++)
            listaCidades.add(new Cidade(this));                   
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
             
            for(int m=0;m<continenteAux.getRegioes().size();m++){
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
    
    public void bloqueiaAccaoExtra() {
        // ToDo: O bloqueio tem que ser na accao
        //Metodo copiado para classe Carta, dona das Accoes
        //Remover este metdo quando a execucao de todas as accoes sairem da classe Jogador
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
    
    public int getQtdRecurso(Recurso recurso){
        int myCont=0;
        
        for(int i=0;i<getCartas().size();i++){
            
            if(getCartas().get(i).getRecurso().getClass() == recurso.getClass()){
                myCont = myCont +getCartas().get(i).getQtdRecurso();
            }
        }
        
        return myCont;
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
    
    //Lista de cidades que ja foram atribuidas a alguma região
    public ArrayList<Cidade> getListaCidadeComRegiao(){
        ArrayList<Cidade> cidadeAux = new ArrayList<>();
        
        for(int i=0;i<listaCidades.size();i++){
            if(listaCidades.get(i).getRegiao()!=null)
                cidadeAux.add(listaCidades.get(i));
        }
        
        return cidadeAux;
    }
    
    //Lista de cidades que ainda não foram atribuidas a nenhuma regiao
    public ArrayList<Cidade> getListaCidadeSemRegiao(){
        ArrayList<Cidade> cidadeAux = new ArrayList<>();
        
        for(int i=0;i<listaCidades.size();i++){
            if(listaCidades.get(i).getRegiao()==null)
                cidadeAux.add(listaCidades.get(i));
        }
        
        return cidadeAux;
    }
    
    //Lista de exercitos que ja foram atribuidos a uma regiao
    public ArrayList<Exercito> getListaExercitoComRegiao(){
        ArrayList<Exercito> exercitosAux = new ArrayList<>();
        
        for(int i=0;i<listaExercitos.size();i++){
            if(listaExercitos.get(i).getRegiao()!=null)
                exercitosAux.add(listaExercitos.get(i));
        }
        
        return exercitosAux;
    }
    
    //Lista de Exercitos que ainda não foram atribuidos a nenhuma regiao
    public ArrayList<Exercito> getListaExercitoSemRegiao(){
        ArrayList<Exercito> exercitosAux = new ArrayList<>();
        
        for(int i=0;i<listaExercitos.size();i++){
            if(listaExercitos.get(i).getRegiao()==null)
                exercitosAux.add(listaExercitos.get(i));
        }
        
        return exercitosAux;
    }
    
    //Lista de regioes com exercitos
    public ArrayList<Regiao> getListaRegioesComExercito(){
        ArrayList<Regiao> regioesAux = new ArrayList<>();
        
        for(int i=0;i<listaExercitos.size();i++){
            if(listaExercitos.get(i).getRegiao()!=null)
                regioesAux.add(listaExercitos.get(i).getRegiao());
        }
        
        return regioesAux;
    }
    
    //Lista de regioes com cidades
    public ArrayList<Regiao> getListaRegioesComCidade(){
        ArrayList<Regiao> regioesAux = new ArrayList<>();
        
        for (int i=0; i < listaCidades.size();i++){
            if (listaCidades.get(i).getRegiao() != null)
                regioesAux.add(listaExercitos.get(i).getRegiao());
        }
        
        return regioesAux;
    }
    
    public ArrayList<Carta> getListaCartaJokers(){
        ArrayList<Carta> cartasAux = new ArrayList<>();
        
        for(int m=0;m<getCartas().size();m++){
            //Adiciona se tiverem jokers
            if(getCartas().get(m).getRecurso().getClass() == RecursoJoker.class){
                cartasAux.add(getCartas().get(m));
            }
        }
        
        return cartasAux;
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
     * @return the cor
     */
    public Color getCor() {
        return cor;
    }
    
    /**
     * @param cor the cor to set
     */
    public void setCor(Color cor) {
        this.cor = cor;
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
     * @param regiao @return the listaExercitos
     */
    public ArrayList<Exercito> getListaExercitos(Regiao regiao) {
        ArrayList<Exercito> list = new ArrayList<>();
        
        for (int i = 0; i < getListaExercitos().size(); i++) {
            // Adiciona com a mesma regiao
            if (getListaExercitos().get(i).getRegiao() == regiao) {
                list.add(getListaExercitos().get(i));
            }
        }
        
        return list;
    }

    /**
     * @return the listaCidades
     */
    public ArrayList<Cidade> getListaCidades() {
        return listaCidades;
    }
    
    /**
     * @param regiao @return the listaCidades
     */
    public ArrayList<Cidade> getListaCidades(Regiao regiao) {
        ArrayList<Cidade> list = new ArrayList<>();
        
        for (int i = 0; i < getListaCidades().size(); i++) {
            // Adiciona com a mesma regiao
            if (getListaCidades().get(i).getRegiao() == regiao) {
                list.add(getListaCidades().get(i));
            }
        }
        
        return list;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }
    
    @Override
    public String toString() {
        return getNome();
    }
}