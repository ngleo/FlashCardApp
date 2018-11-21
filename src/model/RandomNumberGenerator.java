package model;

import java.util.Random;

public class RandomNumberGenerator {
    public int max;
    public Random rand;

    public RandomNumberGenerator(int max) {
        rand = new Random();
        this.max = max;
    }

    public int getRand() {
        return rand.nextInt(this.max);
    }
}
