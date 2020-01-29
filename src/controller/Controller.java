package edu.cs3500.spreadsheets.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JTextField;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.SpreadSheetModel;
import edu.cs3500.spreadsheets.view.IViewModel;
import edu.cs3500.spreadsheets.view.TextualView;
import edu.cs3500.spreadsheets.view.View;
import edu.cs3500.spreadsheets.view.ViewModel;

/**
 * This controller is a middleman between the spreadsheet model and view, It listens to events
 * triggered by the view and executes the appropriate reaction to these events.
 */
public class Controller implements Features {

  private SpreadSheetModel model;
  private View view;
  private String file;

  /**
   * Constructs this controller. Initializes the model, view, and file to be used.
   *
   * @param model represents a spreadsheet model
   * @param view  represents a spreadsheet view
   * @param file  represents a file to saved to and edit
   */
  public Controller(SpreadSheetModel model, View view, String file) {
    if (model == null || view == null) {
      throw new IllegalArgumentException();
    }
    this.model = model;
    this.view = view;
    this.file = file;
  }

  /**
   * Constructs this controller for testing. Initializes the model, view, and an empty file to be
   * used.
   *
   * @param model represents a spreadsheet model
   * @param view  represents a spreadsheet view
   */
  public Controller(SpreadSheetModel model, View view) {
    this(model, view, "");
  }


  /**
   * This is a constructor that we had to create in order to make a Controller with our provider's
   * view.
   * @param model a SpreadSheetModel representing a spreadsheet model
   */
  public Controller(SpreadSheetModel model) {
    if (model == null) {
      throw new IllegalArgumentException();
    }
    this.model = model;
    this.file = "";
  }

  @Override
  public void setView(View v) {
    this.view = v;
  }

  /**
   * Renders the view, calls the views render method.
   */
  private void renderView() {
    try {
      view.render();
    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override
  public void saveSpreadsheet() {
    try {
      FileWriter fw = new FileWriter(file);
      PrintWriter pw = new PrintWriter(fw);
      IViewModel vw = new ViewModel(model);
      View tView = new TextualView(vw, pw);
      tView.render();
      pw.close();
    } catch (IOException e) {
      // This is empty because if the user tries to save and an IOException occurs, then it
      // just won't save the model, which is what is intended, so no body is needed here.
    }
  }

  @Override
  public void editModel(int col, int row, JTextField text) {

    String textBar = text.getText();
    if (this.model.getCell(new Coord(col, row)) == null) {
      this.model.createCell(col, row, text.getText());
    } else {
      this.model.editCellContents(new Coord(col, row), text.getText());
    }
    text.setText(textBar);
    renderView();
  }

  @Override
  public void controllerStart() {
    view.addFeatures(this);
    renderView();
  }

  @Override
  public void updateCell(String rawContents, Coord location) {
    if (this.model.getCell(location) == null) {
      this.model.createCell(location.col, location.row, rawContents);
    } else {
      this.model.editCellContents(location, rawContents);
    }
  }
}

