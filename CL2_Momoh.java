/* Mashiyach Momoh
 [CS1101] Comprehensive Lab 2
 This work is to be done individually. It is not permitted to
 share, reproduce, or alter any part of this assignment for any
 purpose. Students are not permitted from sharing code, uploading
 this assignment online in any form, or viewing/receiving/
 modifying code written from anyone else. This assignment is part.
 of an academic course at The University of Texas at El Paso and
 a grade will be assigned for the work produced individually by
 the student.
*/

import java.util.Scanner;
import java.util.Random;

public class CL2_Momoh {
    public static void main(String[] args) {
        int[] key = setupBoard();
        int[] playingBoard = new int[key.length];

        int attempts = key.length / 2 + 2;
        int remainingAttempts = attempts;

        displayMainMenu(attempts);
        Scanner intScanner = new Scanner(System.in);
        int choice = intScanner.nextInt();

        switch (choice) {
            case 1:
                for (int i = 0; i < attempts; i++) {
                    int completion = runGame(playingBoard, key);
                    if (completion == 3) {
                        System.out.println("Congratulations! You have found all pairs!");
                        break;
                    } else {
                        remainingAttempts--;
                        if (remainingAttempts == 0) {
                            System.out.println("Sorry, you lose! Better luck next time.");
                        } else {
                            System.out.println("You have " + remainingAttempts + " attempts left.");
                        }
                    }
                }
                break;
            case 2:
                while (true) {
                    int completion = runGame(playingBoard, key);
                    if (completion == 3) {
                        System.out.println("Congratulations! You have found all pairs!");
                        break;
                    }
                }
            case 3:
                System.out.println("You have exited the game. Bye bye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please select a valid option.");
                break;
        }
    }

    public static int runGame(int[] playingBoard, int[] board) {
        Scanner intScanner = new Scanner(System.in);
        displayBoard(playingBoard);

        int cell1;
        do {
            System.out.println("\nPlease enter the first cell number:");
            cell1 = intScanner.nextInt();
        } while (!isWithinBounds(playingBoard.length, cell1) || isCellSelected(playingBoard, cell1));
        System.out.println("You've discovered a " + board[cell1]);
        clearCell(playingBoard, cell1, board[cell1]);
        displayBoard(playingBoard);

        int cell2;
        do {
            System.out.println("Where is the matching pair?");
            cell2 = intScanner.nextInt();
        } while (!isWithinBounds(playingBoard.length, cell2) || isCellSelected(playingBoard, cell2));
        System.out.println("You've discovered a " + board[cell2]);
        clearCell(playingBoard, cell2, board[cell2]);
        displayBoard(playingBoard);

        if (board[cell1] == board[cell2]) { // if pair is found
            clearCell(playingBoard, cell1, board[cell1]);
            clearCell(playingBoard, cell2, board[cell2]);
            System.out.println();

            if (isBoardCleared(playingBoard)) {
                return 3; // all pairs have been found
            } else {
                System.out.println("You found a pair!");
                return 1; // a pair has been found
            }

        } else {
            if (board[cell1] != board[cell2]) { // if pair is not found
                clearCell(playingBoard, cell1, 0);
                clearCell(playingBoard, cell2, 0);
                System.out.println("Sorry, wrong guess!");
            }
            return 0; // no pairs have been found
        }
    }

    public static boolean isWithinBounds(int boardLength, int cell) {
        return cell >= 0 && cell < boardLength; // verifying if picked element is within bounds

    }

    public static int[] setupBoard() {
        Scanner intScanner = new Scanner(System.in); // scanner for int
        int boardLength;

        do {
            System.out.println("Please enter the size of the board (must be even): ");
            boardLength = intScanner.nextInt();
        } while (boardLength % 2 != 0); // do-while loop to confirm user input is even and != 0

        int[] board = new int[boardLength];
        placePairs(board);

        return board;
    }

    public static void displayMainMenu(int attempts) {
        System.out.println("----- MEMORY MATCH GAME -----");
        System.out.println("How many attempts would you like to play with? : ");
        System.out.println("1. " + attempts + " attempts");
        System.out.println("2. Unlimited number of attempts");
        System.out.println("3. Exit game");
    }

    public static void displayBoard(int[] board) {
        int boardSize = board.length;
        System.out.print("  ");
        for (int i = 0; i < boardSize; i++) {
            System.out.print(i + "   ");
        }
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < boardSize; i++) {
            System.out.print("___ ");
        }
        System.out.println();
        System.out.print("| ");
        for (int i = 0; i < boardSize; i++) {
            if (board[i] == 0) {
                System.out.print("? | ");
            } else {
                System.out.print(board[i] + " | ");
            }
        }
        System.out.println();
    }

    public static boolean isCellSelected(int[] board, int cell) {
        return board[cell] != 0;
    }

    public static void clearCell(int[] board, int cell, int value) {
        board[cell] = value;
    }

    public static boolean isBoardCleared(int[] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) {
                return false;
            }
        }
        return true;

    }

    public static void placePairs(int[] board) {

        Random rand = new Random();

        // place the values in the array in order

        for (int i = 0; i < board.length; i++)
            board[i] = i / 2 + 1;
        // perfect array shuffle
        // swap the ith value with a random value from index i to end of array
        for (int i = 0; i < board.length - 1; i++) {
            int j = rand.nextInt(board.length - i);
            int temp = board[i];
            board[i] = board[j];
            board[j] = temp;
        }
    }
}
