package com.dennismalm;

import java.util.Scanner;

public class GameBoard {

    Scanner read = new Scanner(System.in);

    private int[][] labyrinth = new int[][]{
            {3, 3, 3, 4, 3, 3, 3},
            {3, 3, 0, 2, 0, 0, 3},    // 0 == tom plats.
            {3, 1, 2, 2, 3, 2, 3},    // 1 == Spelarens position.
            {3, 2, 3, 3, 0, 0, 3},    // 2 == Monster position.
            {3, 0, 0, 3, 0, 3, 3},    // 3 == Hinder eller väggar.
            {3, 3, 0, 0, 0, 3, 3},    // 4 == Mål / slutet av labyrinten.
            {3, 3, 3, 3, 3, 3, 3}
    };
    private int[][] shownLabyrinth = labyrinth;


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

    private void movePlayer(int playerPosX, int playerPosY, int row, int column) throws InterruptedException {
        if (labyrinth[playerPosX][playerPosY] == 1 && labyrinth[playerPosX + row][playerPosY + column] != 3) {
            if (checkValidMove(playerPosX + row, playerPosY + column) == 2) {
                if (Combat.fight() > 0) {
                    labyrinth[playerPosX + row][playerPosY + column] = 1;
                    playerPositionRow = playerPosX + row;
                    playerPositionCol = playerPosY + column;
                }
            } else {
                labyrinth[playerPosX + row][playerPosY + column] = 1;
                playerPositionRow = playerPosX + row;
                playerPositionCol = playerPosY + column;
            }
            labyrinth[playerPosX][playerPosY] = 0;
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

    void printGameBoardTrue() {
        System.out.println("-------------");

        for (int[] ints : labyrinth) {
            for (int j = 0; j < labyrinth.length; j++) {
                if (ints[j] == 2 || ints[j] == 4)
                    System.out.print("0" + " ");
                else
                System.out.print(ints[j] + " ");
            }
            System.out.println(" ");
        }
        System.out.println("-------------");
        System.out.println("1 = Player. 2 = Monster. 3. Obstacle. 4. Exit.");
    }
}