package edu.cs3500.spreadsheets.model.formula;

import java.text.DecimalFormat;
import java.util.Stack;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.SpreadSheetModel;

/**
 * Represents a double value that is a ValueFormula.
 */
public class DoubleFormula implements ValueFormula {

  private double val;

  /**
   * Constructs a double formula representing the given double.
   *
   * @param d a double
   */
  public DoubleFormula(double d) {
    this.val = d;
  }

  @Override
  public <R> R accept(FormulaVisitor<R> visitor) {
    return visitor.visitDouble(this.val);
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
    //return String.format("%f", this.val);
    double answer = this.val;
    DecimalFormat df = new DecimalFormat("###.##");
    return df.format(answer);
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }

    if (!(that instanceof DoubleFormula)) {
      return false;
    }

    return ((DoubleFormula) that).val == this.val;
  }

  @Override
  public int hashCode() {
    return Long.hashCode(Double.doubleToLongBits(this.val));
  }


}

