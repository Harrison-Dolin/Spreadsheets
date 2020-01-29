package edu.cs3500.spreadsheets.model.function;

import java.util.ArrayList;
import java.util.List;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.SpreadSheetModel;
import edu.cs3500.spreadsheets.model.formula.FormulaInterface;
import edu.cs3500.spreadsheets.model.formula.FormulaVisitor;
import edu.cs3500.spreadsheets.model.formula.IFormulaFunction;

/**
 * An abstracted function object for processing concat formulas.
 * A visitor that visits formulas (all types) and helps them evaluate to strings so that they can be
 * concatenated.
 */
public class ConcatVisitor implements FormulaVisitor<String> {
  SpreadSheetModel model;

  /**
   * Constructs a ConcatVisitor for processing concat formulas.
   * @param model the spreadsheet where the evaluation is happening
   */
  ConcatVisitor(SpreadSheetModel model) {
    this.model = model;
  }


  @Override
  public String visitBoolean(Boolean b) {
    return b.toString();
  }

  @Override
  public String visitDouble(double d) {
    return Double.toString(d);
  }

  @Override
  public String visitString(String s) {
    return s;
  }

  @Override
  public String visitFunction(IFormulaFunction func) {
    return func.evaluate(this.model).accept(this);
  }

  @Override
  public String visitReference(List<Coord> reference) {
    List<FormulaInterface> evaluatedRefs = new ArrayList<>();
    for (Coord c : reference) {
      evaluatedRefs.add(this.model.evaluatedContents(c));
    }
    return this.visitFunction(new ConcatFunction(evaluatedRefs));
  }
}
