package edu.cs3500.spreadsheets.model.cell;

import java.util.List;

import edu.cs3500.spreadsheets.provider.model.Cell;
import edu.cs3500.spreadsheets.provider.model.CellVisitor;
import edu.cs3500.spreadsheets.provider.model.Coord;

/**
 * This is an adapter that allows you to use a CellInterface as a Cell.
 * Adapts our cell implementation to our provider's cell.
 */
public class CellInterfaceToCell implements Cell {
  CellInterface cellAdaptee;


  /**
   * Constructs a CellInterfaceToCell.
   * @param cellAdaptee the cell to be adapted, which is our cell (interface CellInterface)
   */
  public CellInterfaceToCell(CellInterface cellAdaptee) {
    this.cellAdaptee = cellAdaptee;
  }

  @Override
  public <R> R accept(CellVisitor<R> visitor) {
    return null;
  }

  @Override
  public boolean isDouble() {
    return false;
  }

  @Override
  public Object getEvaluatedContents() {
    return null;
  }

  @Override
  public boolean hasBeenEvaluated() {
    return false;
  }

  @Override
  public void setEvaluatedContents(Object contents) {
    //This method is not called nor needed to make the provider's view with with ours.
    // Additionally, our cell does not have a visitor pattern so no-where is Object returned.
  }

  @Override
  public void setRawContents(String contents) {
    //This method is not called nor needed to make the provider's view with with ours.
  }

  @Override
  public String getRawContents() {
    if (this.cellAdaptee == null) {
      return "";
    } else {
      return this.cellAdaptee.getOriginalInput();
    }
  }

  @Override
  public String toString() {
    if (this.cellAdaptee == null) {
      return "";
    } else {
      return this.cellAdaptee.evaluatedInputAsString();
    }
  }

  @Override
  public void resetEvaluation() {
    //This method is not called nor needed to make the provider's view with with ours.
  }

  @Override
  public boolean reachesCoord(List<Coord> coordinate) {
    return false;
  }
}
