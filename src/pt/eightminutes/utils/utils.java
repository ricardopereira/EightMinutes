package pt.eightminutes.utils;

import java.util.Random;

public class utils {
    
    public static int randInt(int min, int max) {
        if (max <= 0) 
            return 0;
        
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
