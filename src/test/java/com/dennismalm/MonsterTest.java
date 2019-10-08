package com.dennismalm;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.Random;

class MonsterTest {
    @Test
    void damageDoneInRange() {
        Random rand = new Random();
        Monster monster = new Monster(10, 20);

        // Test damageDone 1000 times and make sure the damage is in the correct range
        for (int i = 0; i < 1000; i++) {
            int damage = monster.damageDone(rand);
            assertTrue(damage >= 10, "Damage too low");
            assertTrue(damage <= 20, "Damage too high");
        }
    }
}