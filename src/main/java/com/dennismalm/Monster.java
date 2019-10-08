package com.dennismalm;

import java.util.Random;

public class Monster {
    int life = 100;
    final int minDamage;
    final int maxDamage;

    public Monster(int minDamage, int maxDamage) {
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public int damageDone(Random rand) {
        return Utils.randInt(rand, minDamage, maxDamage);
    }

    public int getLife() {
        return life;
    }

    public void damage(int damage) {
        life -= damage;
    }

    public boolean isDead() {
        return life <= 0;
    }
}
