package edu.pacificu.cs.minesweeperjava;

/**
 * Creates a IMineSweeper abstract class which provides an interface
 * for Minesweeper views
 *
 * @author Forbes Miyasato
 */

public interface IMineSweeper_View
{

  void eventLoop ();
  /**
   * Sets the game state to user lost
   */
  void userLost ();

  /**
   * Sets the game state to user won
   */
  void userWon ();

  /**
   * Sets the Views game board
   *
   * @param board The game board to set to
   */
  void setBoard (String[][] board);

  /**
   * Handles user selecting invalid position
   */
  void invalidPosition ();
}
