
/*
     * Explain what the program does
     * This code implements a basic version of the game "The Game of Life" where
     * players move on a game board and try to
     * win money. The game board is represented by a 2D array of characters, and
     * each player is represented by a row in a 2D array of integers.
     * The program prints the current player's position and available money,
     * determines the effect of
     * the current position on the player's money, checks if the current player has
     * won, and switches to the next player.
     */

import java.util.Scanner;
import java.util.Random;

public class GameOfLife {
    public static void main(String[] args) {
        System.out.println(
                "Welcome to the Game of Life! In this game, players move around a game board and try to accumulate money.\n\n\n");

        // create a 2D array to represent the game board
        char[][] board = new char[5][5];

        // initialize the board with stars, + and -
        /*
         * This code block initializes the board array with characters * and + and - in
         * a specific pattern.
         * Each even row and column of the array will have a +, odd row and column will
         * have -,
         * and the rest of the cells will have a *. This pattern is used to represent
         * different types of cells in the game.
         */
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    board[i][j] = '+';
                } else if (i % 2 == 1 && j % 2 == 1) {
                    board[i][j] = '-';
                } else {
                    board[i][j] = '*';
                }
            }
        }

        // Add blocked or forbidden locations on the board
        // This information can be recorded in the square as negative number for
        // example,
        // label = -1000
        board[1][1] = '-';
        board[3][3] = '-';

        // create an array to store the player's information
        int[][] players = new int[4][2]; // each row represents a player, column 0 stores the row, column 1 stores the
                                         // column
        int[] money = new int[4]; // stores each player's money
        boolean[] hasWon = new boolean[4]; // stores whether each player has won
        for (int i = 0; i < 4; i++) {
            players[i][0] = 0;
            players[i][1] = 0;
            money[i] = 100; // give each player $100
            hasWon[i] = false; // initialize each player to not have won
        }

        // print a welcome message
        System.out.println("Welcome, Sewar ! \n");

        // play the game
        int currentPlayer = 0; // start with player 1

        while (true) {
            // print the current player's position and available money
            System.out.println(
                    "Player " + (currentPlayer + 1) + ", you are at position (" + players[currentPlayer][0] + ", "
                            + players[currentPlayer][1] + ")\n");
            System.out.println("You currently have $" + money[currentPlayer] + "\n");

            // check the effect of the current position on the player's money
            /*
             * It then checks the effect of the current position on the player's money by
             * looking up the corresponding cell in the "board" array.
             * If the cell is a plus sign, the player gains $250.
             * If it's a minus sign, the player loses $25. If it's a star, the player's
             * money does not change.
             */

            // Create a Scanner object to read input from the console
            Scanner scanner = new Scanner(System.in);

            // Prompt the user to input either 'r' to roll the dice or 'q' to quit the game
            System.out.println("Press 'r' to roll the dice or 'q' to quit the game:");

            // Read the user's input
            String input = scanner.nextLine();

            // If the user entered 'q', end the game and print a thank you message
            if (input.equals("q")) {
                System.out.println("Thanks for playing!");
                break;
            }

            // If the user entered 'r', generate a random dice roll between 1 and 6
            // (inclusive)
            Random random = new Random();
            int diceRoll = random.nextInt(6) + 1;

            // Print out the dice roll for the current player
            System.out.println("You rolled a " + diceRoll + "!\n");

            // Update the current player's position on the game board based on the dice roll
            int newRow = (players[currentPlayer][0] + diceRoll) % board.length;
            int newCol = (players[currentPlayer][1] + diceRoll) % board[0].length;
            players[currentPlayer][0] = newRow;
            players[currentPlayer][1] = newCol;

            // Look up the game board cell that the player landed on and calculate any
            // change in money
            int row = players[currentPlayer][0];
            int col = players[currentPlayer][1];
            char cell = board[row][col];
            int changeInMoney = 0;
            if (cell == '+') {
                changeInMoney = 250;
            } else if (cell == '-') {
                changeInMoney = -25;
            } else if (cell == '*') {
                changeInMoney = 0;
            }

            // At this point, the current player's position and change in money have been
            // calculated based on the dice roll
            // and the game board. The rest of the game logic would continue from here.

            // update the player's money and check if they have won
            money[currentPlayer] += changeInMoney;
            if (money[currentPlayer] >= 500) {
                hasWon[currentPlayer] = true;
                System.out.println("Congratulations, Player " + (currentPlayer + 1) + "! You have won!\n");
                // check if all players have won
                boolean allPlayersHaveWon = true;
                for (boolean won : hasWon) {
                    if (!won) {
                        allPlayersHaveWon = false;
                        break;
                    }
                    /*
                     * If a player has $200 or more, the code sets the corresponding element in the
                     * "hasWon" array to true and prints a congratulations message.
                     * If all players have won, the code prints a message and breaks out of the
                     * loop.
                     */
                }
                if (allPlayersHaveWon) {
                    System.out.println("All players have won!\n");
                    break;
                }
            }

            // switch to the next player
            currentPlayer = (currentPlayer + 1) % 4;

            // print a message to indicate the end of the turn
            System.out.println("End of turn. Player " + (currentPlayer + 1) + ", it's your turn.\n");

            // ask the player to make a move
            // Scanner scnner = new Scanner(System.in);
            // System.out.print("Enter the number of squares to move (1-6): \n");
            System.out.println("You rolled a " + diceRoll + ".\n Your new position is: (" + players[currentPlayer][0]
                    + ", " + players[currentPlayer][1] + ")\n");

            // update the player's position
            int currentRow = players[currentPlayer][0];
            int currentCol = players[currentPlayer][1];
            int newRow1 = currentRow;
            int newCol1 = currentCol + diceRoll;
            if (newCol1 >= board[0].length) {
                newCol1 = newCol1 % board[0].length;
                newRow1++;
            }
            if (newRow1 >= board.length) {
                newRow1 = newRow1 % board.length;
            }
            // check if the new position is blocked
            if (board[newRow1][newCol1] == '-') {
                System.out.println("Sorry, you can't move to that square. Try again.\n");
            } else {
                players[currentPlayer][0] = newRow1;
                players[currentPlayer][1] = newCol1;
            }

            scanner.close();
        }
    }
}
