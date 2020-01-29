import java.util.List;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.SpreadSheetModel;
import edu.cs3500.spreadsheets.model.cell.Cell;
import edu.cs3500.spreadsheets.model.cell.CellInterface;
import edu.cs3500.spreadsheets.model.formula.ValueFormula;

/**
 * Mock representation of a Spreadsheet Model. Appends to the given Stringbuilder to signify
 * which methods are called.
 */
public class MockSpreadsheet implements SpreadSheetModel {
  final StringBuilder log;

  /**
   * Constructor for a mock representation of a Spreadsheet. Initializes the stringbuilder.
   * @param log a Stringbuilder to be appended to
   */
  public MockSpreadsheet(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void createCell(int col, int row, String contents) {
    log.append("createCell: " + col + " " + row + " " + contents);
  }

  @Override
  public void editCellContents(Coord coordinates, String contents) {
    log.append("editCellContents: " + coordinates.col + " " + coordinates.row + " " + contents
            + "\n");
  }

  @Override
  public String cellOriginalContents(Coord coordinates) {
    log.append("cellOriginalContents: " + coordinates.col + " " + coordinates.row + "\n");
    return null;
  }

  @Override
  public ValueFormula evaluatedContents(Coord cell) {
    log.append("evaluatedContents: " + cell.col + " " + cell.row + "\n");
    return null;
  }

  @Override
  public String getEvaluatedContentsAsString(Coord coord) {
    return null;
  }

  @Override
  public void deleteCellContents(Coord coord) {
    log.append("deleteCellContents: " + coord.col + " " + coord.row + "\n");
  }

  @Override
  public CellInterface getCell(Coord coord) {
    log.append("getCell:" + coord.col + " " + coord.row + "\n");
    if (coord.col == 1 && coord.row == 1) {
      return new Cell("hello", new Coord(1,1), this);
    }
    return null;
  }

  @Override
  public Coord getUpperBound() {
    return null;
  }

  @Override
  public Coord getLowerBound() {
    return null;
  }

  @Override
  public List<Coord> getCoordsInCol(int col) {
    return null;
  }
}
