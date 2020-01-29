package edu.cs3500.spreadsheets.model.formula;

import java.util.List;
import java.util.Stack;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.SpreadSheetModel;


/**
 * Represents a reference that is a formula. This reference consists of a list of coordinates, which
 * are the coordinates of the cells that this reference refers to.
 */
public class ReferenceFormula implements FormulaInterface {

  private List<Coord> reference;
  private boolean columnRef;

  /**
   * Constructs a ReferenceFormula.
   *
   * @param reference a list of coordinates, which are the coordinates of the cells that this
   *     reference refers to.
   */
  public ReferenceFormula(List<Coord> reference, Boolean columnRef) {
    this.reference = reference;
    this.columnRef = columnRef;
  }

  @Override
  public <R> R accept(FormulaVisitor<R> visitor) {
    return visitor.visitReference(this.reference);
  }

  @Override
  public ValueFormula evaluate(SpreadSheetModel model) {
    if (this.columnRef) {
      System.out.print("im in");
      this.reference = model.getCoordsInCol(this.reference.get(0).col);
    }
    System.out.print("im out");
    return model.getCell(this.reference.get(0)).evaluateCell();
    //evaluates top left cell
  }

  @Override
  public boolean depHelp(Stack<Coord> seen, SpreadSheetModel model) {
    boolean cyclicRef = false;
    for (Coord c : this.reference) {
      if (seen.contains(c)) {
        cyclicRef = true;
      }
      seen.push(c);
      cyclicRef = cyclicRef || model.getCell(c).depOnCycle(seen);
      seen.pop();
    }
    return cyclicRef;
  }
}
