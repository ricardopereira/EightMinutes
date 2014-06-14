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
    private ArrayList<Regiao> regioes = new ArrayList<>();
    
    // Trajectos de cada Região
    private List[][] trajectos;
    private static final int totalMapCols = 3;
    
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
        // Auxiliar
        ArrayList<Regiao> regioesAux = new ArrayList<>();

        regioes.clear();
        continentes.clear();
        
        // Mapa        
        // CONTINENTE 1
        Continente continente1 = new Continente("c1",this);
        continentes.add(continente1);
        regioes.add(continente1.adicionaRegiao(new Regiao("1",1,continente1)));
        regioes.add(continente1.adicionaRegiao(new Regiao("2",2,continente1)));
        regioes.add(continente1.adicionaRegiao(new Regiao("3",3,continente1)));
        regioes.add(continente1.adicionaRegiao(new Regiao("4",4,continente1)));
        regioes.add(continente1.adicionaRegiao(new Regiao("5",5,continente1)));
        regioes.add(continente1.adicionaRegiao(new Regiao("6",6,continente1)));
        regioes.add(continente1.adicionaRegiao(new Regiao("7",7,continente1)));
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
        
        // CONTINENTE 2
        Continente continente2 = new Continente("c2",this);
        continentes.add(continente2);
        regioes.add(continente2.adicionaRegiao(new Regiao("8",8,continente2)));
        regioes.add(continente2.adicionaRegiao(new Regiao("9",9,continente2)));
        // Define regioes vizinhas
        regioesAux.clear();
        regioesAux.add(continente2.getRegioes().get(1)); //R7
        continente2.getRegioes().get(0).adicionaRegiaoVizinha(regioesAux); //R8
        regioesAux.clear();
        regioesAux.add(continente2.getRegioes().get(0)); //R8
        continente2.getRegioes().get(1).adicionaRegiaoVizinha(regioesAux); //R9
        
        // CONTINENTE 3
        Continente continente3 = new Continente("c3",this);
        continentes.add(continente3);       
        regioes.add(continente3.adicionaRegiao(new Regiao("10",10,continente3)));
        regioes.add(continente3.adicionaRegiao(new Regiao("11",11,continente3)));
        regioes.add(continente3.adicionaRegiao(new Regiao("12",12,continente3)));
        regioes.add(continente3.adicionaRegiao(new Regiao("13",13,continente3)));
        regioes.add(continente3.adicionaRegiao(new Regiao("14",14,continente3)));
        regioes.add(continente3.adicionaRegiao(new Regiao("15",15,continente3)));
        regioes.add(continente3.adicionaRegiao(new Regiao("16",16,continente3)));
        regioes.add(continente3.adicionaRegiao(new Regiao("17",17,continente3)));
        regioes.add(continente3.adicionaRegiao(new Regiao("18",18,continente3)));
        regioes.add(continente3.adicionaRegiao(new Regiao("19",19,continente3)));
        regioes.add(continente3.adicionaRegiao(new Regiao("20",20,continente3)));
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
        
        // CONTINENTE 4
        Continente continente4 = new Continente("c4",this);
        continentes.add(continente4);
        regioes.add(continente4.adicionaRegiao(new Regiao("21",21,continente4)));
        regioes.add(continente4.adicionaRegiao(new Regiao("22",22,continente4)));
        regioes.add(continente4.adicionaRegiao(new Regiao("23",23,continente4)));
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
        int totalRegioes = regioes.size();
        
        if (totalRegioes == 0)
            return 0;
        
        // Ler linha a linha
        while ((line = br.readLine()) != null) {
            // Conteudo da linha
            String[] fields = line.split(";");
            // Verificar se tem 3 colunas
            if (fields.length < totalMapCols) break;
            
            // Origem
            //fields[0] - row
            
            // Destino
            //fields[1] - col
                        
            // Trajecto
            String[] values = fields[2].split(",");
            
            if (getTrajectos()[row][col] == null)
                getTrajectos()[row][col] = new ArrayList<>();
            else
                getTrajectos()[row][col].clear();
            
            // Para cada regiao do trajecto
            for (String regiaoAux : values) {
                // ToDo: Utilizar Iterators!!
                // Pesquisar instancia Regiao
                for (Regiao item : regioes) {
                    if (item.getNome().equals(regiaoAux)) {
                        // Adiciona a instancia da regiao para lista do trajecto
                        getTrajectos()[row][col].add(item);
                        break;
                    }
                }
            }
                        
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
        
        if (debugMode && debugShowMapaTrajectos) {
            // Verificar a leitura
            if (idx != totalRegioes*totalRegioes) {
                System.err.println("Ocorreu um erro");
            }
            else {
                idx = 0;
                // Escreve o trajecto para debug
                for (row=0; row<totalRegioes; row++)
                    for (col=0; col<totalRegioes; col++) {
                        System.out.print(""+ ++idx +": ");
                        if (getTrajectos()[row][col] != null)
                            for (Object item : getTrajectos()[row][col])
                                System.out.print(((Regiao)item).getNome()+",");
                        System.out.print("\n");
                    }
            }
        }
        // Fecha o ficheiro
        br.close();
        // Retorna as linhas lidas
        return idx;
    }
    
    private List[][] getTrajectos() {
        if (trajectos == null)
            trajectos = new ArrayList[regioes.size()][regioes.size()];
        return trajectos;
    }
    
    public ArrayList<Regiao> getTrajecto(Regiao origem, Regiao destino) {
        if (origem == null || destino == null)
            return null;
        
        // Validacoes
        if (origem.getMapIndex() >= regioes.size())
            return null;
        if (destino.getMapIndex() >= regioes.size())
            return null;

        // Obtem o trajecto: origem ate destino
        return (ArrayList<Regiao>)getTrajectos()[origem.getIndex()][destino.getIndex()];        
    }
    
    public int getTrajectosCount() {
        int row, col, c = 0;
        // Escreve o trajecto para debug
        for (row=0; row<getTrajectos().length; row++)
            for (col=0; col<getTrajectos().length; col++) {
                if (getTrajectos()[row][col] != null)
                    c++;
            }
        return c;
    }
    
    public ArrayList<Continente> getContinentesOndeRegiaoTemExercitosDoJogador(Jogador jogador, boolean addRegiaoComCidade) {
        ArrayList<Continente> continentesAux= new ArrayList<>();
        for (Continente item : continentes) {            
            if (item.temExercitosDoJogador(jogador,addRegiaoComCidade))
                continentesAux.add(item);
        }
        return continentesAux;
    }
    
    /**
     * @return the regioes
     */
    public ArrayList<Regiao> getRegioes() {
        return regioes;
    }
    
    /**
     * @return the regioes
     */
    public ArrayList<Regiao> getRegioesComExercitos() {
        ArrayList<Regiao> list = new ArrayList<>();
        
        for (Regiao regiao : getRegioes()) {
            if (!regiao.getExercitos().isEmpty())
                list.add(regiao);
        }
        
        return list;
    }
    
    /**
     * @param name @return the regiao
     */
    public Regiao getRegiaoByAreaName(String name) {
        Regiao item;
        for (int i = 0; i < getRegioes().size(); i++) {
            item = getRegioes().get(i);
            if (item.getAreaName().equals(name))
                return item;
        }
        return null;
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
