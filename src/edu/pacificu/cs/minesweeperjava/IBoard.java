package edu.pacificu.cs.minesweeperjava;

/**
 * Creates a IBoard abstract class which provides an interface
 * for different boards
 *
 * @author Forbes Miyasato
 */

public abstract class IBoard
{
  protected String[][] mBoard;
  private int mRows;
  private int mColumns;

  /**
   * Initializes IBoard using parameter list values
   *
   * @param rows The number of rows the board has
   * @param columns The number of columns the board has
   */
  IBoard (int rows, int columns)
  {
    mBoard = new String[rows][columns];
    mRows = rows;
    mColumns = columns;
  }

  /**
   * Get the minesweeper board
   *
   * @return The minesweeper board
   */
  public String[][] getBoard ()
  {
    return mBoard;
  }

  /**
   * Checks if the input position is within the board boundaries
   *
   * @param x the row of the board
   * @param y the column of the board
   * @return true if input position is within the board, false if not
   */
  protected boolean withinBoard (int x, int y)
  {
    return (x >= 0 && x < mRows) && (y >= 0 && y < mColumns);
  }

  /**
   * Sets the cell at the given position to be the given cell type
   *
   * @param x        the row of the board
   * @param y        the column of the board
   * @param cellType the cell type we want to set at the input position
   */
  protected void setCell (int x, int y, String cellType)
  {
    mBoard[x][y] = cellType;
  }

  /**
   * Get the cell at the input position
   *
   * @param x the row of the board
   * @param y the column of the board
   * @return The cell at the input position
   */
  protected String getCell (int x, int y)
  {
    return mBoard[x][y];
  }
}
