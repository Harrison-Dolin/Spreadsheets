package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import edu.cs3500.spreadsheets.model.cell.Cell;
import edu.cs3500.spreadsheets.model.cell.CellInterface;
import edu.cs3500.spreadsheets.model.formula.StringFormula;
import edu.cs3500.spreadsheets.model.formula.ValueFormula;

/**
 * Represents a spreadsheet. Made up of many cells, you can edit cells, evaluate them, get the
 * original user input, delete the contents.
 */
public class SpreadSheet implements SpreadSheetModel {

  private HashMap<Coord, CellInterface> grid;


  /**
   * Constructs a SpreadSheet.
   */
  public SpreadSheet() {
    this.grid = new HashMap<Coord, CellInterface>();
  }


  /** Creates a new Cell at the given row and column using the given contents.
   * @param col an int representing the desired column to make the cell at
   * @param row an int representing the desired row to make the cell at
   * @param contents a String representing the desired contents to populate the cell with
   */
  public void createCell(int col, int row, String contents) {
    Coord cellCoord = new Coord(col, row);
    CellInterface cell = new Cell(contents, cellCoord, this);
    grid.put(cellCoord, cell);
  }


  /**
   * Changes the contents of the cell at the given coordinates by replacing it with a cell of the
   * given contents.
   *
   * @param coordinates the coordinates of the cell
   * @param contents    the new contents of the cell
   */
  @Override
  public void editCellContents(Coord coordinates, String contents) {
    this.grid.replace(coordinates, new Cell(contents, coordinates, this));
    for (Coord coord : grid.keySet()) { //reset all cashes
      grid.get(coord).resetEvaluatedInput();
      grid.get(coord).resetCycleCash();
    }
  }


  @Override
  public String cellOriginalContents(Coord coordinates) {
    if (this.grid.get(coordinates) == null) {
      return "";
    } else {
      return this.grid.get(coordinates).getOriginalInput();
    }
  }

  @Override
  public ValueFormula evaluatedContents(Coord coord) {
    if (this.grid.get(coord) == null) {
      return new StringFormula("");
    } else {
      return this.grid.get(coord).evaluateCell();
    }
  }

  @Override
  public String getEvaluatedContentsAsString(Coord coord) {
    return this.evaluatedContents(coord).toString();
  }

  /**
   * Deletes the contents of the cell at the given coordinate by replacing it with a cell with no
   * coordinates.
   *
   * @param coordinates the coordinates of the cell
   */
  @Override
  public void deleteCellContents(Coord coordinates) {
    this.grid.remove(coordinates);
    for (Coord coord : grid.keySet()) { //reset all cashes
      grid.get(coord).resetEvaluatedInput();
      grid.get(coord).resetCycleCash();
    }
  }

  @Override
  public CellInterface getCell(Coord coord) {
    return this.grid.get(coord);
  }

  @Override
  public Coord getUpperBound() {
    int largestCol = 1;
    int largestRow = 1;
    for (Coord coord : grid.keySet()) {
      if (coord.col > largestCol) {
        largestCol = coord.col;
      }
      if (coord.row > largestRow) {
        largestRow = coord.row;
      }
    }
    return new Coord(largestCol, largestRow);
  }

  @Override
  public Coord getLowerBound() {
    int smallestCol = 1;
    int smallestRow = 1;
    for (Coord coord : grid.keySet()) {
      if (coord.col < smallestCol) {
        smallestCol = coord.col;
      }
      if (coord.row < smallestRow) {
        smallestRow = coord.row;
      }
    }
    return new Coord(smallestCol, smallestRow);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SpreadSheet s = (SpreadSheet) o;
    boolean same = true;
    for (Coord coord : this.grid.keySet()) {
      CellInterface cell = this.grid.get(coord);
      same = same && cell.equals(s.getCell(coord));
    }
    for (Coord coord : s.grid.keySet()) {
      same = same && this.getCell(coord).equals(s.getCell(coord));
    }
    return same;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.grid);
  }

  public List<Coord> getCoordsInCol(int col) {
    List<Coord> colCells = new ArrayList<>();
    for (Coord coord : this.grid.keySet()) {
      if(coord.col == col) {
        colCells.add(coord);
      }
    }
    return colCells;
  }



}
