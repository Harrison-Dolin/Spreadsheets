package edu.cs3500.spreadsheets.model.formula;

import java.util.List;
import java.util.Stack;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.SpreadSheetModel;

/**
 * Represents a function that is a formula (FormulaInterface). This function has a list of arguments
 * which it can evaluate. The arguments are formulas (FormulaInterface).
 */
public abstract class FormulaFunction implements IFormulaFunction {

  protected List<FormulaInterface> arguments;

  /**
   * Constructs a FormulaFunction that has a list of arguments represented as FormulaInterface
   * given.
   */
  public FormulaFunction(List<FormulaInterface> arguments) {
    this.arguments = arguments;
  }

  @Override
  public <R> R accept(FormulaVisitor<R> visitor) {
    return visitor.visitFunction(this);
  }

  @Override
  public boolean depHelp(Stack<Coord> seen, SpreadSheetModel model) {
    boolean containsCycle = false;
    for (FormulaInterface f : this.arguments) {
      containsCycle = containsCycle || f.depHelp(seen, model);
    }
    return containsCycle;
  }


}
