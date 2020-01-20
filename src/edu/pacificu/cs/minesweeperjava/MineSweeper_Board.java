package edu.pacificu.cs.minesweeperjava;

import java.util.Random;

/**
 * Creates a MineSweeper_Board class where all operations work on the board
 * for minesweeper
 *
 * @author Forbes Miyasato
 */
public class MineSweeper_Board extends IBoard
{
  private boolean[][] mbCantUpdate;
  private int mNotMine;
  private static final int BOARD_ROWS = 9;
  private static final int BOARD_COLUMNS = 9;
  private String[][] mBoard;

  /**
   * Initializes MineSweeper_Board using parameter list values
   *
   * @param difficulty the difficulty of this minesweeper game
   */

  public MineSweeper_Board (int difficulty)
  {
    initializeBoard (difficulty);
  }

  /**
   * Updates the minesweeper board, if cell at the input position is a mine
   * it'll terminate the game, if isn't mine and has adjacent mines then
   * change current cell into adjacent mine counts, if isn't mine and doensn't
   * have adjacent mines then recurse on all adjacent cells.
   *
   * @param x the row of the board
   * @param y the column of the board
   * @return true if current cell is mine, false if not
   */
  public boolean updateBoard (int x, int y)
  {
    int adjacentMines;
    if (isMine (x, y))
    {
      return true;
    }

    //When current cell is not a mine, decrement the total count of non-mine
    //cells, set the current cell to not be updatable, and count the adjacent
    //mines
    mNotMine--;
    mbCantUpdate[x][y] = true;
    adjacentMines = countAdjacentMines (x, y);

    if (adjacentMines > 0)
    {
      setCell (x, y, Integer.toString (adjacentMines));
    }
    else
    {
      //If current cell has no adjacent mines, set the cell to blank
      //and recurse on all adjacent cells.
      setCell (x, y, " ");
      if (validUpdate (x + 1, y) && !isMine (x + 1, y))
      {
        updateBoard (x + 1, y); //up
      }
      if (validUpdate (x - 1, y) && !isMine (x - 1, y))
      {
        updateBoard (x - 1, y); //bottom
      }
      if (validUpdate (x, y - 1) && !isMine (x, y - 1))
      {
        updateBoard (x, y - 1); //left
      }
      if (validUpdate (x, y + 1) && !isMine (x, y + 1))
      {
        updateBoard (x, y + 1); //right
      }
      if (validUpdate (x + 1, y - 1) && !isMine (x + 1, y - 1))
      {
        updateBoard (x + 1, y - 1); //top left
      }
      if (validUpdate (x + 1, y + 1) && !isMine (x + 1, y + 1))
      {
        updateBoard (x + 1, y + 1); //top right
      }
      if (validUpdate (x - 1, y - 1) && !isMine (x - 1, y - 1))
      {
        updateBoard (x - 1, y - 1); //bottom left
      }
      if (validUpdate (x - 1, y + 1) && !isMine (x - 1, y + 1))
      {
        updateBoard (x - 1, y + 1); //bottom right
      }
    }

    return false;
  }

  /**
   * Checks if the input position is within the board boundaries
   *
   * @param x the row of the board
   * @param y the column of the board
   * @return true if input position is within the board, false if not
   */
  public boolean withinBoard (int x, int y)
  {
    return (x >= 0 && x < BOARD_ROWS) && (y >= 0 && y < BOARD_COLUMNS);
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
   * Initialize the minesweeper board based on the given difficulty
   *
   * @param difficulty the difficulty of this minesweeper game
   */
  public void initializeBoard (int difficulty)
  {
    final int difficultyIncrement = 3;
    int mines = BOARD_ROWS + (difficultyIncrement * difficulty);
    final int totalCells = BOARD_COLUMNS * BOARD_ROWS;
    mNotMine = totalCells - mines;
    mbCantUpdate = new boolean[BOARD_ROWS][BOARD_COLUMNS];
    mBoard = new String[BOARD_ROWS][BOARD_COLUMNS];

    for (int i = 0; i < mBoard.length; i++)
    {
      for (int j = 0; j < mBoard[0].length; j++)
      {
        setCell (i, j, ".");
      }
    }

    //place mines on board
    while (mines > 0)
    {
      int x = new Random ().nextInt (BOARD_ROWS);
      int y = new Random ().nextInt (BOARD_COLUMNS);

      if (!isMine (x, y))
      {
        setCell (x, y, "@");
        mines--;
      }
    }
  }

  /**
   * Checks if the user won by having all non mine cells updated
   *
   * @return true if non-mine cells count is 0.
   */
  public boolean checkUserWin ()
  {
    return mNotMine == 0;
  }

  /**
   * Counts the number of adjacent mines from the input position
   *
   * @param x the row of the board
   * @param y the column of the board
   * @return The number of adjacent mines
   */
  public int countAdjacentMines (int x, int y)
  {
    int count = 0;
    //above
    if (withinBoard (x + 1, y))
    {
      if (isMine (x + 1, y))
      {
        count++;
      }
    }
    //below
    if (withinBoard (x - 1, y))
    {
      if (isMine (x - 1, y))
      {
        count++;
      }
    }
    //left
    if (withinBoard (x, y - 1))
    {
      if (isMine (x, y - 1))
      {
        count++;
      }
    }
    //right
    if (withinBoard (x, y + 1))
    {
      if (isMine (x, y + 1))
      {
        count++;
      }
    }
    //top left
    if (withinBoard (x + 1, y - 1))
    {
      if (isMine (x + 1, y - 1))
      {
        count++;
      }
    }
    //top right
    if (withinBoard (x + 1, y + 1))
    {
      if (isMine (x + 1, y + 1))
      {
        count++;
      }
    }
    //bottom left
    if (withinBoard (x - 1, y - 1))
    {
      if (isMine (x - 1, y - 1))
      {
        count++;
      }
    }
    //bottom right
    if (withinBoard (x - 1, y + 1))
    {
      if (isMine (x - 1, y + 1))
      {
        count++;
      }
    }

    return count;
  }

  /**
   * Checks if the input position can be updated, by being within
   * the board, and haven't been updated before.
   *
   * @param x the row of the board
   * @param y the column of the board
   * @return True if the input position can be updated, false if can't
   */
  public boolean validUpdate (int x, int y)
  {
    return withinBoard (x, y) && !mbCantUpdate[x][y];
  }

  /**
   * Get the cell at the input position
   *
   * @param x the row of the board
   * @param y the column of the board
   * @return The cell at the input position
   */
  private String getCell (int x, int y)
  {
    return mBoard[x][y];
  }

  /**
   * Sets the cell at the given position to be the given cell type
   *
   * @param x the row of the board
   * @param y the column of the board
   * @param cellType the cell type we want to set at the input position
   */
  private void setCell (int x, int y, String cellType)
  {
    mBoard[x][y] = cellType;
  }

  /**
   * Checks if the cell at the input position is a mine
   *
   * @param x the row of the board
   * @param y the column of the board
   * @return True if the cell at the input position is a mine, false if not
   */
  private boolean isMine (int x, int y)
  {
    return getCell (x, y).equals ("@");
  }
}
