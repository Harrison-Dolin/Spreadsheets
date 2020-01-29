package edu.cs3500.spreadsheets.model.cell;

import java.util.Objects;
import java.util.Stack;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.FormulaSexpVisitor;
import edu.cs3500.spreadsheets.model.SpreadSheetModel;
import edu.cs3500.spreadsheets.model.formula.FormulaInterface;
import edu.cs3500.spreadsheets.model.formula.StringFormula;
import edu.cs3500.spreadsheets.model.formula.ValueFormula;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Represents a cell in a spreadsheet. This cell saves the original user input and the parsed input
 * it saves the evaluated input as a cash so as not to reevaluate it if it had already been
 * evaluated and a cell it depends on to evaluate hasn't been changed. It saves its spreadsheet to
 * keep track of such changes and its coordinates.
 */
public class Cell implements CellInterface {

  private final String userInput;
  private final FormulaInterface parsedInput;
  private ValueFormula evaluatedInput;
  private SpreadSheetModel model;
  private Coord coordinate;
  private Boolean dependsOnCycle;
  private boolean error;


  /**
   * Constructs a cell.
   *
   * @param userInput  the String that the user inputs into the cell
   * @param coordinate the coordinate of this cell
   * @param model      the spreadsheet that this cell belongs to
   */
  public Cell(String userInput, Coord coordinate, SpreadSheetModel model) {
    this.userInput = userInput;
    this.model = model;
    this.coordinate = coordinate;
    FormulaInterface temp;
    try {
      temp = this.getAFormula();
    } catch (Exception e) {
      this.error = true;
      temp = null;
    }

    this.parsedInput = temp;

  }

  /**
   * Returns the correct new formula based on the type of input given. Transform user input to Sexp
   * (after parsing) then return a Formula representing that input, different formulas depending on
   * the input type.
   *
   * @return the correct formula based on its type of contents
   */
  private FormulaInterface getAFormula() {
    Sexp parsedContents;
    if (userInput.length() == 0) {
      return new StringFormula("");
    } else if (this.userInput.charAt(0) == '=') { //if the contents can be evaluated
      parsedContents = Parser.parse(this.userInput.substring(1));
    } else {
      parsedContents = Parser.parse(this.userInput);
    }
    return parsedContents.accept(new FormulaSexpVisitor(this.model));
  }


  /**
   * Gets the contents of this cell that the user inputted.
   *
   * @return a string representing the input the user placed into the cell
   */
  @Override
  public String getOriginalInput() {
    return this.userInput;
  }

  public String evaluatedInputAsString() {
    return this.model.getEvaluatedContentsAsString(coordinate);
  }

  /**
   * Return the evaluated contents of this cell as a ValueFormula, does not re-compute the formula
   * if it had been computed before and the cells it depends on to compute haven't changed.
   *
   * @return ValueFormula representing the evaluated input of this cell
   */
  @Override
  public ValueFormula evaluateCell() {
    if (this.evaluatedInput != null) { //If we already evaluated this, return cashed value
      return this.evaluatedInput;
    }
    if (this.parsedInput == null || this.error) { //if there are no contents in the cell
      throw new IllegalArgumentException("This cell is empty or has an error");
    } else {
      if (this.depOnCycle(new Stack<Coord>())) { //check if there's a cyclical reference
        throw new IllegalArgumentException("Cyclical reference, cannot evaluate");
      }
      this.evaluatedInput = this.parsedInput.evaluate(this.model); //saves the last computed input
      return this.evaluatedInput;
    }
  }

  /**
   * Returns true if this cell depends on a cycle.
   *
   * @return true if there is a cyclical reference, false otherwise.
   */
  @Override
  public boolean depOnCycle(Stack<Coord> seen) {
    if (this.parsedInput == null) { //if the cells contents are empty
      return false;
    }
    if (this.dependsOnCycle != null) {
      return this.dependsOnCycle;
    }
    seen.push(this.coordinate);
    boolean depOnCycle = this.parsedInput.depHelp(seen, this.model);
    seen.pop();
    return depOnCycle;
  }


  /**
   * Resets the evaluated input that is saved as a cash.
   */
  public void resetEvaluatedInput() {
    this.evaluatedInput = null;
  }

  public void resetCycleCash() {
    this.dependsOnCycle = null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cell cell = (Cell) o;
    return this.userInput.equals(cell.userInput)
            && this.coordinate.equals(cell.coordinate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.userInput, this.coordinate);
  }
}
