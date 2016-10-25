package Shared.Util;

import java.util.Random;

/**
 * Created by emanuele on 31/03/16.
 */
public class RandomMinMax {

    public static int randInt(int min, int max) {

        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
