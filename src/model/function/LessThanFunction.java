package edu.cs3500.spreadsheets.model.function;

import java.util.List;

import edu.cs3500.spreadsheets.model.SpreadSheetModel;
import edu.cs3500.spreadsheets.model.formula.BooleanFormula;
import edu.cs3500.spreadsheets.model.formula.FormulaFunction;
import edu.cs3500.spreadsheets.model.formula.FormulaInterface;

/**
 * This function represents the less than function ( < ) that checks if one formula is less than
 * another. It has a list of arguments which should contain two arguments which
 * it evaluates to a BooleanFormula.
 */
public class LessThanFunction extends FormulaFunction {


  /**
   * Constructs a LessThanFunction, it has a list of arguments that it can evaluate.
   * @param arguments the list of arguments (list of FormulaInterface)
   */
  public LessThanFunction(List<FormulaInterface> arguments) {
    super(arguments);
  }


  @Override
  public BooleanFormula evaluate(SpreadSheetModel model) {
    if (this.arguments.size() != 2) {
      throw new IllegalArgumentException("Cannot compare, wrong number of arguments.");
    } else {
      return new BooleanFormula(arguments.get(0).accept(new LessThanVisitor(model))
              < arguments.get(1).accept(new LessThanVisitor(model)));
    }
  }

}
