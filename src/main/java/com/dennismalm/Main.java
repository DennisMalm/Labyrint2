package com.dennismalm;

public class Main {
    private static int[][] labyrinth = new int[][]{
            {3, 3, 3, 4, 3, 3, 3},
            {3, 3, 0, 2, 0, 0, 3},    // 0 == tom plats.
            {3, 1, 2, 2, 3, 2, 3},    // 1 == Spelarens position.
            {3, 2, 3, 3, 0, 0, 3},    // 2 == Monster position.
            {3, 0, 0, 3, 0, 3, 3},    // 3 == Hinder eller väggar.
            {3, 3, 0, 0, 0, 3, 3},    // 4 == Mål / slutet av labyrinten.
            {3, 3, 3, 3, 3, 3, 3}
    };

    private static GameBoard currentGame = GameBoard.createGameBoard(labyrinth);

    public static void main(String[] args) {
        boolean stop = false;
        do {
            currentGame.printGameboard();
            Menu.Choice input = Menu.menu();
            handleInput(input);
            if (currentGame.getPlayer().isDead()) {
                stop = true;
            } else if(currentGame.checkFinishTile()) {
                System.out.println("You found the exit!");
                stop = true;
            }
        } while (!stop);
    }

    public static void handleInput(Menu.Choice input) {
        switch (input) {
            case NORTH:
            case WEST:
            case EAST:
            case SOUTH:
                currentGame.move(input);
                break;
            case STATUS:
                checkStatus(currentGame.getPlayer());
            case QUIT:
                System.out.println("Exiting game.");
        }
    }

    static void checkStatus(Player player) {
        System.out.println("Your life total is: " + player.getLife() + " and you've killed " + player.getKillCount() + " monsters.");
    }

}