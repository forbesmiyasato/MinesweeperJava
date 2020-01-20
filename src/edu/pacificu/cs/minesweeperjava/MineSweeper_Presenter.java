package edu.pacificu.cs.minesweeperjava;

/**
 * Creates a MineSweeper_Presenter class where all operations work on all
 * the presentation logic between the model and view
 *
 * @author Forbes Miyasato
 */
public class MineSweeper_Presenter
{
  private IMineSweeper_View mcView;
  private MineSweeper_Model mcModel;

  /**
   * Initializes MineSweeper_Presenter using parameter list values
   *
   * @param cView the view used for this minesweeper game
   * @param cModel the minesweeper model
   */
  public MineSweeper_Presenter (IMineSweeper_View cView,
      MineSweeper_Model cModel)
  {
    mcView = cView;
    mcModel = cModel;
  }

  /**
   * Tells the model the user selected cell, then makes the view react based
   * on the models response.
   *
   * @param x the row of the board
   * @param y the column of the board
   * @return true if the user made an normal update on selected cell, false
   *         if selection resulted in user losing, winning or invalid
   *         selection
   */
  public boolean UserSelectedCell (int x, int y)
  {
    //response = -1 if user lost, response = 0 if user selected an invalid
    //position, response = 1 if user won
    int response = mcModel.updateBoard (x, y);
    if (response == -1)
    {
      mcView.userLost ();
      return false;
    }
    else if (response == 0)
    {
      mcView.invalidPosition ();
      return false;
    }

    if (mcModel.checkUserWin ())
    {
      mcView.userWon ();
      return false;
    }
    mcView.setBoard (mcModel.getBoard ());
    return true;
  }

  /**
   * Sets the difficulty for this minesweeper game
   *
   * @param difficulty the difficulty of this minesweeper game
   */
  public void setDifficulty (int difficulty)
  {
    mcModel.initializeBoard (difficulty);
    mcView.setBoard (mcModel.getBoard ());
  }

  /**
   * Resets the minesweeper game
   */
  public void resetGame () {
    mcModel.resetBoard ();
    mcView.setBoard (mcModel.getBoard ());
  }
}