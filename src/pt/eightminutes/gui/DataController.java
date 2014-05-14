/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.eightminutes.gui;

import java.util.Observable;
import pt.eightminutes.logic.Jogo;

/**
 *
 * @author ricardopereira
 */
public class DataController extends Observable {
    
    Jogo jogo;
    
    public DataController(Jogo jogo) {
        this.jogo = jogo;
    }
    
}
