package com.dennismalm;

import java.util.Random;

public class Utils {
    static int randInt(Random rand, int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
}
