package edu.cs3500.spreadsheets.model;

/**
 * Builds a worksheet, in this case, an instance of SpreadSheet.
 */
public class WorksheetBuilder implements WorksheetReader.WorksheetBuilder<SpreadSheet> {
  private final SpreadSheet spreadsheet;

  /**
   * Construct this WorksheetBuilder.
   */
  public WorksheetBuilder() {
    this.spreadsheet = new SpreadSheet();
  }


  /**
   * Return this WorksheetBuilder with a cell added to the spreadSheet, with the given contents at
   * the given coordinates.
   *
   * @param col      the column of the new cell (1-indexed)
   * @param row      the row of the new cell (1-indexed)
   * @param contents the raw contents of the new cell: may be {@code null}, or any string. Strings
   *                 beginning with an {@code =} character should be treated as formulas; all other
   *                 strings should be treated as number or boolean values if possible, and string
   *                 values otherwise.
   * @return this WorksheetBuilder with a cell added to the spreadSheet
   */
  public WorksheetBuilder createCell(int col, int row, String contents) {
    this.spreadsheet.createCell(col, row, contents);
    // If contents starts with an '=' then its a formula
    return this;
  }

  /**
   * Return a SpreadSheet created by this WorkSheetBuilder.
   *
   * @return a SpreadSheet
   */
  public SpreadSheet createWorksheet() {
    return this.spreadsheet;
  }
}
