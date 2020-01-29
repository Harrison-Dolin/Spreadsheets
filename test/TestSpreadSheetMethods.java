import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Stack;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.SpreadSheetModel;
import edu.cs3500.spreadsheets.model.WorksheetBuilder;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.model.cell.Cell;
import edu.cs3500.spreadsheets.model.cell.CellInterface;
import edu.cs3500.spreadsheets.model.formula.BooleanFormula;
import edu.cs3500.spreadsheets.model.formula.DoubleFormula;
import edu.cs3500.spreadsheets.model.formula.StringFormula;

import static org.junit.Assert.assertEquals;

/**
 * This is where the tests for SpreadSheet's methods are.
 */
public class TestSpreadSheetMethods {

  @Test
  public void testEquals() throws FileNotFoundException {
    SpreadSheetModel s1 = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test1.gOOD"));
    SpreadSheetModel s2 = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test2.gOOD"));
    SpreadSheetModel s3 = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test1.gOOD"));
    SpreadSheetModel sEmpty = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/emptyFile.gOOD"));
    SpreadSheetModel s2wMoreEmpty = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test2ButMoreEmptyCells.gOOD"));
    assertEquals(false, s1.equals(s2));
    assertEquals(true, s1.equals(s3));
    assertEquals(false, s1 == null);
    assertEquals(false, s1.equals(sEmpty));
    assertEquals(true, s2.equals(s2wMoreEmpty));
  }

  @Test
  public void testEquals1() throws FileNotFoundException {
    SpreadSheetModel s1 = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testEquals1.gOOD"));
    SpreadSheetModel s3 = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testEquals1.gOOD"));
    SpreadSheetModel s2 = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testEquals2.gOOD"));
    s2.deleteCellContents(new Coord(2,1));
    assertEquals(true, s1.equals(s2));
  }

  @Test
  public void testFile1() throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test1.gOOD"));
    assertEquals(new DoubleFormula(5),
            s.evaluatedContents(new Coord(1, 2)));
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
  public void testFile3() throws FileNotFoundException {
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
  public void testSpreadSheetCellOriginalContents() throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test2.gOOD"));
    assertEquals("=10",
            s.cellOriginalContents(new Coord(1, 1)));
    assertEquals("=10",
            s.cellOriginalContents(new Coord(1, 2)));
    assertEquals("=(SUM A1 A2)", s.cellOriginalContents(new Coord(1,3)));
    assertEquals("=(PRODUCT A1 A2)", s.cellOriginalContents(new Coord(1,4)));
    assertEquals("5", s.cellOriginalContents(new Coord(1,5)));
    assertEquals("\"cat\"", s.cellOriginalContents(new Coord(1,6)));
    assertEquals("=A5", s.cellOriginalContents(new Coord(1,7)));
    assertEquals("true", s.cellOriginalContents(new Coord(1,8)));
  }

  @Test
  public void testSpreadSheetEvaluatedContents() throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test2.gOOD"));
    assertEquals(new DoubleFormula(10),
            s.evaluatedContents(new Coord(1, 1)));
    assertEquals(new DoubleFormula(10), s.evaluatedContents(new Coord(1,2)));
    assertEquals(new DoubleFormula(20), s.evaluatedContents(new Coord(1,3)));
    assertEquals(new DoubleFormula(100), s.evaluatedContents(new Coord(1,4)));
    assertEquals(new DoubleFormula(5), s.evaluatedContents(new Coord(1,5)));
    assertEquals(new StringFormula("cat"), s.evaluatedContents(new Coord(1,6)));
    assertEquals(new DoubleFormula(5), s.evaluatedContents(new Coord(1,7)));
    assertEquals(new BooleanFormula(true), s.evaluatedContents(new Coord(1,8)));
    assertEquals(new DoubleFormula(125), s.evaluatedContents(new Coord(1, 9)));
  }


  @Test
  public void testSpreadSheetGetCell() throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test2.gOOD"));
    assertEquals(new Cell( "=10", new Coord(1,1), s),
            s.getCell(new Coord(1, 1)));

  }

  @Test
  public void testCellGetOriginalInput() throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test2.gOOD"));
    assertEquals("=10", new Cell("=10",
            new Coord(1,1), s).getOriginalInput());
    assertEquals("=(SUM A1 A2)", new Cell("=(SUM A1 A2)",
            new Coord(1,3), s).getOriginalInput());
    assertEquals("\"cat\"", new Cell("\"cat\"",
            new Coord(1,6), s).getOriginalInput());
    assertEquals("true", new Cell("true",
            new Coord(1,8), s).getOriginalInput());
  }


  @Test
  public void testCellResetEvaluatedInput() throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test2.gOOD"));
    CellInterface a1 = new Cell("=10",
            new Coord(1,1), s);
    assertEquals("=10", a1.getOriginalInput());
    assertEquals(new DoubleFormula(10), a1.evaluateCell());
    a1.resetEvaluatedInput();
    assertEquals("=(SUM A1 A2)", new Cell("=(SUM A1 A2)",
            new Coord(1,3), s).getOriginalInput());
    assertEquals("\"cat\"", new Cell("\"cat\"",
            new Coord(1,6), s).getOriginalInput());
    assertEquals("true", new Cell("true",
            new Coord(1,8), s).getOriginalInput());
  }

  @Test
  public void testCellDepOnCycle() throws FileNotFoundException {
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testCyclicReference2.gOOD"));
    CellInterface a1 = s.getCell(new Coord(1,1));
    CellInterface b1 = s.getCell(new Coord(2, 1));
    CellInterface c1 = s.getCell(new Coord(3, 1));
    CellInterface d1 = s.getCell(new Coord(4, 1));
    assertEquals(false, a1.depOnCycle(new Stack<Coord>()));
    assertEquals(false, b1.depOnCycle(new Stack<Coord>()));
    assertEquals(false, c1.depOnCycle(new Stack<Coord>()));
    assertEquals(false, d1.depOnCycle(new Stack<Coord>()));

    CellInterface a2 = s.getCell(new Coord(1,2));
    CellInterface b3 = s.getCell(new Coord(2,3));
    CellInterface c2 = s.getCell(new Coord(3,2));
    CellInterface b4 = s.getCell(new Coord(2,4));
    CellInterface b5 = s.getCell(new Coord(2,5));
    assertEquals(false, a2.depOnCycle(new Stack<Coord>()));
    assertEquals(true, b3.depOnCycle(new Stack<Coord>()));
    assertEquals(false, c2.depOnCycle(new Stack<Coord>()));
    assertEquals(true, b4.depOnCycle(new Stack<Coord>()));
    assertEquals(true, b5.depOnCycle(new Stack<Coord>()));

    //checking again
    assertEquals(false, a2.depOnCycle(new Stack<Coord>()));
    assertEquals(true, b3.depOnCycle(new Stack<Coord>()));
    assertEquals(false, c2.depOnCycle(new Stack<Coord>()));
    assertEquals(true, b4.depOnCycle(new Stack<Coord>()));
    assertEquals(true, b5.depOnCycle(new Stack<Coord>()));
  }




}
