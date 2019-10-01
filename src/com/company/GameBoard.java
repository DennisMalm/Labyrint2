package com.company;

import java.util.Scanner;

public class GameBoard {

    Scanner read = new Scanner(System.in);

    private int[][] labyrinth = new int[][]{
            {3, 3, 3, 4, 3, 3, 3},
            {3, 3, 0, 2, 0, 0, 3},    // 0 == tom plats.
            {3, 0, 2, 2, 3, 2, 3},    // 1 == Spelarens position.
            {3, 2, 3, 0, 0, 0, 3},    // 2 == Monster position.
            {3, 0, 0, 0, 3, 3, 3},    // 3 == Hinder eller väggar.
            {3, 3, 3, 1, 3, 3, 3},     // 4 == Mål / slutet av labyrinten.
            {3, 3, 3, 3, 3, 3, 3}
    };


    int corridor = 0;
    static int playerPositionRow;
    static int playerPositionCol;
    int monster = 2;
    int obstacle = 3;
    int finish = 4;


    void playerMove(int menuInput) throws InterruptedException {


        findPlayerPosition();
        printGameBoard();
        int i = playerPositionCol;
        int j = playerPositionRow;

        if (menuInput == 1) {
            moveNorth(i, j);
        } else if (menuInput == 2) {
            moveWest(i, j);
        } else if (menuInput == 3) {
            moveEast(i, j);
        } else if(menuInput == 4){
            moveSouth(i, j);
        }
        checkFinishTile();
        printGameBoard();
    }

    int checkValidMove(int i, int j) {

        int path = 1;
        if (labyrinth[i][j] == 0) {
            System.out.println("You delve deeper into the labyrinth.");
            path = 1;
        } else if (labyrinth[i][j] == 2) {
            System.out.println("You encounter a monster!");
            path = 2;
        } else if (labyrinth[i][j] == 3) {
            System.out.println("An obstacle blocks the passage.");
            path = 3;
        }


        return path;
    }

    void moveNorth(int i, int j) throws InterruptedException {
        if (labyrinth[i][j] == 1 && labyrinth[i - 1][j] != 3) {
            if (checkValidMove(i - 1, j) == 2) {
                //(labyrinth[i - 1][j] == 2)
                if (Combat.fight() > 0) {
                    labyrinth[i][j] = 0;
                    labyrinth[i - 1][j] = 1;
                    playerPositionRow = i - 1;
                    playerPositionCol = j;
                }
            } else {
                labyrinth[i][j] = 0;
                labyrinth[i - 1][j] = 1;
                playerPositionRow = i - 1;
                playerPositionCol = j;
            }
        }
    }

    void moveWest(int i, int j) throws InterruptedException {
        if (labyrinth[i][j] == 1 && labyrinth[i][j - 1] != 3) {
            if (checkValidMove(i, j - 1) == 2) {
                if (Combat.fight() > 0) {
                    labyrinth[i][j] = 0;
                    labyrinth[i][j - 1] = 1;
                    playerPositionRow = i;
                    playerPositionCol = j - 1;
                    printGameBoard();
                }
            } else {
                labyrinth[i][j] = 0;
                labyrinth[i][j - 1] = 1;
                playerPositionRow = i;
                playerPositionCol = j - 1;
            }
        }
    }

    void moveEast(int i, int j) throws InterruptedException {
        if (labyrinth[i][j] == 1 && labyrinth[i][j + 1] != 3) {
            if (checkValidMove(i, j + 1) == 2) {
                if (Combat.fight() > 0) {
                    labyrinth[i][j] = 0;
                    labyrinth[i][j + 1] = 1;
                    playerPositionRow = i;
                    playerPositionCol = j + 1;
                    printGameBoard();
                }
            } else {
                labyrinth[i][j] = 0;
                labyrinth[i][j + 1] = 1;
                playerPositionRow = i;
                playerPositionCol = j + 1;
            }
        }
    }

    void moveSouth(int i, int j) throws InterruptedException {
        if (labyrinth[i][j] == 1 && labyrinth[i + 1][j] != 3) {
            if (checkValidMove(i+1, j) == 2) {
                if (Combat.fight() > 0) {
                    labyrinth[i][j] = 0;
                    labyrinth[i + 1][j] = 1;
                    playerPositionRow = i + 1;
                    playerPositionCol = j;
                    printGameBoard();
                }
            } else {
                labyrinth[i][j] = 0;
                labyrinth[i + 1][j] = 1;
                playerPositionRow = i + 1;
                playerPositionCol = j;
            }
        }
    }

    void findPlayerPosition() {

        for (int i = 0; i < labyrinth.length; i++) {
            for (int j = 0; j < labyrinth.length; j++) {
                if (labyrinth[i][j] == 1) {
                    playerPositionCol = i;
                    playerPositionRow = j;
                }
            }
        }
    }

    static boolean checkFinishTile() {
        boolean win = false;
        if (playerPositionRow == 0 && playerPositionCol == 3) {
            System.out.println("You've completed the labyrinth!");
            win = true;
        }
        return win;
    }

    void printGameBoard() {
        System.out.println();
        for (int i = 0; i < labyrinth.length; i++) {
            for (int j = 0; j < labyrinth.length; j++) {

                System.out.print(labyrinth[i][j] + " ");
            }
            System.out.println();
        }
    }
}