package edu.cs3500.spreadsheets.model.function;

import java.util.List;

import edu.cs3500.spreadsheets.model.SpreadSheetModel;
import edu.cs3500.spreadsheets.model.formula.DoubleFormula;
import edu.cs3500.spreadsheets.model.formula.FormulaFunction;
import edu.cs3500.spreadsheets.model.formula.FormulaInterface;

/**
 * This function represents the product function that multiplies its arguments and evaluates
 * to a DoubleFormula, if arguments are not doubles, it treats them like the double 1.0 .
 */
public class ProductFunction extends FormulaFunction {

  /**
   * Constructs a ProductFunction, it has a list of arguments that it can evaluate.
   * @param arguments the list of arguments (list of FormulaInterface)
   */
  public ProductFunction(List<FormulaInterface> arguments) {
    super(arguments);
  }

  @Override
  public DoubleFormula evaluate(SpreadSheetModel model) {
    double prod = 1;
    for (FormulaInterface x : arguments) {
      prod *= x.accept(new ProductVisitor(model));
    }
    return new DoubleFormula(prod);
  }
}
