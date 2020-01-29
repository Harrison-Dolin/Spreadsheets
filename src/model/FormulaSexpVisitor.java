package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.cs3500.spreadsheets.model.formula.BooleanFormula;
import edu.cs3500.spreadsheets.model.formula.DoubleFormula;
import edu.cs3500.spreadsheets.model.formula.FormulaFunction;
import edu.cs3500.spreadsheets.model.formula.FormulaInterface;
import edu.cs3500.spreadsheets.model.formula.ReferenceFormula;
import edu.cs3500.spreadsheets.model.formula.StringFormula;
import edu.cs3500.spreadsheets.model.function.ConcatFunction;
import edu.cs3500.spreadsheets.model.function.LessThanFunction;
import edu.cs3500.spreadsheets.model.function.ProductFunction;
import edu.cs3500.spreadsheets.model.function.SumFunction;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

/**
 * A function object that visits an Sexp and returns a FormulaInterface.
 */
public class FormulaSexpVisitor implements SexpVisitor<FormulaInterface> {

  SpreadSheetModel model;

  public FormulaSexpVisitor(SpreadSheetModel model) {
    this.model = model;
  }

  @Override
  public BooleanFormula visitBoolean(boolean b) {
    return new BooleanFormula(b);
  }

  @Override
  public DoubleFormula visitNumber(double d) {
    return new DoubleFormula(d);
  }

  @Override
  public FormulaInterface visitSymbol(String s) {
    if (this.getColNum(s) != -1) {
      return new ReferenceFormula(this.model.getCoordsInCol(this.getColNum(s)), true);
    }
    //Structural invariant: will never get to this method if it is the first argument in an SList
    return new ReferenceFormula(this.getList(s), false);
  }

  @Override
  public StringFormula visitString(String s) {
    return new StringFormula(s);
  }


  /**
   * Visits SList and turns it into a formula, represented as a FormulaInterface.
   *
   * @param l the contents of the list (not yet visited)
   * @return a FormulaInterface, which is the SList represented as a formula
   * @throws IllegalArgumentException if function is invalid or not supported.
   */
  @Override
  public FormulaInterface visitSList(List<Sexp> l) {
    // function first arg is name and rest is arguments
    //if size is 1, pass in this visitor
    //else, check 1st thing in list then visit the rest
    if (l.size() == 1) {
      return l.get(0).accept(this);
    } else {
      List<Sexp> lCopy = new ArrayList<Sexp>(l);
      Sexp first = lCopy.get(0);
      lCopy.remove(0);
      List<FormulaInterface> arguments = new ArrayList<>();
      for (Sexp s : lCopy) {
        arguments.add(s.accept(this));
      }
      return this.getFunction(first, arguments);
    }

  }

  /**
   * The correct function or throw an error if it is invalid, assuming that this is the first
   * argument in a SList representing a function.
   *
   * @param s the given Sexp
   * @return the correct function
   * @throws IllegalArgumentException if it is not a function name
   */
  private FormulaFunction getFunction(Sexp s, List<FormulaInterface> arguments) {
    String sxp = s.toString();
    switch (sxp) {
      case "SUM":
        return new SumFunction(arguments);
      case "PRODUCT":
        return new ProductFunction(arguments);
      case "<":
        return new LessThanFunction(arguments);
      case "CONCAT":
        return new ConcatFunction(arguments);
      default:
        throw new IllegalArgumentException("This function is not supported");
    }
  }

  /**
   * Get a list of the coordinates of the cells represented as a reference from the given string.
   *
   * @param ref a string representing a reference
   * @return a list of cell coordinates in that reference
   * @throws IllegalArgumentException if the string doesn't match the correct format of a reference
   */
  private List<Coord> getList(String ref) {
    List<Coord> references = new ArrayList<Coord>();
    int colonIndex = ref.indexOf(':');
    if (colonIndex == -1) { //no colon, so should refer to one cell
      references.add(this.getCellCoord(ref));
      return references;
    } else { //should refer to a rectangular reference
      return this.getRectangularReference(this.getCellCoord(ref.substring(0, colonIndex)),
              this.getCellCoord(ref.substring(colonIndex + 1)));
    }
  }

  /**
   * Given two Coordinates representing top left and bottom right cells, return a list of
   * Coordinates of all the cells in that rectangular reference.
   *
   * @param coord1 the coordinate of the first cell
   * @param coord2 the coordinate of the second cell
   * @return a list of Coordinates of all the cells in that rectangular reference.
   * @throws IllegalArgumentException if reference is invalid
   */
  private List<Coord> getRectangularReference(Coord coord1, Coord coord2) {
    List<Coord> references = new ArrayList<Coord>();
    //check if they make a rectangle from
    if ((coord1.col <= coord2.col) && (coord1.row <= coord2.row)) {
      for (int col = coord1.col; col <= coord2.col; col++) {
        for (int row = coord1.row; row <= coord2.row; row++) {
          references.add(new Coord(col, row));
        }
      }
      return references;
    } else {
      throw new IllegalArgumentException("Invalid reference");
    }
  }

  /**
   * Given a string that is supposed to represent one cell, get the coordinate of that cell.
   *
   * @param s string representing the cell
   * @return the cells Coordinate
   * @throws IllegalArgumentException if the string doesn't match the correct format of a reference
   */
  private Coord getCellCoord(String s) {
    String pattern = "(([a-zA-Z]+)(\\d+))";
    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(s);
    Boolean itMatches = m.matches();
    if (itMatches) {
      int col = Coord.colNameToIndex(m.group(2));
      int row = Integer.parseInt(m.group(3));
      return new Coord(col, row);
    } else {
      throw new IllegalArgumentException("Wrong reference format");
    }
  }

  /**
   * returns -1 if string is not a column reference
   * @param s
   * @return
   */
  private int getColNum(String s) {
    String pattern1 = "([a-zA-Z]+)";
    String pattern = pattern1 + ":" + pattern1;
    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(s);
    Boolean itMatches = m.matches();
    if (itMatches) {
      int col = Coord.colNameToIndex(m.group(1));
      return col;
    }
    return -1;
  }

}
