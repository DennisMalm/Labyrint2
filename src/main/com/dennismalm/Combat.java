package com.dennismalm;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Combat {


    private static int monsterLife = 100;
    private static int playerLife = 100;
    private static int killCount;

    static int fight() throws InterruptedException {

        System.out.println("Fight starts.\n" +
                "------------------------------------------");
        int swing = 2;
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
                    String healthBack = "";
                    int regen = ThreadLocalRandom.current().nextInt(1, 100);
                    if (regen > 50) {
                        healthBack = "And you regain 10 life.";
                        playerLife += 10;
                    }
                    monsterLife = 100;
                    System.out.println("You have defeated the monster. " + healthBack + "\n" +
                            "----------------------");
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

    private static void delay() throws InterruptedException {
        Thread.sleep(1000);
    }

    static void checkStatus() {
        System.out.println("Your life total is: " + playerLife + " and you've killed " + killCount + " monsters.");
    }

    static boolean checkIfDead() {
        boolean dead;
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
