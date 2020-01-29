package edu.cs3500.spreadsheets.model.formula;

import java.util.Stack;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.SpreadSheetModel;

/**
 * Represents a String that is a formula, specifically a value formula.
 */
public class StringFormula implements ValueFormula {

  private String val;

  /**
   * Constructs a StringFormula representing a String.
   *
   * @param s the String
   */
  public StringFormula(String s) {
    this.val = s;
  }

  @Override
  public <R> R accept(FormulaVisitor<R> visitor) {
    return visitor.visitString(this.val);
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
    return this.val;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }

    if (!(that instanceof StringFormula)) {
      return false;
    }

    return ((StringFormula) that).val.equals(this.val);
  }

  @Override
  public int hashCode() {
    return Long.hashCode(this.val.length());
  }
}

