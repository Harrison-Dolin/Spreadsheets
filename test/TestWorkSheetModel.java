import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.SpreadSheetModel;
import edu.cs3500.spreadsheets.model.WorksheetBuilder;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.model.formula.BooleanFormula;
import edu.cs3500.spreadsheets.model.formula.DoubleFormula;
import edu.cs3500.spreadsheets.model.formula.StringFormula;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * This is where the tests for the SpreadSheet are.
 */
public class TestWorkSheetModel {

  @Test
  public void testFile1AllCellsAccountedFor() throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test1.gOOD"));
    assertEquals(new DoubleFormula(5),
            s.evaluatedContents(new Coord(1, 1)));
    assertEquals(new DoubleFormula(5),
            s.evaluatedContents(new Coord(1, 2)));
  }

  @Test
  public void testVeryLargeCellIndex() throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testLarge.gOOD"));
    assertEquals(new DoubleFormula(10), s.evaluatedContents(new Coord(1, 26)));
  }

  @Test
  public void testVeryLargeCellIndexReference() throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testLarge.gOOD"));
    assertEquals(new DoubleFormula(10), s.evaluatedContents(new Coord(1, 26)));
  }

  @Test
  public void testVeryLargeCellIndexSum() throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testLarge.gOOD"));
    s.editCellContents(new Coord(1, 22), "=(SUM AA1 4)");
    assertEquals(new DoubleFormula(14), s.evaluatedContents(new Coord(1, 22)));
  }

  @Test
  public void testFileEmpty() throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/emptyTest.gOOD"));
    assertNull(s.getCell(new Coord(1, 1)));

  }

  @Test
  public void testFile2Sum() throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test2.gOOD"));
    assertEquals(new DoubleFormula(20), s.evaluatedContents(new Coord(1, 3)));
  }

  @Test
  public void testFile2Product() throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test2.gOOD"));
    assertEquals(new DoubleFormula(100),
            s.evaluatedContents(new Coord(1, 4)));
  }

  @Test
  public void testFile3ReferencesToOtherCells() throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test3.gOOD"));
    assertEquals(new DoubleFormula(144), s.evaluatedContents(new Coord(1, 2)));
    assertEquals(new DoubleFormula(256), s.evaluatedContents(new Coord(2, 2)));
    assertEquals(new DoubleFormula(400), s.evaluatedContents(new Coord(1, 3)));
    assertEquals(new BooleanFormula(false),
            s.evaluatedContents(new Coord(2, 3)));
    assertEquals(new DoubleFormula(400), s.evaluatedContents(new Coord(1, 4)));
  }

  @Test
  public void testFibonacci() throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/fib15.gOOD"));
    assertEquals(new DoubleFormula(610), s.evaluatedContents(new Coord(1, 15)));
  }


  @Test
  public void testSpreadSheetEditCellContentsDouble() throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test1.gOOD"));
    assertEquals(new DoubleFormula(5),
            s.evaluatedContents(new Coord(1, 2)));
    s.editCellContents(new Coord(1,2), "33");
    assertEquals(new DoubleFormula(33), s.evaluatedContents(new Coord(1,2)));
  }

  @Test
  public void testSpreadSheetEditCellContentsBool() throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test1.gOOD"));
    assertEquals(new DoubleFormula(5),
            s.evaluatedContents(new Coord(1, 2)));
    s.editCellContents(new Coord(1,2), "true");
    assertEquals(new BooleanFormula(true), s.evaluatedContents(new Coord(1,2)));
  }

  @Test
  public void testSpreadSheetEditCellContentsString() throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test1.gOOD"));
    assertEquals(new DoubleFormula(5),
            s.evaluatedContents(new Coord(1, 2)));
    s.editCellContents(new Coord(1,2), "\"mactuallyreallysad\"");
    assertEquals(new StringFormula("mactuallyreallysad"),
            s.evaluatedContents(new Coord(1,2)));
  }

  @Test
  public void testSpreadSheetEditCellContentsSumFunction() throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test1.gOOD"));
    assertEquals(new DoubleFormula(5),
            s.evaluatedContents(new Coord(1, 2)));
    s.editCellContents(new Coord(1,2), "=(SUM 4 5)");
    assertEquals(new DoubleFormula(9), s.evaluatedContents(new Coord(1,2)));
  }

  @Test
  public void testSpreadSheetEditCellContentsSumFunctionWithReference()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test2.gOOD"));
    assertEquals(new DoubleFormula(10),
            s.evaluatedContents(new Coord(1, 1)));
    s.editCellContents(new Coord(1,2), "=(SUM A1 5)");
    assertEquals(new DoubleFormula(15), s.evaluatedContents(new Coord(1,2)));
  }

  @Test
  public void testSpreadSheetEditCellContentsSumFunctionTwoReferences()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test2.gOOD"));
    assertEquals(new DoubleFormula(10),
            s.evaluatedContents(new Coord(1, 2)));
    s.editCellContents(new Coord(1,3), "=(SUM A1 A2)");
    assertEquals(new DoubleFormula(20), s.evaluatedContents(new Coord(1,3)));
  }

  @Test
  public void testSpreadSheetEditCellContentsSumFunctionSameReferences()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test2.gOOD"));
    assertEquals(new DoubleFormula(10),
            s.evaluatedContents(new Coord(1, 2)));
    s.editCellContents(new Coord(1,3), "=(SUM A1 A1)");
    assertEquals(new DoubleFormula(20), s.evaluatedContents(new Coord(1,3)));
  }

  @Test
  public void testSpreadSheetEditCellContentsSumFunctionReferenceBlock()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test2.gOOD"));
    assertEquals(new DoubleFormula(10),
            s.evaluatedContents(new Coord(1, 1)));
    s.editCellContents(new Coord(1,3), "=(SUM A1:A2)");
    assertEquals(new DoubleFormula(20), s.evaluatedContents(new Coord(1,3)));
  }

  @Test
  public void testSpreadSheetEditCellContentsSumFunctionOneInput()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test1.gOOD"));
    assertEquals(new DoubleFormula(5),
            s.evaluatedContents(new Coord(1, 2)));
    s.editCellContents(new Coord(1,2), "=(SUM 5)");
    assertEquals(new DoubleFormula(5), s.evaluatedContents(new Coord(1,2)));
  }

  @Test
  public void testSpreadSheetEditCellContentsSumFunctionBadFormed2()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test1.gOOD"));
    assertEquals(new DoubleFormula(5),
            s.evaluatedContents(new Coord(1, 2)));
    s.editCellContents(new Coord(1,2), "=(SUM)");
  }

  @Test
  public void testSpreadSheetEditCellContentsSumFunctionInvalidInputs()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test1.gOOD"));
    assertEquals(new DoubleFormula(5),
            s.evaluatedContents(new Coord(1, 2)));
    s.editCellContents(new Coord(1,2), "=(SUM A1 hello)");
  }

  @Test
  public void testSpreadSheetEditCellContentsSumFunctionInvalidInputs2()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test1.gOOD"));
    assertEquals(new DoubleFormula(5),
            s.evaluatedContents(new Coord(1, 2)));
    s.editCellContents(new Coord(1,2), "=(SUM A1 \"true\")");
    s.evaluatedContents(new Coord(1, 2));
  }

  ////////////////////////////////////////////////

  @Test
  public void testSpreadSheetEditCellContentsProdcutFunction() throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test1.gOOD"));
    assertEquals(new DoubleFormula(5),
            s.evaluatedContents(new Coord(1, 2)));
    s.editCellContents(new Coord(1,2), "=(PRODUCT 4 5)");
    assertEquals(new DoubleFormula(20), s.evaluatedContents(new Coord(1,2)));
  }

  @Test
  public void testSpreadSheetEditCellContentsProductFunctionWithReference()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test2.gOOD"));
    assertEquals(new DoubleFormula(10),
            s.evaluatedContents(new Coord(1, 1)));
    s.editCellContents(new Coord(1,2), "=(PRODUCT A1 5)");
    assertEquals(new DoubleFormula(50), s.evaluatedContents(new Coord(1,2)));
  }

  @Test
  public void testSpreadSheetEditCellContentsProductFunctionTwoReferences()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test2.gOOD"));
    assertEquals(new DoubleFormula(10),
            s.evaluatedContents(new Coord(1, 2)));
    s.editCellContents(new Coord(1,3), "=(PRODUCT A1 A2)");
    assertEquals(new DoubleFormula(100), s.evaluatedContents(new Coord(1,3)));
  }

  @Test
  public void testSpreadSheetEditCellContentsProductFunctionSameReferences()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test2.gOOD"));
    assertEquals(new DoubleFormula(10),
            s.evaluatedContents(new Coord(1, 2)));
    s.editCellContents(new Coord(1,3), "=(PRODUCT A1 A1)");
    assertEquals(new DoubleFormula(100), s.evaluatedContents(new Coord(1,3)));
  }

  @Test
  public void testSpreadSheetEditCellContentsProductFunctionReferenceBlock()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test2.gOOD"));
    assertEquals(new DoubleFormula(10),
            s.evaluatedContents(new Coord(1, 1)));
    s.editCellContents(new Coord(1,3), "=(PRODUCT A1:A2)");
    assertEquals(new DoubleFormula(100), s.evaluatedContents(new Coord(1,3)));
  }

  @Test
  public void testSpreadSheetEditCellContentsProductFunctionOneInput()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test1.gOOD"));
    assertEquals(new DoubleFormula(5),
            s.evaluatedContents(new Coord(1, 2)));
    s.editCellContents(new Coord(1,2), "=(PRODUCT 5)");
    assertEquals(new DoubleFormula(5), s.evaluatedContents(new Coord(1, 2)));
  }

  @Test
  public void testSpreadSheetEditCellContentsProductFunctionBadFormed2()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test1.gOOD"));
    assertEquals(new DoubleFormula(5),
            s.evaluatedContents(new Coord(1, 2)));
    s.editCellContents(new Coord(1,2), "=(PRODUCT)");
  }


  @Test (expected = IllegalArgumentException.class)
  public void testSpreadSheetEditCellContentsProductFunctionBadReferenceGroup()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test1.gOOD"));
    assertEquals(new DoubleFormula(5),
            s.evaluatedContents(new Coord(1, 2)));
    s.editCellContents(new Coord(1,2), "=(PRODUCT A1:A8)");
    s.evaluatedContents(new Coord(1, 2));
  }

  @Test
  public void testSpreadSheetEditCellContentsProductInvalidInput()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test1.gOOD"));
    assertEquals(new DoubleFormula(5),
            s.evaluatedContents(new Coord(1, 2)));
    s.editCellContents(new Coord(1,2), "=(PRODUCT A1 giraffe)");
  }

  @Test
  public void testSpreadSheetEditCellContentsProductInvalidInput2()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test1.gOOD"));
    assertEquals(new DoubleFormula(5),
            s.evaluatedContents(new Coord(1, 2)));
    s.editCellContents(new Coord(1,2), "=(PRODUCT A1 \"true\")");
    s.evaluatedContents(new Coord(1, 2));
  }
  ////////////////////////////////////////////////////////

  @Test
  public void testSpreadSheetEditCellContentsConcatFunction()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testConcat.gOOD"));
    assertEquals(new StringFormula("cat"),
            s.evaluatedContents(new Coord(1, 1)));
    s.editCellContents(new Coord(1,1), "=(CONCAT \"wolf\" \"pats\")");
    assertEquals(new StringFormula("wolfpats"), s.evaluatedContents(new Coord(1,1)));
  }

  @Test
  public void testSpreadSheetEditCellContentsConcatFunctionWithReference()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testConcat.gOOD"));
    assertEquals(new StringFormula("cat"),
            s.evaluatedContents(new Coord(1, 1)));
    s.editCellContents(new Coord(1,2), "=(CONCAT A1 \"g\")");
    assertEquals(new StringFormula("catg"), s.evaluatedContents(new Coord(1,2)));
  }

  @Test
  public void testSpreadSheetEditCellContentsConcatFunctionTwoReferences()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testConcat.gOOD"));
    assertEquals(new StringFormula("cat"),
            s.evaluatedContents(new Coord(1, 1)));
    s.editCellContents(new Coord(2,1), "=(CONCAT A1 A2)");
    assertEquals(new StringFormula("catdog"), s.evaluatedContents(new Coord(2,1)));
  }

  @Test
  public void testSpreadSheetEditCellContentsConcatFunctionSameReferences()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testConcat.gOOD"));
    assertEquals(new StringFormula("cat"),
            s.evaluatedContents(new Coord(1, 1)));
    s.editCellContents(new Coord(2,1), "=(CONCAT A1 A1)");
    assertEquals(new StringFormula("catcat"), s.evaluatedContents(new Coord(2,1)));
  }

  @Test
  public void testSpreadSheetEditCellContentsConcatFunctionReferenceBlock()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testConcat.gOOD"));
    assertEquals(new StringFormula("cat"),
            s.evaluatedContents(new Coord(1, 1)));
    s.editCellContents(new Coord(2,1), "=(CONCAT A1:A2)");
    assertEquals(new StringFormula("catdog"), s.evaluatedContents(new Coord(2,1)));
  }

  @Test
  public void testSpreadSheetEditCellContentsConcatFunctionOneInput()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testConcat.gOOD"));
    assertEquals(new StringFormula("cat"),
            s.evaluatedContents(new Coord(1, 1)));
    s.editCellContents(new Coord(2,2), "=(CONCAT 5)");
    assertEquals(new StringFormula("5.0"), s.evaluatedContents(new Coord(2, 2)));
  }

  @Test
  public void testSpreadSheetEditCellContentsConcatFunctionBadFormed2()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testConcat.gOOD"));
    assertEquals(new StringFormula("cat"),
            s.evaluatedContents(new Coord(1, 1)));
    s.editCellContents(new Coord(2,2), "=(CONCAT)");
  }

  @Test
  public void testSpreadSheetEditCellContentsConcatFunctionBadReference()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testConcat.gOOD"));
    assertEquals(new StringFormula("cat"),
            s.evaluatedContents(new Coord(1, 1)));
    s.editCellContents(new Coord(2,2), "=(CONCAT A4 hi)");
  }


  @Test
  public void testSpreadSheetEditCellContentsLessThanFunction() throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testLessThan.gOOD"));
    assertEquals(new DoubleFormula(3),
            s.evaluatedContents(new Coord(1, 2)));
    assertEquals(new BooleanFormula(true),
            s.evaluatedContents(new Coord(2, 2)));
    s.editCellContents(new Coord(2,2), "=(< 4 5)");
    assertEquals(new BooleanFormula(true), s.evaluatedContents(new Coord(2,2)));
  }


  @Test
  public void testSpreadSheetEditCellContentsLessThanFunctionTwoReferences()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testLessThan.gOOD"));
    assertEquals(new DoubleFormula(3),
            s.evaluatedContents(new Coord(1, 2)));
    assertEquals(new BooleanFormula(true),
            s.evaluatedContents(new Coord(2, 2)));
    s.editCellContents(new Coord(2,2), "=(< A2 A1)");
    assertEquals(new BooleanFormula(false), s.evaluatedContents(new Coord(2,2)));
  }

  @Test
  public void testSpreadSheetEditCellContentsLessThanFunctionSameReferences()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testLessThan.gOOD"));
    assertEquals(new DoubleFormula(3),
            s.evaluatedContents(new Coord(1, 2)));
    assertEquals(new BooleanFormula(true),
            s.evaluatedContents(new Coord(2, 2)));
    s.editCellContents(new Coord(2,2), "=(< A2 A2)");
    assertEquals(new BooleanFormula(false), s.evaluatedContents(new Coord(2,2)));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSpreadSheetEditCellContentsLessThanFunctionReferenceBlock()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testLessThan.gOOD"));
    assertEquals(new DoubleFormula(3),
            s.evaluatedContents(new Coord(1, 2)));
    assertEquals(new BooleanFormula(true),
            s.evaluatedContents(new Coord(2, 2)));
    s.editCellContents(new Coord(2,2), "=(< A1:A2)");
    assertEquals(new BooleanFormula(true), s.evaluatedContents(new Coord(2,2)));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSpreadSheetEditCellContentsLessThanFunctionBadFormed()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testLessThan.gOOD"));
    assertEquals(new DoubleFormula(3),
            s.evaluatedContents(new Coord(1, 2)));
    assertEquals(new BooleanFormula(true),
            s.evaluatedContents(new Coord(2, 2)));
    s.editCellContents(new Coord(2,2), "=(< 5)");
    s.evaluatedContents(new Coord(2, 2));
  }

  @Test
  public void testSpreadSheetEditCellContentsLessThanFunctionBadFormed2()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testLessThan.gOOD"));
    assertEquals(new DoubleFormula(3),
            s.evaluatedContents(new Coord(1, 2)));
    assertEquals(new BooleanFormula(true),
            s.evaluatedContents(new Coord(2, 2)));
    s.editCellContents(new Coord(2,2), "=(<)");
  }

  @Test
  public void testSpreadSheetEditCellContentsLessThanFunctionBadReferenceGroup()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testLessThan.gOOD"));
    assertEquals(new DoubleFormula(3),
            s.evaluatedContents(new Coord(1, 2)));
    assertEquals(new BooleanFormula(true),
            s.evaluatedContents(new Coord(2, 2)));
    s.editCellContents(new Coord(2,2), "=(< A12:A2)");
  }

  @Test
  public void testSpreadSheetEditCellContentsLessThanInvalidInput()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testLessThan.gOOD"));
    assertEquals(new DoubleFormula(3),
            s.evaluatedContents(new Coord(1, 2)));
    assertEquals(new BooleanFormula(true),
            s.evaluatedContents(new Coord(2, 2)));
    s.editCellContents(new Coord(2,2), "=(< A1 giraffe)");
  }

  @Test
  public void testSpreadSheetEditCellContentsLessThanInvalidInput2()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testLessThan.gOOD"));
    assertEquals(new DoubleFormula(3),
            s.evaluatedContents(new Coord(1, 2)));
    assertEquals(new BooleanFormula(true),
            s.evaluatedContents(new Coord(2, 2)));
    s.editCellContents(new Coord(2,2), "=(PRODUCT A1 \"true\")");
    s.evaluatedContents(new Coord(2, 2));
  }

  @Test
  public void testSpreadSheetEditCellContentsLessThanInvalidInput3()
          throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testLessThan.gOOD"));
    assertEquals(new DoubleFormula(3),
            s.evaluatedContents(new Coord(1, 2)));
    assertEquals(new BooleanFormula(true),
            s.evaluatedContents(new Coord(2, 2)));
    s.editCellContents(new Coord(2,2), "=(PRODUCT hi true)");
  }
}