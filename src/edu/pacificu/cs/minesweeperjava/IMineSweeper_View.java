package edu.pacificu.cs.minesweeperjava;

/**
 * Creates a IMineSweeper abstract class which provides an interface
 * for Minesweeper views
 *
 * @author Forbes Miyasato
 */

public interface IMineSweeper_View
{
  boolean UserSelectCell ();

  void eventLoop ();

  void userLost ();

  void userWon ();

  void displayHeader ();

  void enterDifficulty ();

  void setBoard (String[][] board);

  void printBoard ();

  void printInvalidPosition ();

  void printMessage ();
}
