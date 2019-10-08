package com.dennismalm;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private static GameBoard currentGame = new GameBoard();
    private static Scanner read = new Scanner(System.in);
    static boolean menu() throws InterruptedException {
        currentGame.printGameBoardTrue();
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
        int input = menuInput();
        switch (input) {
            case 1:
            case 2:
            case 3:
            case 4:
                currentGame.move(input);
                break;
            case 5:
                Combat.checkStatus();
                return false;
            case 6:
                System.out.println("Exiting game.");
                return true;
        }
        return false;
    }

    static int menuInput() {
        int input;
        while (true) {
            try {
                input = (read.nextInt());
                if (input > 0 && input <= 6) {
                    break;
                }
            } catch (InputMismatchException ime) {
                read.nextLine();
            }
            System.out.println("Please enter correct instructions.");
        }
        return input;
    }
}
