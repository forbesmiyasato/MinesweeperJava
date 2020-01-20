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
  private int mDifficulty;

  /**
   * Initializes MineSweeper_Board using parameter list values
   *
   * @param difficulty the difficulty of this minesweeper game
   */
  public MineSweeper_Board (int difficulty)
  {
    super (BOARD_ROWS, BOARD_COLUMNS);
    mDifficulty = difficulty;
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

    for (int i = 0; i < BOARD_ROWS; i++)
    {
      for (int j = 0; j < BOARD_COLUMNS; j++)
      {
        setCell (i, j, ".");
      }
    }

    Random random = new Random();
    random.setSeed (0);
    //place mines on board
    while (mines > 0)
    {
      int row = random.nextInt (BOARD_ROWS);
      int column = random.nextInt (BOARD_COLUMNS);

      if (!isMine (column, row))
      {
        setCell (column, row, "@");
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

  /**
   * Resets the minesweeper board (to it's current difficulty)
   */
  public void resetBoard() {
    initializeBoard (mDifficulty);
  }
}

