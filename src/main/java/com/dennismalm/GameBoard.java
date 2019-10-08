package com.dennismalm;

public class GameBoard {
    private final Player player;
    private final Position playerPosition;
    private final Position finishTile;

    private final int[][] labyrinth;
    private final Monster[][] monsters;

    public GameBoard(Player player,
                     Position playerPosition,
                     int[][] labyrinth,
                     Monster[][] monsters,
                     Position finishTile) {
        this.player = player;
        this.playerPosition = playerPosition;
        this.labyrinth = labyrinth;
        this.monsters = monsters;
        this.finishTile = finishTile;
    }

    public Player getPlayer() {
        return this.player;
    }

    void move(Menu.Choice menuInput) {
        int dx = 0;
        int dy = 0;

        if (menuInput == Menu.Choice.NORTH) {
            dx--;
        } else if (menuInput == Menu.Choice.WEST) {
            dy--;
        } else if (menuInput == Menu.Choice.EAST) {
            dy++;
        } else if (menuInput == Menu.Choice.SOUTH) {
            dx++;
        }
        movePlayer(dx, dy);
    }

    private boolean isValidMove(int x, int y) {
        int corridor = 0;
        return labyrinth[x][y] == corridor;
    }

    private void movePlayer(int dx, int dy) {
        int x = playerPosition.getX();
        int y = playerPosition.getY();
        if (isValidMove(x + dx, y + dy)) {
            labyrinth[playerPosition.getX()][playerPosition.getY()] = 0;
            playerPosition.move(dx, dy);
            labyrinth[playerPosition.getX()][playerPosition.getY()] = 1;

            Monster monster = monsters[playerPosition.getX()][playerPosition.getY()];
            if (monster != null && !monster.isDead()) {
                Combat.fight(player, monster);
            }
        } else {
            System.out.println("You can't walk that way.");
        }
    }

    boolean checkFinishTile() {
        return playerPosition.equals(finishTile);
    }

    void printGameboard() {
        System.out.println("-------------");

        for (int i = 0; i < labyrinth.length; i++) {
            for (int j = 0; j < labyrinth.length; j++) {
                if (labyrinth[i][j] == 2 || labyrinth[i][j] == 4) {
                    System.out.print("0 ");
                } else if (playerPosition.getX() == i && playerPosition.getY() == j) {
                    System.out.print("1 ");
                } else {
                    System.out.print(labyrinth[i][j] + " ");
                }
            }
            System.out.println(" ");
        }
        System.out.println("-------------");
        System.out.println("1 = Player. 2 = Monster. 3. Obstacle. 4. Exit.");
    }

    private static Position findValuePosition(int[][] labyrinth, int value) {
        for (int x = 0; x < labyrinth.length; x++) {
            for (int y = 0; y < labyrinth.length; y++) {
                if (labyrinth[x][y] == value) {
                    return new Position(x, y);
                }
            }
        }
        return null;
    }

    private static int MONSTER_MIN_DAMAGE = 1;
    private static int MONSTER_MAX_DAMAGE = 10;
    private static int PLAYER_MIN_DAMAGE = 20;
    private static int PLAYER_MAX_DAMAGE = 25;

    private static Monster getNewMonster() {
        return new Monster(MONSTER_MIN_DAMAGE, MONSTER_MAX_DAMAGE);
    }

    public static GameBoard createGameBoard(int[][] labyrinth) {
        Player player = new Player(PLAYER_MIN_DAMAGE, PLAYER_MAX_DAMAGE);
        // Initialise labyrinth map
        int[][] actualLabyrinth = new int[labyrinth.length][];
        for (int x = 0; x < labyrinth.length; x++) {
            actualLabyrinth[x] = new int[labyrinth[x].length];
            for (int y = 0; y < labyrinth[x].length; y++) {
                if (labyrinth[x][y] == 3) {
                    actualLabyrinth[x][y] = 3;
                } else {
                    actualLabyrinth[x][y] = 0;
                }
            }
        }

        // Initialize monsters
        Monster[][] monsters = new Monster[labyrinth.length][];
        for (int x = 0; x < labyrinth.length; x++) {
            monsters[x] = new Monster[labyrinth.length];
            for (int y = 0; y < labyrinth.length; y++) {
                if (labyrinth[x][y] == 2) {
                    monsters[x][y] = getNewMonster();
                }
            }
        }

        Position playerPosition = findValuePosition(labyrinth, 1);
        Position finishTile = findValuePosition(labyrinth, 4);

        return new GameBoard(player, playerPosition, actualLabyrinth, monsters, finishTile);
    }
}