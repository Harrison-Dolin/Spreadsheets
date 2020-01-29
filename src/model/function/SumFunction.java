package edu.cs3500.spreadsheets.model.function;

import java.util.List;

import edu.cs3500.spreadsheets.model.SpreadSheetModel;
import edu.cs3500.spreadsheets.model.formula.DoubleFormula;
import edu.cs3500.spreadsheets.model.formula.FormulaFunction;
import edu.cs3500.spreadsheets.model.formula.FormulaInterface;

/**
 * This function represents the sum function that adds its arguments and evaluates to a
 * DoubleFormula, if arguments are not doubles, it treats them like the double 0.0 .
 */
public class SumFunction extends FormulaFunction {

  /**
   * Constructs a SumFunction, it has a list of arguments that it can evaluate.
   *
   * @param arguments the list of arguments (list of FormulaInterface)
   */
  public SumFunction(List<FormulaInterface> arguments) {
    super(arguments);
  }

  @Override
  public DoubleFormula evaluate(SpreadSheetModel model) {
    double sum = 0;
    for (FormulaInterface x : this.arguments) {
      sum += x.accept(new SumVisitor(model));
    }
    return new DoubleFormula(sum);
  }


}
