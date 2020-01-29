package edu.cs3500.spreadsheets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import edu.cs3500.spreadsheets.controller.Controller;
import edu.cs3500.spreadsheets.controller.Features;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.SpreadSheet;
import edu.cs3500.spreadsheets.model.SpreadSheetModel;
import edu.cs3500.spreadsheets.model.SpreadSheetModelToWorksheet;
import edu.cs3500.spreadsheets.model.WorksheetBuilder;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.provider.model.Worksheet;
import edu.cs3500.spreadsheets.provider.view.WorksheetEditView;
import edu.cs3500.spreadsheets.view.EditingView;
import edu.cs3500.spreadsheets.view.IViewModel;
import edu.cs3500.spreadsheets.view.TextualView;
import edu.cs3500.spreadsheets.view.View;
import edu.cs3500.spreadsheets.view.ViewModel;
import edu.cs3500.spreadsheets.view.VisualPanel;
import edu.cs3500.spreadsheets.view.VisualView;
import edu.cs3500.spreadsheets.view.WorksheetViewToView;

/**
 * The main class for our program.
 */
public class BeyondGood {
  /**
   * The main entry point.
   *
   * @param args any command-line arguments
   */

  public static void main(String[] args) {

    if (args.length == 1 && args[0].equals("-gui")) {
      try {
        IViewModel viewModel = new ViewModel(new SpreadSheet());
        View view = new VisualView(new VisualPanel(viewModel));
        view.render();
      } catch (IOException e) {
        System.out.print(e.getMessage());
      }
    } else if (args.length == 1 && args[0].equals("-edit")) {
      SpreadSheetModel s = new SpreadSheet();
      IViewModel viewModel = new ViewModel(s);
      View temp = new EditingView(viewModel);
      Features controller = new Controller(s, temp, "");
      controller.controllerStart();
    } else if (args.length == 1 && args[0].equals("-provider")) {
      SpreadSheetModel s = new SpreadSheet();
      Worksheet providerS = new SpreadSheetModelToWorksheet(s);
      Features controller = new Controller(s);
      View view = new WorksheetViewToView(new WorksheetEditView(providerS, controller));
      controller.setView(view);
      controller.controllerStart();
    } else if (args[0].equals("-in")) {
      SpreadSheetModel spreadsheet;
      try {
        spreadsheet = WorksheetReader.read(new WorksheetBuilder(),
                new FileReader(args[1]));

        if (args.length == 3 && args[2].equals("-gui")) {
          try {
            IViewModel viewModel = new ViewModel(spreadsheet);
            View view = new VisualView(new VisualPanel(viewModel));
            view.render();
          } catch (IOException e) {
            System.out.print(e.getMessage());
          }
        }

        if (args.length == 4 && args[2].equals("-save")) {
          try {
            File file = new File(args[3]);
            FileWriter f = new FileWriter(file.getPath(), true);
            IViewModel viewModel = new ViewModel(spreadsheet);
            View tView = new TextualView(viewModel, f);
            tView.render();
            f.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }

        if (args.length == 3 && args[2].equals("-edit")) {
          IViewModel viewModel = new ViewModel(spreadsheet);
          View temp = new EditingView(viewModel);
          Features controller = new Controller(spreadsheet, temp, args[1]);
          controller.controllerStart();
        }

        if (args.length == 3 && args[2].equals("-provider")) {
          Worksheet providerS = new SpreadSheetModelToWorksheet(spreadsheet);
          Features controller = new Controller(spreadsheet);
          View view = new WorksheetViewToView(new WorksheetEditView(providerS, controller));
          controller.setView(view);
          controller.controllerStart();
        }


        if (args.length == 4 && args[2].equals("-eval")) {
          try {
            System.out.print(spreadsheet.getEvaluatedContentsAsString(getCoordinateOf(args[3],
                    getFirstDigitIndex(args[3]))));
          } catch (Exception e) {
            System.out.print("Error in cell:" + args[3] + e.getMessage());
          }
        }
      } catch (FileNotFoundException f) {
        System.out.print("Invalid File");
      }
    }
  }


  /**
   * Creates the integer value of the first digit in a given string representation of a cell.
   *
   * @param s inputted string representation of a cell or list of cells
   * @return integer value of the string
   */
  private static int getFirstDigitIndex(String s) {
    int index = -1;
    for (int i = 0; i < s.length(); i++) {
      if (Character.isDigit(s.charAt(i))) {
        index = i;
      }
    }
    if (index == -1) {
      throw new IllegalArgumentException("wrong format for a cell");
    }
    return index;
  }


  /**
   * Calculates the coordinate of a string representation of a cell.
   *
   * @param s          given string representation of a cell or list of cells
   * @param digitIndex index of the starting point of the cell to be calculated
   * @return the Coord value of the cell
   */
  private static Coord getCoordinateOf(String s, int digitIndex) {
    int col = Coord.colNameToIndex(s.substring(0, digitIndex));
    int row;
    try {
      row = Integer.parseInt(s.substring(digitIndex));
    } catch (Exception e) {
      throw new IllegalArgumentException("wrong format for cell");
    }
    return new Coord(col, row);
  }

}

