// -----------------------------------------------------
// Mini project  1  
// Written by: (Saja Zmaili - 2020904019 ) 
// -----------------------------------------------------


/*

--- WELCOME TO GameOfLife ----

General explanatio: 

GameOfLife is a Java program that implements a simple board game. 
The program creates a two-dimensional board on which the player begins at row 4 and column 1.
The players goal is to get to the "end" square (cell), represented by the letter "E.". The players move on the board by pressing 1 to move left, 2 to move right, 3 to move up, or 4 to move down.
A player could land on a special cell that handles a question, if a player answers the question correctly he will be awarded with money, but, BE CAREFUL ! If you answer incorrectly, you are sent back to the starting square :/  
Finally, the game either ends when you reach the end "E" square(cell), or when any player earns more than $1000. 

*/

// Import the Scanner class to read input from the user
import java.util.Scanner; 

public class Main {
    public static void main(String[] args) {
        // Define a 2D char array to represent the game board
        char[][] board = {
                {'*', '*', '*', '*', '*', '*'},
                {'*', '*', '*', '*', '*', '*'},
                {'*', '*', '*', '*', '*', '*'},
                {'*', '*', '*', '*', '*', '*'},
                {'*', '*', '*', '*', '*', '*'},
                {'*', '*', '*', '*', '*', 'E'}  // E represents the "end" square
        };

/*

Moving
Start at location [4][1]. 
You can move left, right, up or down.

*/

int row = 4;           // Initialize the row position of the player to 4
int col = 1;           // Initialize the column position of the player to 1
int money = 0;         // Initialize the amount of money the player has to 0
boolean gameOver = false;  // Initialize the game over status to false
int currentPlayer = 1;  // Initialize the current player to 1
Scanner input = new Scanner(System.in);  // Create a new Scanner object to read input from the user

while (!gameOver) {  // Loop until the game is over
    // Print the board if it is the current player's turn
    if (currentPlayer == 1) {
        // Loop through each row and column of the board and print out the corresponding character
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();  // Print a newline after each row is printed
        }
    }

System.out.println("Player " + currentPlayer + "'s turn");  // Print the current player's turn
System.out.println("Current position: row=" + row + ", col=" + col);  // Print the player's current position
System.out.println("Current money: " + money);  // Print the player's current amount of money
char currSquare = board[row][col];  // Get the character representing the square the player is currently on

//2 situations of when a game ends

if (currSquare == 'E') {  // If the player is on the end square, end the game


    System.out.println("Congratulations, Player " + currentPlayer + "! You reached the end square and finished the game!");
    currentPlayer = 1;  // Reset the current player to player 1
    gameOver = true;  // Set the game over status to true
} else if (currSquare == 'Q') {  // If the player is on a question square, ask a question

/*
Activities
As shown in the game of life, there might be something that "happens"
on a specific square. For example :
- player must answer a question correctly to receive a reward
*/
    System.out.println("You landed on a special square. Answer the question correctly to earn 1000 dollars.");
    System.out.println("What year was Corona detected?");
    int ans = input.nextInt();  // Read the user's answer to the question
    if (ans == 2019) {  // If the user answered the question correctly, award them 1000 dollars
        money = money + 1000;
        System.out.println("Congratulations, Player " + currentPlayer + "! You earned 1000 dollars.");
    } else {  // If the user answered the question incorrectly, send them back to the first square
        System.out.println("Incorrect answer, Player " + currentPlayer + ". You must go back to the first square.");
        row = 4;
        col = 1;
    }
}

/*
Moving

*/

else {
    System.out.println("Press 1 to move left, 2 to move right, 3 to move up, or 4 to move down.");  // Prompt the player to make a move
    int move = input.nextInt();  // Read the player's move
    switch (move) {  // Check which move the player made
        case 1:
            if (col > 0) {  // If the move is valid (i.e., the player won't move off the game board), update the player's position
                col--;
            }
            break;
        case 2:
            if (col < 5) {
                col++;
            }
            break;
        case 3:
            if (row > 0) {
                row--;
            }
            break;
        case 4:
            if (row < 5) {
                row++;
            }
            break;
        default:  // If the player made an invalid move, tell them to try again
            System.out.println("Invalid move. Please try again.");
    }

/*

Rules
Some locations might be "blocked" or "forbidden". 
This information can be permanently recorded in the program -
 if( newRow == 5 && newCol == 3)
 { output("This place is forbidden"; }
*/


// Check if the current cell is at the forbidden position (row 5, column 3)
if (row == 5 && col == 3) {
    // Print a message to the console indicating that the position is forbidden
    System.out.println("This place is forbidden");
    // Move the cell to a new position at row 4, column 1
    row = 4;
    col = 1;
}

}

/*

Winning
Most board games require the player to reach the "end" square to finish.
Other possibilities are:
- earn a lot of money, like more than $1000

*/


if (!gameOver) {  // If the game is not over yet...
    if (money > 1000) {  // Check if the player has earned more than $1000
        System.out.println("Congratulations! You earned over 1000 dollars and finished the game!");
        gameOver = true;  // If so, end the game
    } else {  // Otherwise, switch to the next player
        currentPlayer++;
        if (currentPlayer > 4) {  // If all four players have taken their turns, start over with player 1
            currentPlayer = 1;

                    }
                }
            }
        }
    }
}

/*
------------------ END OF THE GAME -------------------------
*/
