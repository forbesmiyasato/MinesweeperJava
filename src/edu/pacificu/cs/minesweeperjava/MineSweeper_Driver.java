package edu.pacificu.cs.minesweeperjava;

/**
 * Creates a Driver class to run the minesweeper game
 *
 * @author Forbes Miyasato
 */

public class MineSweeper_Driver
{

  /**
   * A simple driver that runs the Minesweeper game
   *
   * @param args command line arguments if any
   */
  public static void main (String[] args)
  {
    IMineSweeper_View cView = new MineSweeper_View_TextUI ();

    cView.eventLoop ();
  }
}