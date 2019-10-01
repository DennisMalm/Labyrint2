package com.company;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Combat {


    static int monsterLife = 100;
    static int playerLife = 100;
    static int swing;
    static int killCount;
    public static int fight() throws InterruptedException {


        System.out.println("Fight starts.");
         // monsterLife = 100;
         // playerLife = 100;
        swing = 2;
        boolean fighting = true;
        boolean alive;

        do {

            if (swing % 2 == 0) {
                monsterLife = monsterLife - damageDone(swing);
                System.out.println("Monstrets liv: " + monsterLife);
                delay();
                if (monsterLife <= 0) {
                    killCount++;
                    playerLife += 10;
                    monsterLife = 100;
                    System.out.println("You have defeated the monster and heal yourself for 10 life.");
                    fighting = false;

                }

            } else if (swing % 2 == 1) {
                playerLife = playerLife - damageDone(swing);
                System.out.println("Ditt liv: " + playerLife);
                delay();
                if (checkIfDead()) {
                    fighting = false;
                }
            }

            swing++;
        } while (fighting);
        return playerLife;
    }

    private static int damageDone(int swing) {
        Random randomNumber = new Random();
        if (swing % 2 == 1) {
            int damage = ThreadLocalRandom.current().nextInt(1, 10);
            System.out.println("Monstret slår för " + damage);
            return damage;
        } else {
            int damage = ThreadLocalRandom.current().nextInt(5, 25);
            System.out.println("Du slår för " + damage);
            return damage;
        }
    }

    public static void delay() throws InterruptedException {
        Thread.sleep(10);
    }
    static void checkStatus(){
        System.out.println("Your life total is: " + playerLife + " and you've killed " + killCount + " monsters.");
    }
    static boolean checkIfDead(){
        boolean dead = false;
        if(playerLife <= 0) {
            System.out.println("You have been slain by the angry rabbit... :(");
            dead = true;
        }
        else{
            dead = false;
        }
        return dead;
    }
}
