package edu.pacificu.cs.minesweeperjava;

/**
 * View interface IMineSweeper for Minesweeper views
 *
 * @author Forbes Miyasato
 */

public interface IMineSweeper_View
{

  /**
   * Main Loop for the game.
   */
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
