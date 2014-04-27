/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.logic;

import java.io.Serializable;
import java.util.ArrayList;


public class Jogador implements Serializable{
    private String nome;
    private int moedas;
    private int aposta;
    private ArrayList<Carta> cartas = new ArrayList<>();
    private ArrayList<Peca> pecas = new ArrayList<>();
    private Carta cartaActiva;
    
    public Jogador(){
        
    }
    
    public Jogador(String nome,Cor cor, int moedas, int qtdExercito, int qtdCidades){
        this.nome = nome;
        this.moedas = moedas;
        for(int i=0;i<qtdExercito;i++)
            pecas.add(new Exercito(cor));
        
        for(int m=0;m<qtdCidades;m++)
            pecas.add(new Cidade(cor));                   
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
            for(int i=0;i<exercitos.size();i++){
                for(int m=0;m<pecas.size();m++){
                    if((pecas.get(m) instanceof Exercito)&&(pecas.get(m)==exercitos.get(i)))
                    {
                        ((Exercito)pecas.get(m)).colocaExercito(regiao);
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
        for(int i=0;i<exercitos.size();i++){
            for(int m=0;m<pecas.size();m++){
                if((pecas.get(m) instanceof Exercito)&&(pecas.get(m)==exercitos.get(i))){
                    ((Exercito)pecas.get(m)).moveExercito(regiao);
                    if(getCartaActiva()!=null)
                        getCartaActiva().getAccaoActiva().setQtd(getCartaActiva().getAccaoActiva().getQtd()-1);
                    
                    bloqueiaAccaoExtra();
                    break;
                }
            }                           
        }       
    }
    
    public void moveExercitoAgua(Regiao regiao, ArrayList<Exercito> exercitos){
        for(int i=0;i<exercitos.size();i++){
            for(int m=0;m<pecas.size();m++){
                if((pecas.get(m) instanceof Exercito)&&(pecas.get(m)==exercitos.get(i))){
                    ((Exercito)pecas.get(m)).moveExercito(regiao);
                    if(getCartaActiva()!=null)
                        getCartaActiva().getAccaoActiva().setQtd(getCartaActiva().getAccaoActiva().getQtd()-1);
                    
                    bloqueiaAccaoExtra();
                    break;
                }
            }                           
        }       
    }
    
    public void destroiExercito(Exercito exercito){
        ArrayList<Exercito> exercitosAux = getListaExercitos();
        int myIdx=exercitosAux.indexOf(exercito);
        
        if(myIdx!=-1){           
            exercitosAux.get(myIdx).destroiExercito();
            if(getCartaActiva()!=null)
                getCartaActiva().getAccaoActiva().setQtd(getCartaActiva().getAccaoActiva().getQtd()-1);
                
                bloqueiaAccaoExtra();
        }                                   
    }
    
    public void colocaCidade(Regiao regiao){
        ArrayList<Cidade> cidadesAux = getListaCidades();
        
        for(int m=0;m<cidadesAux.size();m++){
            if(cidadesAux.get(m).getRegiao()==null){
                cidadesAux.get(m).colocaCidade(regiao);
                
                if(getCartaActiva()!=null)
                    getCartaActiva().getAccaoActiva().setQtd(getCartaActiva().getAccaoActiva().getQtd()-1);
                
                bloqueiaAccaoExtra();
                break;
            }
        }                                 
    }
    
    public ArrayList<Exercito> getListaExercitos(){
        ArrayList<Exercito> exercitosAux= new ArrayList<>();
        
        for(int m=0;m<pecas.size();m++){
            if((pecas.get(m) instanceof Exercito)&&(pecas.get(m)!=null)){
                exercitosAux.add((Exercito)pecas.get(m));
            }
        }
        return exercitosAux;
    }        
    
    public ArrayList<Cidade> getListaCidades(){
        ArrayList<Cidade> cidadesAux= new ArrayList<>();
        
        for(int m=0;m<pecas.size();m++){
            if((pecas.get(m) instanceof Cidade)&&(pecas.get(m)!=null)){
                cidadesAux.add((Cidade)pecas.get(m));
            }
        }
        return cidadesAux;
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
        for(int m=0;m<pecas.size();m++){
            if((pecas.get(m) instanceof Cidade)&&(pecas.get(m).getRegiao()!=null)){
                if(cont==idx)
                    return (Cidade)pecas.get(m);
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
     * @return the pecas
     */
    public ArrayList<Peca> getPecas() {
        return pecas;
    }

    /**
     * @param pecas the pecas to set
     */
    public void setPecas(ArrayList<Peca> pecas) {
        this.pecas = pecas;
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
}
