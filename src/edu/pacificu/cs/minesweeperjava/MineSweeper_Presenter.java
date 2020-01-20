package edu.pacificu.cs.minesweeperjava;

import java.util.Scanner;

/**
 * Creates a MineSweeper_Presenter class where all operations work on all
 * the presentation logic between the model and view
 *
 * @author Forbes Miyasato
 */
public class MineSweeper_Presenter
{
  private IMineSweeper_View mpcView;
  private MineSweeper_Model mpcModel;

  /**
   * Initializes MineSweeper_Presenter using parameter list values
   *
   * @param cView the view used for this minesweeper game
   * @param cModel the minesweeper model
   */
  public MineSweeper_Presenter (IMineSweeper_View cView,
      MineSweeper_Model cModel)
  {
    mpcView = cView;
    mpcModel = cModel;
  }

  /**
   * Tells the model the user selected cell, then makes the view react based
   * on the models response.
   *
   * @param x the row of the board
   * @param y the column of the board
   * @return true if the view should print board after this user selection,
   * false if shouldn't
   */
  public boolean UserSelectedCell (int x, int y)
  {
    //response = -1 if user lost, response = 0 if user selected an invalid
    //position, response = 1 if user won
    int response = mpcModel.updateBoard (x, y);
    if (response == -1)
    {
      mpcView.userLost ();
      return false;
    }
    else if (response == 0)
    {
      mpcView.printInvalidPosition ();
      return false;
    }

    if (mpcModel.checkUserWin ())
    {
      mpcView.userWon ();
      return false;
    }
    mpcView.setBoard (mpcModel.getBoard ());
    return true;
  }

  /**
   * Sets the difficulty for this minesweeper game
   *
   * @param difficulty the difficulty of this minesweeper game
   */
  public void setDifficulty (int difficulty)
  {
    mpcModel.initializeBoard (difficulty);
    mpcView.setBoard (mpcModel.getBoard ());
  }
}