package com.company;

import java.util.Scanner;

public class GameBoard {

    Scanner read = new Scanner(System.in);

    private int[][] labyrinth = new int[][]{
            {3, 3, 3, 4, 3, 3, 3},
            {3, 3, 0, 2, 0, 0, 3},    // 0 == tom plats.
            {3, 0, 2, 2, 3, 2, 3},    // 1 == Spelarens position.
            {3, 2, 3, 3, 0, 0, 3},    // 2 == Monster position.
            {3, 0, 0, 3, 0, 3, 3},    // 3 == Hinder eller väggar.
            {3, 3, 0, 1, 0, 3, 3},    // 4 == Mål / slutet av labyrinten.
            {3, 3, 3, 3, 3, 3, 3}
    };

    private static int playerPositionRow;
    private static int playerPositionCol;

    void move(int menuInput) throws InterruptedException {

        findPlayerPosition();
        int i = playerPositionCol;
        int j = playerPositionRow;
        int row = 0;
        int column = 0;

        if (menuInput == 1) {
            row--;
        } else if (menuInput == 2) {
            column--;
        } else if (menuInput == 3) {
            column++;
        } else if (menuInput == 4) {
            row++;
        }
        movePlayer(i, j, row, column);
    }

    private int checkValidMove(int i, int j) {
        int path = 1;
        int obstacle = 3;
        int monster = 2;
        int corridor = 0;
        if (labyrinth[i][j] == corridor) {
            System.out.println("You delve deeper into the labyrinth.");
            path = 1;
        } else if (labyrinth[i][j] == monster) {
            Combat.monsterEncounter();
            path = 2;
        } else if (labyrinth[i][j] == obstacle) {
            System.out.println("An obstacle blocks the passage.");
            path = 3;
        }
        return path;
    }

    private void movePlayer(int i, int j, int row, int column) throws InterruptedException {
        if (labyrinth[i][j] == 1 && labyrinth[i + row][j + column] != 3) {
            if (checkValidMove(i + row, j + column) == 2) {
                if (Combat.fight() > 0) {
                    labyrinth[i + row][j + column] = 1;
                    playerPositionRow = i + row;
                    playerPositionCol = j + column;
                }
            } else {
                labyrinth[i + row][j + column] = 1;
                playerPositionRow = i + row;
                playerPositionCol = j + column;
            }
            labyrinth[i][j] = 0;
        }
    }

    private void findPlayerPosition() {
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
            System.out.println("\nYou've completed the labyrinth!");
            win = true;
        }
        return win;
    }

    void printGameBoard() {
        System.out.println("-------------");

        for (int[] ints : labyrinth) {
            for (int j = 0; j < labyrinth.length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println(" ");
        }
        System.out.println("-------------");
        System.out.println("1 = Player. 2 = Monster. 3. Obstacle. 4. Exit.");
    }
}