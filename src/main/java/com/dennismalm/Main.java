package com.dennismalm;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        boolean stop;
        do {
            stop = Menu.menu();
            if (Combat.checkIfDead()) {
                stop = true;
            } else if(GameBoard.checkFinishTile()) {
                stop = true;
            }
        } while (!stop);
    }
}