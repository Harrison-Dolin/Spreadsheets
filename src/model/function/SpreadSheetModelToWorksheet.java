package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.cell.CellInterfaceToCell;
import edu.cs3500.spreadsheets.provider.model.Cell;
import edu.cs3500.spreadsheets.provider.model.Worksheet;

/**
 * This is an adapter that allows you to use a SpreadSheetModel as a Worksheet.
 * Adapts our spreadsheet implementation to our provider's worksheet.
 */
public class SpreadSheetModelToWorksheet implements Worksheet {
  SpreadSheetModel modelAdaptee;

  /**
   * Constructs a SpreadSheetModelToWorksheet.
   * @param modelAdaptee the spreadSheet to be adapted, which is our spreadsheet
   *     (interface SpreadSheetModel)
   */
  public SpreadSheetModelToWorksheet(SpreadSheetModel modelAdaptee) {
    this.modelAdaptee = modelAdaptee;
  }

  @Override
  public void addCell(Coord coord, Cell cell) {
    modelAdaptee.createCell(coord.col, coord.row, cell.getRawContents());
  }

  @Override
  public Cell getCellAt(Coord coord) {
    return new CellInterfaceToCell(modelAdaptee.getCell(coord));
  }

  @Override
  public Object evalCell(Coord coord) {
    return null;
  }

  @Override
  public void updateCell(Coord coord, Cell cell) {
    modelAdaptee.editCellContents(coord, cell.getRawContents());
  }

  @Override
  public String sheetRawContents() {
    return null;
  }

  @Override
  public void evaluate() {
    //This method was not needed to make the provider's view work with our code.
  }
}
