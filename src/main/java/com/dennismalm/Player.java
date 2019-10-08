package com.dennismalm;

import java.util.Random;

public class Player {
    private int life = 100;
    private int killCount = 0;
    final int minDamage;
    final int maxDamage;

    public Player(int minDamage, int maxDamage) {
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

    public void heal(int healing) {
        life += healing;
    }

    public boolean isDead() {
        return life <= 0;
    }

    public void addKill() {
        killCount += 1;
    }

    public int getKillCount() {
        return killCount;
    }
}
