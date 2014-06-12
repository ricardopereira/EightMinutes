package pt.eightminutes.logic;

public class Cidade extends Peca {

    public Cidade(Jogador jogador) {
        super("Cidade", null, jogador);
    }    
    
    public void colocaCidade(Regiao regiao){
        setRegiao(regiao);
        regiao.getPecas().add(this);
    }
    
}
