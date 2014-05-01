/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Mapa implements Serializable{
    private Regiao regiaoInicial;
    private ArrayList<Continente> continentes = new ArrayList<>();
    private ArrayList<Regiao> regioesAux = new ArrayList<>();
    
    public Mapa(){
        defineMapa();
        defineRegiaoInicial();
    }

    public void defineRegiaoInicial(){
        Random rand = new Random();
        int i = rand.nextInt(continentes.size()-1)+1;             
        int m = rand.nextInt(continentes.get(i).getRegioes().size()-1)+1;
        
        setRegiaoInicial(continentes.get(i).getRegioes().get(m));
    }
    
    public void defineMapa(){
        //TODO-definir como se cria o mapa, criado fixo para testar
        
        Continente continente1 = new Continente("Continente 1");
        continentes.add(continente1);
        continente1.adicionaRegiao(new Regiao("1",continente1));
        continente1.adicionaRegiao(new Regiao("2",continente1));
        continente1.adicionaRegiao(new Regiao("3",continente1));
        continente1.adicionaRegiao(new Regiao("4",continente1));
        continente1.adicionaRegiao(new Regiao("5",continente1));
        continente1.adicionaRegiao(new Regiao("6",continente1));
        continente1.adicionaRegiao(new Regiao("7",continente1));
        //define regioes vizinhas
        regioesAux.add(continente1.getRegioes().get(1)); //R2
        regioesAux.add(continente1.getRegioes().get(2)); //R3
        regioesAux.add(continente1.getRegioes().get(3)); //R4
        continente1.getRegioes().get(0).adicionaRegiaoVizinha(regioesAux); //R1
        regioesAux.clear();
        regioesAux.add(continente1.getRegioes().get(0)); //R1
        regioesAux.add(continente1.getRegioes().get(2)); //R3       
        continente1.getRegioes().get(1).adicionaRegiaoVizinha(regioesAux); //R2
        regioesAux.clear();
        regioesAux.add(continente1.getRegioes().get(0)); //R1
        regioesAux.add(continente1.getRegioes().get(2)); //R3
        regioesAux.add(continente1.getRegioes().get(4)); //R5
        continente1.getRegioes().get(2).adicionaRegiaoVizinha(regioesAux); //R3
        regioesAux.clear();
        regioesAux.add(continente1.getRegioes().get(0)); //R1
        regioesAux.add(continente1.getRegioes().get(1)); //R2
        regioesAux.add(continente1.getRegioes().get(2)); //R3
        regioesAux.add(continente1.getRegioes().get(4)); //R5
        continente1.getRegioes().get(3).adicionaRegiaoVizinha(regioesAux); //R4
        regioesAux.clear();
        regioesAux.add(continente1.getRegioes().get(2)); //R3
        regioesAux.add(continente1.getRegioes().get(3)); //R4
        continente1.getRegioes().get(4).adicionaRegiaoVizinha(regioesAux); //R5
        regioesAux.clear();
        regioesAux.add(continente1.getRegioes().get(2)); //R3
        regioesAux.add(continente1.getRegioes().get(6)); //R7
        continente1.getRegioes().get(5).adicionaRegiaoVizinha(regioesAux); //R6
        regioesAux.clear();
        regioesAux.add(continente1.getRegioes().get(5)); //R6
        continente1.getRegioes().get(6).adicionaRegiaoVizinha(regioesAux); //R7
        
        Continente continente2 = new Continente("Continente 2");
        continentes.add(continente2);
        continente2.adicionaRegiao(new Regiao("8",continente2));
        continente2.adicionaRegiao(new Regiao("9",continente2));
        regioesAux.clear();
        regioesAux.add(continente2.getRegioes().get(1)); //R
        continente2.getRegioes().get(0).adicionaRegiaoVizinha(regioesAux); //R
        regioesAux.clear();
        regioesAux.add(continente2.getRegioes().get(0)); //R
        continente2.getRegioes().get(1).adicionaRegiaoVizinha(regioesAux); //R
        
        Continente continente3 = new Continente("Continente 3");
        continentes.add(continente3);
        continente3.adicionaRegiao(new Regiao("10",continente3));
        continente3.adicionaRegiao(new Regiao("11",continente3));
        continente3.adicionaRegiao(new Regiao("12",continente3));
        continente3.adicionaRegiao(new Regiao("13",continente3));
        continente3.adicionaRegiao(new Regiao("14",continente3));
        continente3.adicionaRegiao(new Regiao("15",continente3));
        continente3.adicionaRegiao(new Regiao("16",continente3));
        continente3.adicionaRegiao(new Regiao("17",continente3));
        continente3.adicionaRegiao(new Regiao("18",continente3));
        continente3.adicionaRegiao(new Regiao("19",continente3));
        continente3.adicionaRegiao(new Regiao("20",continente3));
        //define regioes vizinhas
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(1)); //R
        continente3.getRegioes().get(0).adicionaRegiaoVizinha(regioesAux); //R
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(0)); //R
        regioesAux.add(continente3.getRegioes().get(2)); //R
        regioesAux.add(continente3.getRegioes().get(3)); //R
        continente3.getRegioes().get(1).adicionaRegiaoVizinha(regioesAux); //R
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(1)); //R
        regioesAux.add(continente3.getRegioes().get(3)); //R
        regioesAux.add(continente3.getRegioes().get(8)); //R
        continente3.getRegioes().get(2).adicionaRegiaoVizinha(regioesAux); //R
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(1));
        regioesAux.add(continente3.getRegioes().get(2));
        regioesAux.add(continente3.getRegioes().get(4));
        regioesAux.add(continente3.getRegioes().get(5));
        regioesAux.add(continente3.getRegioes().get(7));
        regioesAux.add(continente3.getRegioes().get(8));
        continente3.getRegioes().get(3).adicionaRegiaoVizinha(regioesAux);
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(3));
        regioesAux.add(continente3.getRegioes().get(5));
        continente3.getRegioes().get(4).adicionaRegiaoVizinha(regioesAux);        
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(3));
        regioesAux.add(continente3.getRegioes().get(4));
        regioesAux.add(continente3.getRegioes().get(6));
        regioesAux.add(continente3.getRegioes().get(7));
        continente3.getRegioes().get(5).adicionaRegiaoVizinha(regioesAux);        
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(5));
        regioesAux.add(continente3.getRegioes().get(7));
        continente3.getRegioes().get(6).adicionaRegiaoVizinha(regioesAux); 
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(3));
        regioesAux.add(continente3.getRegioes().get(5));
        regioesAux.add(continente3.getRegioes().get(6));
        regioesAux.add(continente3.getRegioes().get(8));
        regioesAux.add(continente3.getRegioes().get(10));
        continente3.getRegioes().get(7).adicionaRegiaoVizinha(regioesAux);
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(2));
        regioesAux.add(continente3.getRegioes().get(3));
        regioesAux.add(continente3.getRegioes().get(7));
        regioesAux.add(continente3.getRegioes().get(9));
        continente3.getRegioes().get(8).adicionaRegiaoVizinha(regioesAux);
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(8));
        continente3.getRegioes().get(9).adicionaRegiaoVizinha(regioesAux);
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(7));
        continente3.getRegioes().get(10).adicionaRegiaoVizinha(regioesAux);
        
        Continente continente4 = new Continente("Continente 4");
        continentes.add(continente4);
        continente4.adicionaRegiao(new Regiao("21",continente4));
        continente4.adicionaRegiao(new Regiao("22",continente4));
        continente4.adicionaRegiao(new Regiao("23",continente4));
        regioesAux.clear();
        regioesAux.add(continente4.getRegioes().get(1));        
        continente4.getRegioes().get(0).adicionaRegiaoVizinha(regioesAux); 
        regioesAux.clear();
        regioesAux.add(continente4.getRegioes().get(0));    
        regioesAux.add(continente4.getRegioes().get(2)); 
        continente4.getRegioes().get(1).adicionaRegiaoVizinha(regioesAux);  
        regioesAux.clear();
        regioesAux.add(continente4.getRegioes().get(1));        
        continente4.getRegioes().get(2).adicionaRegiaoVizinha(regioesAux); 
        
        //Define vizinhos marítimos
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(0));        
        continente1.getRegioes().get(1).adicionaRegiaoVizinha(regioesAux); 
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(4));        
        continente1.getRegioes().get(4).adicionaRegiaoVizinha(regioesAux); 
        regioesAux.clear();
        regioesAux.add(continente2.getRegioes().get(0));        
        continente1.getRegioes().get(6).adicionaRegiaoVizinha(regioesAux); 
        
        regioesAux.clear();
        regioesAux.add(continente1.getRegioes().get(6));        
        continente2.getRegioes().get(0).adicionaRegiaoVizinha(regioesAux); 
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(5));        
        continente2.getRegioes().get(1).adicionaRegiaoVizinha(regioesAux); 
        
        regioesAux.clear();
        regioesAux.add(continente1.getRegioes().get(1));        
        continente3.getRegioes().get(0).adicionaRegiaoVizinha(regioesAux); 
        regioesAux.clear();
        regioesAux.add(continente1.getRegioes().get(4));        
        continente3.getRegioes().get(4).adicionaRegiaoVizinha(regioesAux); 
        regioesAux.clear();
        regioesAux.add(continente2.getRegioes().get(1));        
        continente3.getRegioes().get(5).adicionaRegiaoVizinha(regioesAux); 
        regioesAux.clear();
        regioesAux.add(continente4.getRegioes().get(0));        
        continente3.getRegioes().get(6).adicionaRegiaoVizinha(regioesAux); 
        regioesAux.clear();
        regioesAux.add(continente4.getRegioes().get(2));        
        continente3.getRegioes().get(9).adicionaRegiaoVizinha(regioesAux); 
        
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(6));        
        continente4.getRegioes().get(0).adicionaRegiaoVizinha(regioesAux); 
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(9));        
        continente4.getRegioes().get(2).adicionaRegiaoVizinha(regioesAux); 
    }
    
    /**
     * @return the continentes
     */
    public ArrayList<Continente> getContinentes() {
        return continentes;
    }

    /**
     * @param continentes the continentes to set
     */
    public void setContinentes(ArrayList<Continente> continentes) {
        this.continentes = continentes;
    }

    /**
     * @return the regiaoInicial
     */
    public Regiao getRegiaoInicial() {
        return regiaoInicial;
    }

    /**
     * @param regiaoInicial the regiaoInicial to set
     */
    public void setRegiaoInicial(Regiao regiaoInicial) {
        this.regiaoInicial = regiaoInicial;
    }
    
    @Override
    public String toString() {
        String s = "";
        s += "Número de Continentes :" + continentes.size()+"\n";
        for(int i=0;i<continentes.size();i++)
        {
            s += "  "+continentes.get(i).getNome() +"\n";
            for(int m=0;m<continentes.get(i).getQtdRegioes();m++)
            {
                s+="        Região:"+continentes.get(i).getRegioes().get(m).getNome()+"\n";
            }
        }
        return s;
    }  
}
