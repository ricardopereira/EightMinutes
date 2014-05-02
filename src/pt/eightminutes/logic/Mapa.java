/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.logic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mapa extends Base implements Serializable {
    
    private Regiao regiaoInicial;
    private ArrayList<Continente> continentes = new ArrayList<>();
    private ArrayList<Regiao> regioesAux = new ArrayList<>();
    
    // Trajectos de cada Região
    private List[][] trajectos;
    private static final int totalMapCols = 3;
    // ToDo - Rever esta constante
    public static final int totalRegioes = 23;
    
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
        // Mapa
        Continente continente1 = new Continente("Continente 1");
        continentes.add(continente1);
        continente1.adicionaRegiao(new Regiao("1",continente1));
        continente1.adicionaRegiao(new Regiao("2",continente1));
        continente1.adicionaRegiao(new Regiao("3",continente1));
        continente1.adicionaRegiao(new Regiao("4",continente1));
        continente1.adicionaRegiao(new Regiao("5",continente1));
        continente1.adicionaRegiao(new Regiao("6",continente1));
        continente1.adicionaRegiao(new Regiao("7",continente1));
        // Define regioes vizinhas
        regioesAux.add(continente1.getRegioes().get(1)); //R2
        regioesAux.add(continente1.getRegioes().get(2)); //R3
        regioesAux.add(continente1.getRegioes().get(3)); //R4
        continente1.getRegioes().get(0).adicionaRegiaoVizinha(regioesAux); //R1
        regioesAux.clear();
        regioesAux.add(continente1.getRegioes().get(0)); //R1
        regioesAux.add(continente1.getRegioes().get(3)); //R4
        continente1.getRegioes().get(1).adicionaRegiaoVizinha(regioesAux); //R2
        regioesAux.clear();
        regioesAux.add(continente1.getRegioes().get(0)); //R1
        regioesAux.add(continente1.getRegioes().get(3)); //R4
        regioesAux.add(continente1.getRegioes().get(4)); //R5
        regioesAux.add(continente1.getRegioes().get(5)); //R6
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
        // Define regioes vizinhas
        regioesAux.clear();
        regioesAux.add(continente2.getRegioes().get(1)); //R7
        continente2.getRegioes().get(0).adicionaRegiaoVizinha(regioesAux); //R8
        regioesAux.clear();
        regioesAux.add(continente2.getRegioes().get(0)); //R8
        continente2.getRegioes().get(1).adicionaRegiaoVizinha(regioesAux); //R9
        
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
        // Define regioes vizinhas
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(1)); //R11
        continente3.getRegioes().get(0).adicionaRegiaoVizinha(regioesAux); //R10
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(0)); //R10
        regioesAux.add(continente3.getRegioes().get(2)); //R12
        regioesAux.add(continente3.getRegioes().get(3)); //R13
        continente3.getRegioes().get(1).adicionaRegiaoVizinha(regioesAux); //R11
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(1)); //R11
        regioesAux.add(continente3.getRegioes().get(3)); //R13
        regioesAux.add(continente3.getRegioes().get(8)); //R18
        continente3.getRegioes().get(2).adicionaRegiaoVizinha(regioesAux); //R12
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(1)); //R11
        regioesAux.add(continente3.getRegioes().get(2)); //R12
        regioesAux.add(continente3.getRegioes().get(4)); //R14
        regioesAux.add(continente3.getRegioes().get(5)); //R15
        regioesAux.add(continente3.getRegioes().get(7)); //R17
        regioesAux.add(continente3.getRegioes().get(8)); //R18
        continente3.getRegioes().get(3).adicionaRegiaoVizinha(regioesAux); //R13
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(3)); //R13
        regioesAux.add(continente3.getRegioes().get(5)); //R15
        continente3.getRegioes().get(4).adicionaRegiaoVizinha(regioesAux); //R14
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(3)); //R13
        regioesAux.add(continente3.getRegioes().get(4)); //R14
        regioesAux.add(continente3.getRegioes().get(6)); //R16
        regioesAux.add(continente3.getRegioes().get(7)); //R17
        continente3.getRegioes().get(5).adicionaRegiaoVizinha(regioesAux); //R15
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(5)); //R15
        regioesAux.add(continente3.getRegioes().get(7)); //R17
        continente3.getRegioes().get(6).adicionaRegiaoVizinha(regioesAux); //R16
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(3)); //R13
        regioesAux.add(continente3.getRegioes().get(5)); //R15
        regioesAux.add(continente3.getRegioes().get(6)); //R16
        regioesAux.add(continente3.getRegioes().get(8)); //R18
        regioesAux.add(continente3.getRegioes().get(10)); //R20
        continente3.getRegioes().get(7).adicionaRegiaoVizinha(regioesAux); //R17
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(2)); //R12
        regioesAux.add(continente3.getRegioes().get(3)); //R13
        regioesAux.add(continente3.getRegioes().get(7)); //R17
        regioesAux.add(continente3.getRegioes().get(9)); //R19
        continente3.getRegioes().get(8).adicionaRegiaoVizinha(regioesAux); //R18
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(8)); //R18
        continente3.getRegioes().get(9).adicionaRegiaoVizinha(regioesAux); //R19
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(7)); //R17
        continente3.getRegioes().get(10).adicionaRegiaoVizinha(regioesAux); //R20
        
        Continente continente4 = new Continente("Continente 4");
        continentes.add(continente4);
        continente4.adicionaRegiao(new Regiao("21",continente4));
        continente4.adicionaRegiao(new Regiao("22",continente4));
        continente4.adicionaRegiao(new Regiao("23",continente4));
        // Define regioes vizinhas
        regioesAux.clear();
        regioesAux.add(continente4.getRegioes().get(1)); //R22
        continente4.getRegioes().get(0).adicionaRegiaoVizinha(regioesAux); //R21
        regioesAux.clear();
        regioesAux.add(continente4.getRegioes().get(0)); //R21
        regioesAux.add(continente4.getRegioes().get(2)); //R23
        continente4.getRegioes().get(1).adicionaRegiaoVizinha(regioesAux); //R22
        regioesAux.clear();
        regioesAux.add(continente4.getRegioes().get(1)); //R22
        continente4.getRegioes().get(2).adicionaRegiaoVizinha(regioesAux); //R23
        

        //Define vizinhos marítimos
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(0)); //R10
        continente1.getRegioes().get(1).adicionaRegiaoVizinha(regioesAux); //R2
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(4)); //R14
        continente1.getRegioes().get(4).adicionaRegiaoVizinha(regioesAux); //R5
        regioesAux.clear();
        regioesAux.add(continente2.getRegioes().get(0)); //R8
        continente1.getRegioes().get(6).adicionaRegiaoVizinha(regioesAux); //R7
        
        regioesAux.clear();
        regioesAux.add(continente1.getRegioes().get(6)); //R7
        continente2.getRegioes().get(0).adicionaRegiaoVizinha(regioesAux); //R8
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(5)); //R15
        continente2.getRegioes().get(1).adicionaRegiaoVizinha(regioesAux); //R9
        
        regioesAux.clear();
        regioesAux.add(continente1.getRegioes().get(1)); //R2
        continente3.getRegioes().get(0).adicionaRegiaoVizinha(regioesAux); //R10
        regioesAux.clear();
        regioesAux.add(continente1.getRegioes().get(4)); //R5
        continente3.getRegioes().get(4).adicionaRegiaoVizinha(regioesAux); //R14
        regioesAux.clear();
        regioesAux.add(continente2.getRegioes().get(1)); //R9
        continente3.getRegioes().get(5).adicionaRegiaoVizinha(regioesAux); //R15
        regioesAux.clear();
        regioesAux.add(continente4.getRegioes().get(0)); //R21
        continente3.getRegioes().get(6).adicionaRegiaoVizinha(regioesAux); //R16
        regioesAux.clear();
        regioesAux.add(continente4.getRegioes().get(2)); //R23
        continente3.getRegioes().get(9).adicionaRegiaoVizinha(regioesAux); //R19
        
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(6)); //R16
        continente4.getRegioes().get(0).adicionaRegiaoVizinha(regioesAux); //R21
        regioesAux.clear();
        regioesAux.add(continente3.getRegioes().get(9)); //R19
        continente4.getRegioes().get(2).adicionaRegiaoVizinha(regioesAux); //R23
    }
    
    public int lerTrajectos() throws FileNotFoundException, IOException {
        // Abrir o ficheiro
        FileInputStream fstream = new FileInputStream("res/map");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                
        String line;
        String field;
        int idx = 0;
        int row = 0;
        int col = 0;
                
        // Total de Regioes
        trajectos = new ArrayList[totalRegioes][totalRegioes];
        
        // Ler linha a linha
        while ((line = br.readLine()) != null) {
            // Conteudo da linha
            String[] fields = line.split(";");
            // Verificar se tem 3 colunas
            if (fields.length < totalMapCols) break;
            
            // Origem
            //fields[0]
            
            // Destino
            //fields[1]
                        
            // Trajecto
            String[] values = fields[2].split(",");
            trajectos[row][col] = new ArrayList<>();
            trajectos[row][col].add(values[0]);
            
            //trajectos[row][col] = values.length;
            
            // Proximo valor
            col++;
            idx++;
            
            // Fim da linha
            if (col + 1 > totalRegioes) {
                row++;
                col = 0;
            }
            
            // Ultima linha
            if (row + 1 > totalRegioes) break;
        }
        
        if (debugMode && debugShowMapFile) {
            // Verificar a leitura
            if (idx != totalRegioes*totalRegioes) {
                System.err.println("Ocorreu um erro");
            }
            else {
                idx = 0;
                for (row=0; row<totalRegioes; row++)
                    for (col=0; col<totalRegioes; col++) {
                        System.out.println(""+ ++idx +": "+trajectos[row][col].get(0));
                    }
            }
        }
        
        // Fecha o ficheiro
        br.close();
        // Retorna as linhas lidas
        return idx;
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
