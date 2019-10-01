package com.company;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Combat {


    static int monsterLife = 100;
    static int playerLife = 100;
    static int swing;
    static int killCount;

    public static int fight() throws InterruptedException {

        System.out.println("Fight starts.\n" +
                           "------------------------------------------------------------");
        // monsterLife = 100;
        // playerLife = 100;
        swing = 2;
        boolean fighting = true;
        boolean alive;

        do {
            if (swing % 2 == 0) {
                monsterLife = monsterLife - damageDone(swing);
                System.out.println("Monster life: " + monsterLife +
                                "\n----------------------");
                delay();
                if (monsterLife <= 0) {
                    killCount++;
                    playerLife += 10;
                    monsterLife = 100;
                    System.out.println("You have defeated the monster and heal yourself for 10 life.\n" +
                                       "------------------------------------------------------------");
                    fighting = false;
                }

            } else if (swing % 2 == 1) {
                playerLife = playerLife - damageDone(swing);
                System.out.println("Your life total: " + playerLife + "\n" +
                                   "----------------------");
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
            System.out.println("The monster hit for " + damage);
            return damage;
        } else {
            int damage = ThreadLocalRandom.current().nextInt(5, 25);
            System.out.println("You hit the monster for " + damage);
            return damage;
        }
    }

    public static void delay() throws InterruptedException {
        Thread.sleep(500);
    }

    static void checkStatus() {
        System.out.println("Your life total is: " + playerLife + " and you've killed " + killCount + " monsters.");
    }

    static boolean checkIfDead() {
        boolean dead = false;
        if (playerLife <= 0) {
            System.out.println("You have been slain...");
            dead = true;
        } else {
            dead = false;
        }
        return dead;
    }

    static void monsterEncounter() {
        System.out.println("\nYou encounter a monster!");
        System.out.println("------------------------------------------------" +
                "             /\\\n" +
                "            ( ;`~v/~~~ ;._\n" +
                "         ,/'\"/^) ' < o\\  '\".~'\\\\\\--,\n" +
                "       ,/\",/W  u '`. ~  >,._..,   )'\n" +
                "      ,/'  w  ,U^v  ;//^)/')/^\\;~)'\n" +
                "   ,/\"'/   W` ^v  W |;         )/'\n" +
                " ;''  |  v' v`\" W }  \\\\\n" +
                "\"    .'\\    v  `v/^W,) '\\)\\.)\\/)\n" +
                "         `\\   ,/,)'   ''')/^\"-;'\n" +
                "              \\\n" +
                "            \".\n" +
                "           \\                       \n" +
                "------------------------------------------------\n");
    }
}
