package edu.pacificu.cs.minesweeperjava;

/**
 * Creates a IBoard abstract class which provides an interface
 * for different boards
 *
 * @author Forbes Miyasato
 */

public abstract class IBoard
{
  public abstract void initializeBoard (int difficulty);

  public abstract String[][] getBoard ();

  public abstract boolean withinBoard (int x, int y);
}
