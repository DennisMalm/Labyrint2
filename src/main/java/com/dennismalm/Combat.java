package com.dennismalm;

import java.util.Random;

public class Combat {
    private static Random rand = new Random();

    /*
     * Returns true if player beats monster.
     */
    static boolean fight(Player player, Monster monster) {
        monsterEncounter();
        int swing = 2;

        do {
            if (swing % 2 == 0) {
                monster.damage(player.damageDone(rand));
                System.out.println("Monster life: " + monster.getLife() +
                        "\n----------------------");
                delay();
                if (monster.isDead()) {
                    player.addKill();;
                    String healthBack = "";
                    int regen = rand.nextInt(100) + 1;
                    if (regen > 50) {
                        System.out.println("And you regain 10 life.");
                        player.heal(10);
                    }
                    return true;
                }

            } else if (swing % 2 == 1) {
                player.damage(monster.damageDone(rand));
                System.out.println("Your life total: " + player.getLife() + "\n" +
                        "----------------------");
                delay();
                if (player.isDead()) {
                    System.out.println("You have been slain...");
                    return false;
                }
            }

            swing++;
        } while (true);
    }

    private static void delay() {
        // https://stackoverflow.com/questions/24104313/how-do-i-make-a-delay-in-java
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
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
        System.out.println("Fight starts.\n" +
                "------------------------------------------");

    }
}
