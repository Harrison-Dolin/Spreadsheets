package edu.cs3500.spreadsheets.model.function;

import java.util.List;
import edu.cs3500.spreadsheets.model.SpreadSheetModel;
import edu.cs3500.spreadsheets.model.formula.FormulaFunction;
import edu.cs3500.spreadsheets.model.formula.FormulaInterface;
import edu.cs3500.spreadsheets.model.formula.StringFormula;

/**
 * This function represents a concatinating function that concatinates strings. It has a list
 * of arguments which it evaluates to a stringFormula.
 */
public class ConcatFunction extends FormulaFunction {

  /**
   * Constructs a ConcatFunction, it has a list of arguments that it can evaluate.
   * @param arguments the list of arguments (list of FormulaInterface)
   */
  public ConcatFunction(List<FormulaInterface> arguments) {
    super(arguments);
  }

  @Override
  public StringFormula evaluate(SpreadSheetModel model) {
    String str = "";
    for (FormulaInterface f : this.arguments) {
      str += f.accept(new ConcatVisitor(model));
    }
    return new StringFormula(str);
  }
}
