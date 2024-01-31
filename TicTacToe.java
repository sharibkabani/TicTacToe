/*=============================================================================
|  TicTacToe.java                                                             |
|-----------------------------------------------------------------------------|
|  Programmer:  Sharib Kabani                                                 |
|  Course:  ICS3U1                                                            |
|  Date: June 22, 2022                                                        |
|-----------------------------------------------------------------------------|
|                                                                             |
=============================================================================*/

import java.util.*;
import java.io.*;

public class TicTacToe

{

   public static void main(String[] args)
   
   {
   
      Scanner sc = new Scanner(System.in);
      Random random = new Random();
      Exception error = new Exception();
   
      // Variables and array
      String playerOne = "null";
      String playerTwo = "null";
      int x = 0;
      int y = 0;
      int rows = 3;
      int cols = 3;
      String[][] board = new String[rows][cols];
      int choice = 0;
   
      // Menu output
      System.out.println("=============WELCOME TO SHARIB'S TIC-TAC-TOE===========");
      System.out.println("1 - Player vs Player");
      System.out.println("2 - Quit\n");
   
      // While true loop asking for user choice
      while (true) {
      
         // Try while catching invalid input
         try {
         
            // User input
            System.out.print("Please choose one of the following. ");
            choice = sc.nextInt();
         
            // If statements printing appropriate response
            if (choice == 1) {
            
               sc.nextLine();
            
               // Asking for player names
               System.out.println("");
               System.out.print("Please enter player 1 name: ");
               playerOne = sc.nextLine();
            
               System.out.print("Please enter player 2 name: ");
               playerTwo = sc.nextLine();
               System.out.println("");
            
               // Method call
               game(playerOne, playerTwo, board, x, y);
               break;
            
            } else if (choice == 2) {
            
               System.out.println();
               System.out.println("See you soon!");
               System.exit(0);
            
            } else {
            
               throw error;
            
            }
         
            // Catching invalid input
         } catch (Exception invalidChoice) {
         
            sc.nextLine();
         
            System.out.println();
            System.out.println("INVALID INPUT!");
            System.out.println();
            System.out.println("1 - Player vs Player");
            System.out.println("2 - Quit\n");
            continue;
         }
      
      }
   
   }

   /*===================================================================================================*\
   |  void writeFilePlayerOne(String playerOne, int playerOneWins, int playerOneTies, int playerOneLoses)|
   |-----------------------------------------------------------------------------------------------------|
   |  returns void - This method does not return any values                                              |
   |-----------------------------------------------------------------------------------------------------|
   |  String playerOne - This parameter is player one's name                                             |
   |-----------------------------------------------------------------------------------------------------|
   |  int playerOneWins - This parameter is player one's wins total                                      |
   |-----------------------------------------------------------------------------------------------------|
   |  int playerOneTies - This parameter is player one' ties total                                       |
   |-----------------------------------------------------------------------------------------------------|
   |  int playerOneLoses - This parameter is player one's losses total                                   |
   |-----------------------------------------------------------------------------------------------------|
   |  This method creates a new file if playerOne is a new player with                                   |
   |  fresh stats.                                                                                       |
   =====================================================================================================*/
   public static void writeFilePlayerOne(String playerOne, int playerOneWins, int playerOneTies, int playerOneLoses) {
   
      // Variable
      String fileName = playerOne + ".txt";
   
      // File creation with location
      File playerOneStats = new File("/TicTacToe" + fileName);
   
      // Try while catching problem with file creation
      try {
      
         // Buffered writer
         FileWriter fw = new FileWriter(fileName);
         BufferedWriter bw = new BufferedWriter(fw);
      
         // Variables
         playerOneWins = 0;
         playerOneLoses = 0;
         playerOneTies = 0;
      
         // Writing fresh stats
         bw.write("0\n");
         bw.write("0\n");
         bw.write("0\n");
         bw.close();
      
         // Catching issue with file creation
      } catch (IOException e) {
      
         System.out.println(e + " Problem writing" + fileName);
      
      }
   
   }

   /*===================================================================================================*\
   |  void writeFilePlayerTwo(String playerTwo, int playerOneTwo, int playerOneTwo, int playerTwoLoses)  |
   |-----------------------------------------------------------------------------------------------------|
   |  returns void - This method does not return any values                                              |
   |-----------------------------------------------------------------------------------------------------|
   |  String playerTwo - This parameter is player two's name                                             |
   |-----------------------------------------------------------------------------------------------------|
   |  int playerTwoWins - This parameter is player two's wins total                                      |
   |-----------------------------------------------------------------------------------------------------|
   |  int playerTwoTies - This parameter is player two' ties total                                       |
   |-----------------------------------------------------------------------------------------------------|
   |  int playerTwoLoses - This parameter is player two's losses total                                   |
   |-----------------------------------------------------------------------------------------------------|
   |  This method creates a new file if playerTwo is a new player with                                   |
   |  fresh stats.                                                                                       |
   =====================================================================================================*/
   public static void writeFilePlayerTwo(String playerTwo, int playerTwoWins, int playerTwoTies, int playerTwoLoses) {
   
      // Variable
      String fileName = playerTwo + ".txt";
   
      // File creation
      File playerTwoStats = new File("/TicTacToe" + fileName);
   
      // Try while catching problem with file creation
      try {
      
         // Buffered writer
         FileWriter fw = new FileWriter(fileName);
      
         // Variables
         playerTwoWins = 0;
         playerTwoLoses = 0;
         playerTwoTies = 0;
      
         // Writing fresh stats
         fw.write("0\n");
         fw.write("0\n");
         fw.write("0\n");
         fw.close();
      
         // Catching issue with file creation
      } catch (IOException e) {
      
         System.out.println(e + " Problem writing" + fileName);
      
      }
   
   }
   /*=======================================================================
   |  String[][] resetBoard(String[][] board, int x, int y)                |
   |-----------------------------------------------------------------------|
   |  returns String[][] - The value of the empty board                    |
   |-----------------------------------------------------------------------|
   |  String[][] board - This is a parameter is a 2d array used as a board |
   |-----------------------------------------------------------------------|
   |  int x - This parameter is the x value of the 2d array                |
   |-----------------------------------------------------------------------|
   |  int y - This parameter is the y value of the 2d array                |
   |-----------------------------------------------------------------------|
   |  This method resets the board every time the game starts or finishes  |
   =======================================================================*/
   public static String[][] resetBoard(String[][] board, int x, int y) {
   
      // Nested for loop filling up array with empty spots
      for (int row = 0; row < board.length; row++) {
      
         for (int col = 0; col < board.length; col++) {
         
            board[row][col] = " - ";
         
         }
      }
   
      // Returning filled up array
      return board;
   
   }

   /*=======================================================================
   |  String[][] printBoard(String board[][], int x, int y, int playerTurn)|
   |-----------------------------------------------------------------------|
   |  returns String[][] - The value of the updated board                  |
   |-----------------------------------------------------------------------|
   |  String[][] board - This is a parameter is a 2d array used as a board |
   |-----------------------------------------------------------------------|
   |  int x - This parameter is the x value of the 2d array                |
   |-----------------------------------------------------------------------|
   |  int y - This parameter is the y value of the 2d array                |
   |-----------------------------------------------------------------------|
   |  int playerTurn - This parameter is used to determine if X or O placed|
   |-----------------------------------------------------------------------|
   |  This method prints the updated board after the user inputs x and y   |
   =======================================================================*/
   public static String[][] printBoard(String board[][], int x, int y, int playerTurn) {
   
      // Column headers
      System.out.println("   0  1  2");
      System.out.println("  ---------");
   
      // If statements checking which to place
      if (playerTurn == 0) {
      
         board[x][y] = " X ";
      
      } else if (playerTurn == 1) {
      
         board[x][y] = " O ";
      
      }
   
      // Nested for loop printing board with updated values
      for (int i = 0; i < board.length; i++) {
      
         System.out.print(i + "|");
      
         for (int j = 0; j < board.length; j++) {
         
            System.out.print(board[i][j]);
         
         }
      
         System.out.print("|");
         System.out.println("");
      
      }
   
      System.out.println("  ---------");
   
      // Returning updated board
      return board;
   
   }

   /*====================================================================
   |  String playerHasWon(String[][] board)                             |
   |--------------------------------------------------------------------|
   |  returns String - The value of the winning row/column/diagonal     |
   |--------------------------------------------------------------------|
   |  String[][] board - This parameter is a 2d array used as the board |
   |--------------------------------------------------------------------|
   |  This method takes the board and checks if there is a win          |
   ====================================================================*/
   public static String playerHasWon(String[][] board) {
   
      // For loop checking rows
      for (int i = 0; i < 3; i++) {
         if (board[i][0] == board[i][1] &&
            board[i][1] == board[i][2] &&
            board[i][0] != " - ") {
            return board[i][0];
         }
      }
   
      // For loop checking columns
      for (int j = 0; j < 3; j++) {
         if (board[0][j] == board[1][j] &&
            board[1][j] == board[2][j] &&
            board[0][j] != " - ") {
            return board[0][j];
         }
      }
   
      // If statement checking diagonal 1
      if (board[0][0] == board[1][1] &&
         board[1][1] == board[2][2] &&
         board[0][0] != " - ") {
         return board[0][0];
      }
   
      // If statement checking diagonal 1
      if (board[2][0] == board[1][1] &&
         board[1][1] == board[0][2] &&
         board[2][0] != " - ") {
         return board[2][0];
      }
   
      // Return
      return " ";
   }

   /*===================================================================================================*\
   |  void game(String playerOne, String playerTwo, String[][] board, int x, int y)                      |
   |-----------------------------------------------------------------------------------------------------|
   |  returns void - This method does not return any values                                              |
   |-----------------------------------------------------------------------------------------------------|
   |  String playerOne - This parameter is player one's name                                             |
   |-----------------------------------------------------------------------------------------------------|
   |  String playerTwo - This parameter is player two's name                                             |
   |-----------------------------------------------------------------------------------------------------|
   |  String[][] board - This parameter is a 2d array used as a board                                    |
   |-----------------------------------------------------------------------------------------------------|
   |  int x - This parameter is used as the x value in the game                                          |
   |-----------------------------------------------------------------------------------------------------|
   |  int y - This parameter is used as the y value in the game                                          |
   |-----------------------------------------------------------------------------------------------------|
   |  This method plays the game while checking for wins, keeping updated stats, and taking care of      |
   |  invalid inputs                                                                                     |
   =====================================================================================================*/
   public static void game(String playerOne, String playerTwo, String[][] board, int x, int y) {
   
      // Variables
      int playerOneWins = 0;
      int playerOneLoses = 0;
      int playerOneTies = 0;
   
      String read = "";
   
      // Try while catching nonexistent file
      try {
      
         // Buffered reader
         String fileName = playerOne + ".txt";
         BufferedReader in = new BufferedReader(new FileReader(fileName));
      
         // Reading first line and allocating value  (wins)
         read = in .readLine();
         int value = Integer.parseInt(read);
         playerOneWins = playerOneWins + value;
      
         // Reading second line and allocating value  (loses)
         read = in .readLine();
         value = Integer.parseInt(read);
         playerOneLoses = playerOneLoses + value;
      
         // Reading third line and allocating value (ties)
         read = in .readLine();
         value = Integer.parseInt(read);
         playerOneLoses = playerOneLoses + value;

         in.close();

         // Catching nonexistent file and creating one instead
      } catch (IOException playerOneStats) {
      
         writeFilePlayerOne(playerOne, playerOneWins, playerOneTies, playerOneLoses);
      
      }
   
      // Variables
      int playerTwoWins = 0;
      int playerTwoLoses = 0;
      int playerTwoTies = 0;
   
      // Try while catching nonexistent file
      try {
      
         // Buffered reader
         String fileName = playerTwo + ".txt";
         BufferedReader in = new BufferedReader(new FileReader(fileName));
      
         // Reading first line and allocating value (wins)
         read = in .readLine();
         int value = Integer.parseInt(read);
         playerTwoWins = playerTwoWins + value;
      
         // // Reading second line and allocating value (loses)
         read = in .readLine();
         value = Integer.parseInt(read);
         playerTwoLoses = playerTwoLoses + value;
      
         // Reading third line and allocating value (ties)
         read = in .readLine();
         value = Integer.parseInt(read);
         playerTwoTies = playerTwoTies + value;

         in.close();
      
         // Catching nonexistent file and creating one instead
      } catch (IOException playerTwoStats) {
      
         writeFilePlayerTwo(playerTwo, playerTwoWins, playerTwoTies, playerTwoLoses);
      
      }
   
      // While true loop
      while (true) {
      
         Scanner sc = new Scanner(System.in);
      
         // Variables
         int playerTurn = -1;
         int playAgain;
         int option = 0;
      
         // Resetting and printing board
         resetBoard(board, x, y);
         printBoard(board, x, y, playerTurn);
      
         // Try while catching invalid input
         try {
         
            // For loop looping as many times as possible
            for (int i = 1; i <= 9; i++) {
            
               // If statement checking who's turn it is
               if (i % 2 != 0) {
               
                  // Option user input 
                  System.out.println();
                  System.out.print("[1] Play turn [2] Give up ");
                  option = sc.nextInt();
                  System.out.println();
               
                  // If statement if user would like to play turn
                  if (option == 1) {
                  
                     // User input
                     System.out.println("It is " + playerOne + "'s turn.");
                  
                     System.out.print("Choose row (0, 1 or 2) ");
                     x = sc.nextInt();
                  
                     System.out.print("Choose column (0, 1 or 2) ");
                     y = sc.nextInt();
                  
                     // If statement checking if valid input
                     if (x > 3 || x < -2 || y > 3 || y < -2) {
                     
                        // Do while loop asking for valid input
                        do {
                        
                           System.out.println("\nPlease choose from the possible rows and columns.\n");
                        
                           System.out.print("Choose row (0, 1 or 2) ");
                           x = sc.nextInt();
                        
                           System.out.print("Choose column (0, 1 or 2) ");
                           y = sc.nextInt();
                        
                        } while (x > 3 || x < -2 || y > 3 || y < -2);
                     
                     }
                  
                     // If statement checking if spot taken
                     if (board[x][y] == " X " || board[x][y] == " O ") {
                     
                        // Do loop asking for valid input
                        do {
                        
                           System.out.println("\nSpot taken!\n");
                        
                           System.out.print("Choose row (0, 1 or 2) ");
                           x = sc.nextInt();
                        
                           System.out.print("Choose column (0, 1 or 2) ");
                           y = sc.nextInt();
                        
                        } while (board[x][y] == " X " || board[x][y] == " O ");
                     
                     }
                  
                     System.out.println();
                  
                     playerTurn = 0;
                  
                     // Printing updated board
                     printBoard(board, x, y, playerTurn);
                  
                     // If statement checking if player one has won
                     if (playerHasWon(board) == " X ") {
                     
                        System.out.println(playerOne + " has won!");
                     
                        playerOneWins += 1;
                     
                        // Updating .txt stats
                        try {
                        
                           // Variable
                           String fileName = playerOne + ".txt";
                        
                           // Buffered writer
                           FileWriter fw = new FileWriter(fileName);
                           BufferedWriter bw = new BufferedWriter(fw);
                        
                           // Writing updated stats
                           bw.write(playerOneWins + "\n");
                           bw.write(playerOneLoses + "\n");
                           bw.write(playerOneTies + "");
                           bw.close();
                        
                           // Letting user know if unable to update stats 
                        } catch (IOException PlayerOneWins) {
                        
                           System.out.println("Unable to update " + playerOne + "'s stats.");
                        
                        }
                     
                        playerTwoLoses += 1;
                     
                        // Updating .txt stats
                        try {
                        
                           // Variable
                           String fileName = playerTwo + ".txt";
                        
                           // Buffered writer
                           FileWriter fw = new FileWriter(fileName);
                           BufferedWriter bw = new BufferedWriter(fw);
                        
                           // Writing updated stats
                           bw.write(playerTwoWins + "\n");
                           bw.write(playerTwoLoses + "\n");
                           bw.write(playerTwoTies + "");
                           bw.close();
                        
                           // Letting user know if unable to update stats 
                        } catch (IOException PlayerTwoLoses) {
                        
                           System.out.println("Unable to update " + playerTwo + "'s stats.");
                        
                        }
                     
                        break;
                     
                        // If statement checking if player two won
                     } else if (playerHasWon(board) == " O ") {
                     
                        System.out.println(playerTwo + " has won!");
                     
                        playerTwoWins += 1;
                     
                        // Updating .txt stats
                        try {
                        
                           // Variables
                           String fileName = playerTwo + ".txt";
                        
                           // Buffered writer
                           FileWriter fw = new FileWriter(fileName);
                           BufferedWriter bw = new BufferedWriter(fw);
                        
                           // Writing updated stats
                           bw.write(playerTwoWins + "\n");
                           bw.write(playerTwoLoses + "\n");
                           bw.write(playerTwoTies + "");
                           bw.close();
                        
                           // Letting user know if unable to update stats 
                        } catch (IOException PlayerTwoWins) {
                        
                           System.out.println("Unable to update " + playerTwo + "'s stats.");
                        
                        }
                     
                        playerOneLoses += 1;
                     
                        // Updating .txt stats
                        try {
                        
                           // Variables
                           String fileName = playerOne + ".txt";
                        
                           // Buffered writer
                           FileWriter fw = new FileWriter(fileName);
                           BufferedWriter bw = new BufferedWriter(fw);
                        
                           // Writing updated stats
                           bw.write(playerOneWins + "\n");
                           bw.write(playerOneLoses + "\n");
                           bw.write(playerOneTies + "");
                           bw.close();
                        
                           // Letting user know if unable to update stats 
                        } catch (IOException PlayerOneLoses) {
                        
                           System.out.println("Unable to update " + playerOne + "'s stats.");
                        
                        }
                        break;
                     
                        // If statement checking if tied
                     } else if (i == 9) {
                     
                        System.out.println("Tie!\n");
                        playerOneTies += 1;
                     
                        // Updating .txt files
                        try {
                        
                           // Variable
                           String fileName = playerOne + ".txt";
                        
                           // Buffered writer
                           FileWriter fw = new FileWriter(fileName);
                           BufferedWriter bw = new BufferedWriter(fw);
                        
                           // Buffered writer
                           bw.write(playerOneWins + "\n");
                           bw.write(playerOneLoses + "\n");
                           bw.write(playerOneTies + "");
                           bw.close();
                        
                           // Letting user know if unable to update stats 
                        } catch (IOException PlayerOneTies) {
                        
                           System.out.println("Unable to update " + playerOne + "'s stats.");
                        
                        }
                     
                        playerTwoTies += 1;
                     
                        // Updating .txt stats
                        try {
                        
                           // Variable
                           String fileName = playerTwo + ".txt";
                        
                           // Buffered writer
                           FileWriter fw = new FileWriter(fileName);
                           BufferedWriter bw = new BufferedWriter(fw);
                        
                           // Writing updated stats
                           bw.write(playerTwoWins + "\n");
                           bw.write(playerTwoLoses + "\n");
                           bw.write(playerTwoTies + "");
                           bw.close();
                        
                           // Letting user know if unable to update stats 
                        } catch (IOException PlayerTwoTies) {
                        
                           System.out.println("Unable to update " + playerTwo + "'s stats.");
                        
                        }
                     
                        break;
                     
                     }
                  
                     // If statement if given up
                  } else if (option == 2) {
                  
                     System.out.println(playerTwo + " has won!");
                  
                     playerTwoWins += 1;
                  
                     // Updating .txt stats
                     try {
                     
                        // Variable
                        String fileName = playerTwo + ".txt";
                     
                        // Buffered writer
                        FileWriter fw = new FileWriter(fileName);
                        BufferedWriter bw = new BufferedWriter(fw);
                     
                        // Writing updated stats
                        bw.write(playerTwoWins + "\n");
                        bw.write(playerTwoLoses + "\n");
                        bw.write(playerTwoTies + "");
                        bw.close();
                     
                        // Letting user know if unable to update stats 
                     } catch (IOException PlayerTwoWins) {
                     
                        System.out.println("Unable to update " + playerTwo + "'s stats.");
                     
                     }
                  
                     playerOneLoses += 1;
                  
                     // Updating .txt stats
                     try {
                     
                        // Variable
                        String fileName = playerOne + ".txt";
                     
                        // Buffered writer
                        FileWriter fw = new FileWriter(fileName);
                        BufferedWriter bw = new BufferedWriter(fw);
                     
                        // Writing updated stats
                        bw.write(playerOneWins + "\n");
                        bw.write(playerOneLoses + "\n");
                        bw.write(playerOneTies + "");
                        bw.close();
                     
                        // Letting user know if unable to update stats 
                     } catch (IOException PlayerOneLoses) {
                     
                        System.out.println("Unable to update " + playerOne + "'s stats.");
                     
                     }
                     break;
                  }
               
                  // If statement checking who's turn it is
               } else if (i % 2 == 0) {
               
                  // Option user input
                  System.out.println();
                  System.out.print("[1] Play turn [2] Give up ");
                  option = sc.nextInt();
                  System.out.println();
               
                  // If statement if user would like to play turn
                  if (option == 1) {
                  
                     // User input
                     System.out.println("It is " + playerTwo + "'s turn.");
                  
                     System.out.print("Choose row (0, 1 or 2) ");
                     x = sc.nextInt();
                  
                     System.out.print("Choose column (0, 1 or 2) ");
                     y = sc.nextInt();
                  
                     // If statement checking for invalid input
                     if (x > 3 || x < -2 || y > 3 || y < -2) {
                     
                        // Do while loop asking user for valid input
                        do {
                        
                           System.out.println("\nPlease choose from the possible rows and columns.\n");
                        
                           System.out.print("Choose row (0, 1 or 2) ");
                           x = sc.nextInt();
                        
                           System.out.print("Choose column (0, 1 or 2) ");
                           y = sc.nextInt();
                        
                        } while (x > 3 || x < -2 || y > 3 || y < -2);
                     
                     }
                  
                     // If statement checking if spot taken
                     if (board[x][y] == " X " || board[x][y] == " O ") {
                     
                        // Do while loop asking for valid input
                        do {
                        
                           System.out.println("\nSpot taken!");
                        
                           System.out.print("Choose row (0, 1 or 2) ");
                           x = sc.nextInt();
                        
                           System.out.print("Choose column (0, 1 or 2) ");
                           y = sc.nextInt();
                        
                        } while (board[x][y] == " X " || board[x][y] == " O ");
                     
                     }
                  
                     System.out.println();
                  
                     playerTurn = 1;
                  
                     // Printing updated board
                     printBoard(board, x, y, playerTurn);
                  
                     // If statement checking if player one won
                     if (playerHasWon(board) == " X ") {
                     
                        System.out.println(playerOne + " has won!");
                        playerOneWins += 1;
                     
                        // Updating .txt stats
                        try {
                        
                           // Variable
                           String fileName = playerOne + ".txt";
                        
                           // Buffered writer
                           FileWriter fw = new FileWriter(fileName);
                           BufferedWriter bw = new BufferedWriter(fw);
                        
                           // Writing updated stats
                           bw.write(playerOneWins + "\n");
                           bw.write(playerOneLoses + "\n");
                           bw.write(playerOneTies + "");
                           bw.close();
                        
                           // Letting user know if unable to update stats
                        } catch (IOException PlayerOneWins) {
                        
                           System.out.println("Unable to update " + playerOne + "'s stats.");
                        
                        }
                     
                        playerTwoLoses += 1;
                     
                        // Updating .txt stats
                        try {
                        
                           // Variables
                           String fileName = playerTwo + ".txt";
                        
                           // Buffered writer
                           FileWriter fw = new FileWriter(fileName);
                           BufferedWriter bw = new BufferedWriter(fw);
                        
                           // Writing updated stats
                           bw.write(playerTwoWins + "\n");
                           bw.write(playerTwoLoses + "\n");
                           bw.write(playerTwoTies + "");
                           bw.close();
                        
                           // Letting user know if unable to update stats
                        } catch (IOException PlayerTwoLoses) {
                        
                           System.out.println("Unable to update " + playerTwo + "'s stats.");
                        
                        }
                        break;
                     
                        // If statement checking if player two won
                     } else if (playerHasWon(board) == " O ") {
                     
                        System.out.println(playerTwo + " has won!");
                     
                        playerTwoWins += 1;
                     
                        // Updating .txt stats
                        try {
                        
                           // Variable
                           String fileName = playerTwo + ".txt";
                        
                           // Buffered writer
                           FileWriter fw = new FileWriter(fileName);
                           BufferedWriter bw = new BufferedWriter(fw);
                        
                           // Writing updated stats
                           bw.write(playerTwoWins + "\n");
                           bw.write(playerTwoLoses + "\n");
                           bw.write(playerTwoTies + "");
                           bw.close();
                        
                           // Letting user know if unable to update stats
                        } catch (IOException PlayerTwoWins) {
                        
                           System.out.println("Unable to update " + playerTwo + "'s stats.");
                        
                        }
                     
                        playerOneLoses += 1;
                     
                        // Updating .txt stats
                        try {
                        
                           // Variable
                           String fileName = playerOne + ".txt";
                        
                           // Buffered writer
                           FileWriter fw = new FileWriter(fileName);
                           BufferedWriter bw = new BufferedWriter(fw);
                        
                           // Writing updating stats
                           bw.write(playerOneWins + "\n");
                           bw.write(playerOneLoses + "\n");
                           bw.write(playerOneTies + "");
                           bw.close();
                        
                           // Letting user know if unable to update stats
                        } catch (IOException PlayerOneLoses) {
                        
                           System.out.println("Unable to update " + playerOne + "'s stats.");
                        
                        }
                        break;
                     
                        // If statement if tied
                     } else if (i == 9) {
                     
                        System.out.println("Tie!\n");
                        playerOneTies += 1;
                     
                        // Updating .txt stats
                        try {
                        
                           // Variable
                           String fileName = playerOne + ".txt";
                        
                           // Buffered writer
                           FileWriter fw = new FileWriter(fileName);
                           BufferedWriter bw = new BufferedWriter(fw);
                        
                           // Writing updated stats
                           bw.write(playerOneWins + "\n");
                           bw.write(playerOneLoses + "\n");
                           bw.write(playerOneTies + "");
                           bw.close();
                        
                           // Letting user know if unable to update stats
                        } catch (IOException PlayerOneTies) {
                        
                           System.out.println("Unable to update " + playerOne + "'s stats.");
                        
                        }
                     
                        playerTwoTies += 1;
                     
                        // Updating .txt stats
                        try {
                        
                           // Variables
                           String fileName = playerTwo + ".txt";
                        
                           // Buffered writer
                           FileWriter fw = new FileWriter(fileName);
                           BufferedWriter bw = new BufferedWriter(fw);
                        
                           // Writing updated stats
                           bw.write(playerTwoWins + "\n");
                           bw.write(playerTwoLoses + "\n");
                           bw.write(playerTwoTies + "");
                           bw.close();
                        
                           // Letting user know if unable to update stats
                        } catch (IOException PlayerTwoTies) {
                        
                           System.out.println("Unable to update " + playerTwo + "'s stats.");
                        
                        }
                        break;
                     
                     }
                  
                     // If statement if given up
                  } else if (option == 2) {
                  
                     System.out.println(playerOne + " has won!");
                  
                     playerOneWins += 1;
                  
                     // Updating .txt stats
                     try {
                     
                        // Variable
                        String fileName = playerOne + ".txt";
                     
                        // Buffered writer
                        FileWriter fw = new FileWriter(fileName);
                        BufferedWriter bw = new BufferedWriter(fw);
                     
                        // Writing updated stats
                        bw.write(playerOneWins + "\n");
                        bw.write(playerOneLoses + "\n");
                        bw.write(playerOneTies + "");
                        bw.close();
                     
                        // Letting user know if unable to update stats
                     } catch (IOException PlayerOneWins) {
                     
                        System.out.println("Unable to update " + playerOne + "'s stats.");
                     
                     }
                  
                     playerTwoLoses += 1;
                  
                     // Updating .txt stats
                     try {
                     
                        // Variable
                        String fileName = playerTwo + ".txt";
                     
                        // Buffered writer
                        FileWriter fw = new FileWriter(fileName);
                        BufferedWriter bw = new BufferedWriter(fw);
                     
                        // Writing updated stats
                        bw.write(playerTwoWins + "\n");
                        bw.write(playerTwoLoses + "\n");
                        bw.write(playerTwoTies + "");
                        bw.close();
                     
                        // Letting user know if unable to update stats
                     } catch (IOException PlayerTwoLoses) {
                     
                        System.out.println("Unable to update " + playerTwo + "'s stats.");
                     
                     }
                     break;
                  
                  }
               
               }
            
            }
         
            // Final output
            System.out.println(playerOne + " wins: " + playerOneWins + " Loses: " + playerOneLoses + " Ties: " + playerOneTies);
            System.out.println(playerTwo + " wins: " + playerTwoWins + " Loses: " + playerTwoLoses + " Ties: " + playerTwoTies);
            System.out.println();
         
            // User input to play again or quit
            System.out.println("Would you like to play again?");
            System.out.println();
            System.out.print("Enter 1 to play again, 2 to quit ");
            playAgain = sc.nextInt();
            System.out.println();
         
            // If statement restarting game
            if (playAgain == 1) {
            
               continue;
            
               // Else if statement quitting program
            } else if (playAgain == 2) {
            
               System.out.println("See you soon!");
               System.exit(0);
            
               // Else if statement if invalid choice
            } else if (playAgain != 1 || playAgain != 2) {
            
               // Do while loop asking user for valid input
               do {
               
                  sc.nextLine();
               
                  System.out.println("INVALID INPUT");
                  System.out.println();
               
                  System.out.print("Enter 1 to play again, 2 to quit ");
                  playAgain = sc.nextInt();
                  System.out.println();
               
                  if (playAgain == 1) {
                  
                     continue;
                  
                  } else if (playAgain == 2) {
                  
                     System.out.println("See you soon!");
                     System.exit(0);
                  
                  }
               
               } while (playAgain != 1 || playAgain != 2);
            
            }
         
            // Catching any invalid input and restarting game
         } catch (Exception invalidSpot) {
         
            sc.nextLine();
         
            System.out.println();
            System.out.println("Invalid input! Restarting game.");
            playerTurn = 1;
            System.out.println();
            continue;
         
         }
      
      }
   
   }

}