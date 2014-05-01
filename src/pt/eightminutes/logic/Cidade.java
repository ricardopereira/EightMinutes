/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.logic;


public class Cidade extends Peca{

    public Cidade(Cor cor,Jogador jogador) {
        super("Cidade", null,cor,jogador);
    }    
    
    public void colocaCidade(Regiao regiao){
        setRegiao(regiao);
        regiao.getPecas().add(this);
    }
    
}
