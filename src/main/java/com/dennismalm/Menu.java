package com.dennismalm;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    enum Choice {
        NORTH, WEST, EAST, SOUTH, STATUS, QUIT
    }

    private static Scanner read = new Scanner(System.in);
    static Choice menu() {
        System.out.println(
                        "                           \n" +
                        "Get through the labyrinth! \n" +
                        "-------------------------- \n" +
                        "1. Go North. "                 +
                        "2. Go West.                \n" +
                        "3. Go East.  "                 +
                        "4. Go South.               \n" +
                        "5. Check your status.      \n" +
                        "6. Quit.                     "
        );
        return menuInput();
    }

    static Map<Integer, Choice> intToChoice = Map.of(
            1, Choice.NORTH,
            2, Choice.WEST,
            3, Choice.EAST,
            4, Choice.SOUTH,
            5, Choice.STATUS,
            6, Choice.QUIT
    );

    static Choice menuInput() {
        while (true) {
            try {
                int input = (read.nextInt());
                if (input > 0 && input <= 6) {
                    return intToChoice.get(input);
                }
            } catch (InputMismatchException ime) {
                read.nextLine();
            }
            System.out.println("Please enter correct instructions.");
        }
    }
}
