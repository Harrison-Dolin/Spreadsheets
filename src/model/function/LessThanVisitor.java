package edu.cs3500.spreadsheets.model.function;

import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.SpreadSheetModel;
import edu.cs3500.spreadsheets.model.formula.FormulaInterface;
import edu.cs3500.spreadsheets.model.formula.FormulaVisitor;
import edu.cs3500.spreadsheets.model.formula.IFormulaFunction;

/**
 * An abstracted function object for processing less than formulas.
 * A visitor that visits formulas (all types) and helps them evaluate to doubles so that they can be
 * checked in LessThanFunction.
 */
public class LessThanVisitor implements FormulaVisitor<Double> {
  SpreadSheetModel model;

  /**
   * Constructs a LessThanVisitor for processing LessThan formulas.
   * @param model the spreadsheet where the evaluation is happening
   */
  LessThanVisitor(SpreadSheetModel model) {
    this.model = model;
  }


  @Override
  public Double visitBoolean(Boolean b) {
    throw new IllegalArgumentException("Cannot compare a boolean");
  }

  @Override
  public Double visitDouble(double d) {
    return d;
  }

  @Override
  public Double visitString(String s) {
    throw new IllegalArgumentException("Cannot compare a string");
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
    if (evaluatedRefs.size() != 1) {
      throw new IllegalArgumentException("Invalid number of arguments");
    }
    return evaluatedRefs.get(0).accept(this);
  }
}
