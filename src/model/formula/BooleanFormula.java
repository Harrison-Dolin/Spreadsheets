package edu.cs3500.spreadsheets.model.formula;

import java.util.Stack;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.SpreadSheetModel;

/**
 * Represents a boolean value that is a ValueFormula.
 */
public class BooleanFormula implements ValueFormula {

  final boolean val;

  /**
   * Constructs a BooleanFormula representing the given boolean.
   *
   * @param b a boolean
   */
  public BooleanFormula(boolean b) {
    this.val = b;
  }

  @Override
  public <R> R accept(FormulaVisitor<R> visitor) {
    return visitor.visitBoolean(this.val);
  }

  @Override
  public ValueFormula evaluate(SpreadSheetModel model) {
    return this;
  }

  @Override
  public boolean depHelp(Stack<Coord> seen, SpreadSheetModel model) {
    return false;
  }

  @Override
  public String toString() {
    return Boolean.toString(this.val);
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }

    if (!(that instanceof BooleanFormula)) {
      return false;
    }

    return ((BooleanFormula) that).val == this.val;
  }

  @Override
  public int hashCode() {
    return Long.hashCode(Boolean.toString(this.val).length());
  }

}


