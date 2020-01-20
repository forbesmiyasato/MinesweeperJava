package edu.pacificu.cs.minesweeperjava;

/**
 * Creates a MineSweeper_Model class where all operations work on the business
 * logic for minesweeper
 *
 * @author Forbes Miyasato
 */

public class MineSweeper_Model
{
  private MineSweeper_Board mcBoard;

  /**
   * Checks if the user won the game
   *
   * @return true if user won, false if not
   */
  public boolean checkUserWin ()
  {
    return mcBoard.checkUserWin ();
  }

  /**
   * Updates the minesweeper board, if cell at the input position is a mine
   * it'll return -1, if the input position is an invalid update return 0
   * it'll return 0, if the update was successful it'll return 1
   *
   * @param x the row of the board
   * @param y the column of the board
   * @return an integer representing the result of updating the given position
   */
  public int updateBoard (int x, int y)
  {
    if (!mcBoard.validUpdate (x, y))
    {
      return 0; //The update position is invalid
    }
    else if (mcBoard.updateBoard (x, y))
    {
      return -1; //User lost on update
    }

    return 1; //Updated successfully
  }

  /**
   * Gets the minesweeper board
   *
   * @return The minesweeper board
   */
  public String[][] getBoard ()
  {
    return mcBoard.getBoard ();
  }

  /**
   * Initialize the minesweeper board based on the given difficulty
   *
   * @param difficulty the difficulty of this minesweeper game
   */
  public void initializeBoard (int difficulty)
  {
    mcBoard = new MineSweeper_Board (difficulty);
  }

  /**
   * Resets the board for the minesweeper game
   */
  public void resetBoard ()
  {
    mcBoard.resetBoard ();
  }
}