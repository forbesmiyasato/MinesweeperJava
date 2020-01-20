package edu.pacificu.cs.minesweeperjava;

/**
 * Creates a IMineSweeper abstract class which provides an interface
 * for Minesweeper views
 *
 * @author Forbes Miyasato
 */

public abstract class IMineSweeper_View
{
  protected boolean mbLost;
  protected boolean mbWon;

  abstract public boolean UserSelectCell ();

  abstract public void eventLoop ();

  abstract public void userLost ();

  abstract public void userWon ();

  abstract public void displayHeader ();

  abstract public void enterDifficulty ();

  abstract public void setBoard (String[][] board);

  abstract public void printBoard ();

  abstract public void printInvalidPosition ();

  abstract public void printMessage ();
}
