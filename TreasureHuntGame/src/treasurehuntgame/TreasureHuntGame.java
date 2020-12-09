package treasurehuntgame;

import java.util.Random;
import java.util.Scanner;

public class TreasureHuntGame {

    //Using a two-dimentional array (the size is your choice)
    // 1st number is the row, 2nd number is the collums
    private static String[][] GameBoard;
    private static int BoardSize;
    private static int NumberOfGuesses;    
    private static int Score;

    public static void main(String[] args) {
        SetUpGame();

        GameBoard = new String[BoardSize][BoardSize];
        Score = 0;
        SetUpBoard();
        AddTreasure();

        for (int i = 0; i < NumberOfGuesses; i++) {
            CoordinateUserGuess();
            System.out.println("You have got: " +((NumberOfGuesses-1)-i) +" guesses left");
        }
        PrintBoard();
        System.out.println("Your final score is: " + Score);
    }

    public static void SetUpBoard() {
        for (int i = 0; i < BoardSize; i++) {
            for (int j = 0; j < BoardSize; j++) {
                GameBoard[i][j] = "[  ]";
            }
        }
    }

    public static void PrintBoard() {
        for (int i = 0; i < BoardSize; i++) {
            for (int j = 0; j < BoardSize; j++) {
                System.out.print(GameBoard[i][j]);
            }
            System.out.println("");
        }
    }

    //In random positions in the array store a random amount of coins
    public static void AddTreasure() {
        Random random = new Random();

        // Random amount of treasure, between 15-50 items
        // (max-min)+1+min
        int min = (BoardSize * BoardSize) / 4;
        int max = (BoardSize * BoardSize) / 2;

        int NumberOfTreasureItems = random.nextInt((max - min) + 1) + min;

        System.out.println("There are: " + NumberOfTreasureItems + " bits of treasure to find");

        for (int i = 0; i < NumberOfTreasureItems; i++) {
            //Random number is 2 digits long
            GameBoard[random.nextInt(BoardSize)][random.nextInt(BoardSize)] = "[" + (random.nextInt((99 - 10) + 1) + 10) + "]";
        }
    }

    //Get the user to enter coordinates where they think the treasure is
    public static void CoordinateUserGuess() {
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.println("Please type in the X coordinate which is the row (any number between 1 and " + (BoardSize) + ")");
                int x = input.nextInt() - 1;

                System.out.println("Please type in the Y coordinate which is the row (any number between 1 and " + (BoardSize) + ")");
                int y = input.nextInt() - 1;

                // only accepts number between 0 and 9 so it is always in the board
                if (x >= 0 && x <= BoardSize - 1 && y >= 0 && y <= BoardSize - 1) {
                    CheckForTreasure(x ,y);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e);
                System.out.println("Please type in only numbers between 0 and " + (BoardSize - 1) + ")");
            }
            System.out.println("That Input was not correct. Please check carefully.");
        }

    }

    //If there is treasure at this point this gets added to the users total
    public static void CheckForTreasure(int x, int y) {
        if (!GameBoard[x][y].substring(1, 3).equals("  ")) {
            Score = Score + Integer.parseInt(GameBoard[x][y].substring(1, 3)); // GameBoard[x][y].substring(1,3) this includes 1 but doesnt include 3
            System.out.println("Woo you hit treasure ^o^. Your current score is: " + Score);
        } else {
            System.out.println("Better luck next time no treasure. Your current score is: " + Score);
        }
    }

    // asks for board size
    public static void SetUpGame() {
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.println("What size would you like the board to be?");
                BoardSize = input.nextInt();

                System.out.println("How many guesses would you like?");
                NumberOfGuesses = input.nextInt();
                
                if(BoardSize>=2 && NumberOfGuesses>=2) {
                    break;
                }
                
            } catch (Exception e) {
                System.out.println("Incorrect Inputs. The board size should be a positive number more than 2, and the guesses shoud be a positive number more than 2    ");
            }
            System.out.println("That Input was not correct. Please check carefully.");
        }
    }

}
