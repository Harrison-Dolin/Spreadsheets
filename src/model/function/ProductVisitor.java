package edu.cs3500.spreadsheets.model.function;

import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.SpreadSheetModel;
import edu.cs3500.spreadsheets.model.formula.FormulaInterface;
import edu.cs3500.spreadsheets.model.formula.FormulaVisitor;
import edu.cs3500.spreadsheets.model.formula.IFormulaFunction;

/**
 *  An abstracted function object for processing product formulas.
 *  A visitor that visits formulas (all types) and helps them evaluate to doubles so that they
 *  can be multiplied in ProductFunction.
 *  If argument is a double, leaves it the same, if it is a string or
 *  boolean it becomes 1.0, functions and references are first evaluated then visited again until
 *  they are values and treated the way values are (described above: double, string, or boolean).
 */
public class ProductVisitor implements FormulaVisitor<Double> {
  SpreadSheetModel model;

  /**
   * Constructs a ProductVisitor for processing Product formulas.
   * @param model the spreadsheet where the evaluation is happening
   */
  ProductVisitor(SpreadSheetModel model) {
    this.model = model;
  }


  @Override
  public Double visitBoolean(Boolean b) {
    return 1.0;
  }

  @Override
  public Double visitDouble(double d) {
    return d;
  }

  @Override
  public Double visitString(String s) {
    return 1.0;
  }

  @Override
  public Double visitFunction(IFormulaFunction func) {
    return func.evaluate(this.model).accept(this);
  }

  @Override
  public Double visitReference(List<Coord> reference) {
    List<FormulaInterface> evaluatedRefs = new ArrayList<>();
    for (Coord c : reference) {
      evaluatedRefs.add(this.model.evaluatedContents(c));
    }
    return this.visitFunction(new ProductFunction(evaluatedRefs));
  }
}
