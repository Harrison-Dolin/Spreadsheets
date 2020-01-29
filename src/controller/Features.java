package edu.cs3500.spreadsheets.controller;

import javax.swing.JTextField;

import edu.cs3500.spreadsheets.view.View;

/**
 * Interface for a Worksheet commander using the features interface/pattern.
 */
public interface Features extends edu.cs3500.spreadsheets.provider.controller.Features {

  /**
   * Changes the contents of the model at the given coordinate. IF the cell already exists, then
   * it updates the contents of the cell with the given contents. If the cell does not exist
   * creates a cell in the model at those coordinates with the given contents.
   * @param col the column of the cell to be edited
   * @param row the row of the cell to be edited
   * @param text the contents of the cell to be added/updated
   */
  void editModel(int col, int row, JTextField text);

  /**
   * Starts the controller.
   */
  void controllerStart();

  /**
   * Saves the current values of the spreadsheet ot a file.
   */
  void saveSpreadsheet();

  /**
   * Had to add this to make providers code work.
   * @param view an instance of a View which represents how the spreadsheet will be viewed
   */
  void setView(View view);
}

