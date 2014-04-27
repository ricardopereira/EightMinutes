/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.logic;


public class RecursoJoker extends Recurso{
    
    private Recurso recursoEscolhido;

    public RecursoJoker() {
        super("Joker");
    }

    @Override
    public int getPontuacao(int numCartas) {
        if(recursoEscolhido!=null)
        {
            return recursoEscolhido.getPontuacao(numCartas);
        }
        else
            return 0;
    }

    /**
     * @return the recursoEscolhido
     */
    public Recurso getRecursoEscolhido() {
        return recursoEscolhido;
    }

    /**
     * @param recursoEscolhido the recursoEscolhido to set
     */
    public void setRecursoEscolhido(Recurso recursoEscolhido) {
        this.recursoEscolhido = recursoEscolhido;
    }
    
}
