package edu.pacificu.cs.minesweeperjava;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Creates a MineSweeper_View_TextUI where all operations work on the view
 * for the text base minesweeper game
 *
 * @author Forbes Miyasato
 */
public class MineSweeper_View_TextUI extends IMineSweeper_View
{
  private String[][] mpBoard;
  private MineSweeper_Presenter mpcPresenter;
  private Scanner scanner; //scanner to get user input

  /**
   * Initializes The MineSweeper_View_TextUI for this minesweeper game
   */
  public MineSweeper_View_TextUI ()
  {
    MineSweeper_Model cModel = new MineSweeper_Model ();
    mpcPresenter = new MineSweeper_Presenter (this, cModel);
  }

  /**
   * Prompts the user to select a cell, then passes the information to the
   * presenter. Also determines whether the board should be printed after
   * selecting the cell
   *
   * @return true if the board should be printed after selecting the cell,
   *         false if not
   */
  public boolean UserSelectCell ()
  {
    int column = -1, row = -1; //To ask for valid input again
    // if input wasn't integers
    System.out.print ("Enter X and Y Coordinate: ");
    try
    {
      column = scanner.nextInt ();
      row = scanner.nextInt ();
    } catch (InputMismatchException e)
    {
      System.out.println("Position must be integers!");
      scanner.nextLine ();
    }
    return (mpcPresenter.UserSelectedCell (row, column));
  }

  /**
   * Main Loop for the game. Asks user to select difficulty and cells,
   * prints the board, and displays messages.
   */
  public void eventLoop ()
  {
    scanner = new Scanner (System.in);
    displayHeader ();
    enterDifficulty ();
    boolean printBoardNextLoop = true;
    while (!mbLost && !mbWon)
    {
      if (printBoardNextLoop)
      {
        printBoard ();
      }

      printBoardNextLoop = UserSelectCell ();
    }

    printMessage ();
    scanner.close ();
  }

  /**
   * Sets the game state to user lost
   */
  public void userLost ()
  {
    mbLost = true;
  }

  /**
   * Sets the game state to user won
   */
  public void userWon ()
  {
    mbWon = true;
  }

  /**
   * Prints the game header
   */
  public void displayHeader ()
  {
    System.out.println ("***********");
    System.out.println ("Minesweeper");
    System.out.println ("***********");
  }

  /**
   * Prompts the user to enter difficulty, then passes the information to the
   * presenter.
   */
  public void enterDifficulty ()
  {
    final int HIGHEST_DIFFICULTY = 2;
    int difficulty = 1; //To not print invalid message first time
    do
    {
      if (difficulty < 0 || difficulty > HIGHEST_DIFFICULTY)
      {
        System.out.println ("Invalid Difficulty!!!");
      }
      System.out.println ("Enter difficulty level");
      System.out.print ("(0 = EASY, 1 = MEDIUM, 2 = HARD): ");
      try
      {
        difficulty = scanner.nextInt ();
      }
      catch (InputMismatchException e)
      {
        System.out.println ("Difficulty must be an integer!");
        difficulty = -1; //Stay in loop
        scanner.nextLine ();
      }
    } while (difficulty < 0 || difficulty > HIGHEST_DIFFICULTY);
    mpcPresenter.setDifficulty (difficulty);
  }

  /**
   * Sets the TextUI's game board
   *
   * @param board The game board to set to
   */
  public void setBoard (String[][] board)
  {
    mpBoard = board;
  }

  /**
   * Prints the TextUI's board
   */
  public void printBoard ()
  {
    System.out.println ();
    System.out.println ("  0   1   2   3   4   5   6   7   8 ");
    for (int i = 0; i < mpBoard.length; i++)
    {
      for (int j = 0; j < mpBoard[0].length; j++)
      {
        System.out.print ("  " + mpBoard[i][j]);
        if (j != mpBoard.length - 1)
        {
          System.out.print ("|");
        }
      }
      System.out.println ("  " + i);
      if (i != mpBoard.length - 1)
      {
        System.out.println ("-----------------------------------");
      }
    }
    System.out.println ();
  }

  /**
   * Prints message to tell the user they selected an invalid position
   */
  public void printInvalidPosition ()
  {
    System.out.println ("Invalid Position! Pick again!");
  }

  /**
   * Prints the final game message based on whether the user won or lost
   */
  public void printMessage ()
  {
    if (mbWon)
    {
      System.out.println ("Congratulations. You win.");
    }

    else if (mbLost)
    {
      System.out.println ("Boooom!!! You lose.");
    }
  }
}
