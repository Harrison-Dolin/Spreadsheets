//import org.junit.Test;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import edu.cs3500.spreadsheets.model.Coord;
//import edu.cs3500.spreadsheets.model.SpreadSheetModel;
//import edu.cs3500.spreadsheets.model.WorksheetBuilder;
//import edu.cs3500.spreadsheets.model.WorksheetReader;
//import edu.cs3500.spreadsheets.model.formula.BooleanFormula;
//import edu.cs3500.spreadsheets.model.formula.DoubleFormula;
//import edu.cs3500.spreadsheets.model.formula.FormulaInterface;
//import edu.cs3500.spreadsheets.model.formula.ReferenceFormula;
//import edu.cs3500.spreadsheets.model.formula.StringFormula;
//import edu.cs3500.spreadsheets.model.function.ConcatFunction;
//import edu.cs3500.spreadsheets.model.function.LessThanFunction;
//import edu.cs3500.spreadsheets.model.function.ProductFunction;
//import edu.cs3500.spreadsheets.model.function.SumFunction;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Testing class for the functions package.
// */
//public class TestFunctionAndFormulas {
//
//  @Test
//  public void testSumFunctionEvaluate() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new DoubleFormula(3), new DoubleFormula(4)));
//    SumFunction sFunc = new SumFunction(arguments);
//    assertEquals(new DoubleFormula(7.000000), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testSumFunctionEvaluateOneArg() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new DoubleFormula(4)));
//    SumFunction sFunc = new SumFunction(arguments);
//    assertEquals(new DoubleFormula(4.000000), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testSumFunctionEvaluateNoArg() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList());
//    SumFunction sFunc = new SumFunction(arguments);
//    assertEquals(new DoubleFormula(0), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testSumFunctionEvaluateStringInput1() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new StringFormula("hello"),
//                    new DoubleFormula(4)));
//    SumFunction sFunc = new SumFunction(arguments);
//    assertEquals(new DoubleFormula(4), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testSumFunctionEvaluateStringInput2() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new DoubleFormula(3),
//                    new StringFormula("hi")));
//    SumFunction sFunc = new SumFunction(arguments);
//    assertEquals(new DoubleFormula(3), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testSumFunctionEvaluateStringInput3() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new StringFormula("hello"),
//                    new StringFormula("j")));
//    SumFunction sFunc = new SumFunction(arguments);
//    assertEquals(new DoubleFormula(0), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testSumFunctionEvaluateBooleanInput1() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new BooleanFormula(true), new DoubleFormula(4)));
//    SumFunction sFunc = new SumFunction(arguments);
//    assertEquals(new DoubleFormula(4), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testSumFunctionEvaluateBooleanInput2() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new DoubleFormula(3),
//                    new BooleanFormula(true)));
//    SumFunction sFunc = new SumFunction(arguments);
//    assertEquals(new DoubleFormula(3), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testSumFunctionEvaluateBooleanInput3() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new BooleanFormula(true),
//                    new BooleanFormula(false)));
//    SumFunction sFunc = new SumFunction(arguments);
//    assertEquals(new DoubleFormula(0), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testSumFunctionEvaluateFunction() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new ReferenceFormula(Arrays.asList(new
//                    Coord(1, 3))), new DoubleFormula(4)));
//    SumFunction sFunc = new SumFunction(arguments);
//    assertEquals(new DoubleFormula(404), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testSumFunctionEvaluateFunction2() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new DoubleFormula(3),
//                    new ReferenceFormula(Arrays.asList(new Coord(1, 3)))));
//    SumFunction sFunc = new SumFunction(arguments);
//    assertEquals(new DoubleFormula(403), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testSumFunctionEvaluateFunctionBoth() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new
//                            ReferenceFormula(Arrays.asList(new Coord(1, 3))),
//                    new ReferenceFormula(Arrays.asList(new Coord(1, 3)))));
//    SumFunction sFunc = new SumFunction(arguments);
//    assertEquals(new DoubleFormula(800), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testProductFunctionEvaluate() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new DoubleFormula(3), new DoubleFormula(4)));
//    ProductFunction sFunc = new ProductFunction(arguments);
//    assertEquals(new DoubleFormula(12), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testProductFunctionEvaluateOneArg() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new DoubleFormula(4)));
//    ProductFunction sFunc = new ProductFunction(arguments);
//    assertEquals(new DoubleFormula(4.000000), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testProductFunctionEvaluateNoArg() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList());
//    ProductFunction sFunc = new ProductFunction(arguments);
//    assertEquals(new DoubleFormula(1), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testProductFunctionEvaluateStringInput1() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new StringFormula("hello"),
//                    new DoubleFormula(4)));
//    ProductFunction sFunc = new ProductFunction(arguments);
//    assertEquals(new DoubleFormula(4), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testProductFunctionEvaluateStringInput2() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new DoubleFormula(3), new StringFormula("hi")));
//    ProductFunction sFunc = new ProductFunction(arguments);
//    assertEquals(new DoubleFormula(3), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testProductFunctionEvaluateStringInput3() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new StringFormula("hello"),
//                    new StringFormula("j")));
//    ProductFunction sFunc = new ProductFunction(arguments);
//    assertEquals(new DoubleFormula(1), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testProductFunctionEvaluateBooleanInput1() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new BooleanFormula(true),
//                    new DoubleFormula(4)));
//    ProductFunction sFunc = new ProductFunction(arguments);
//    assertEquals(new DoubleFormula(4), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testProductFunctionEvaluateBooleanInput2() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new DoubleFormula(3), new BooleanFormula(true)));
//    ProductFunction sFunc = new ProductFunction(arguments);
//    assertEquals(new DoubleFormula(3), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testProductFunctionEvaluateBooleanInput3() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new BooleanFormula(true),
//                    new BooleanFormula(false)));
//    ProductFunction sFunc = new ProductFunction(arguments);
//    assertEquals(new DoubleFormula(1), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testProductFunctionEvaluateFunction() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new ReferenceFormula(Arrays.asList(new
//                    Coord(1, 3))), new DoubleFormula(4)));
//    ProductFunction sFunc = new ProductFunction(arguments);
//    assertEquals(new DoubleFormula(1600), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testProductFunctionEvaluateFunction2() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new DoubleFormula(3),
//                    new ReferenceFormula(Arrays.asList(new Coord(1, 3)))));
//    ProductFunction sFunc = new ProductFunction(arguments);
//    assertEquals(new DoubleFormula(1200), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testSProductFunctionEvaluateFunctionBoth() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new
//                            ReferenceFormula(Arrays.asList(new Coord(1, 3))),
//                    new ReferenceFormula(Arrays.asList(new Coord(1, 3)))));
//    ProductFunction sFunc = new ProductFunction(arguments);
//    assertEquals(new DoubleFormula(160000), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testLessThanFunctionEvaluate() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new DoubleFormula(3), new DoubleFormula(4)));
//    LessThanFunction sFunc = new LessThanFunction(arguments);
//    assertEquals(new BooleanFormula(true), sFunc.evaluate(s));
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void testLessThanFunctionEvaluateOneArg() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new DoubleFormula(4)));
//    LessThanFunction sFunc = new LessThanFunction(arguments);
//    assertEquals(new BooleanFormula(true), sFunc.evaluate(s));
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void testLessThanFunctionEvaluateNoArg() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList());
//    LessThanFunction sFunc = new LessThanFunction(arguments);
//    assertEquals(new BooleanFormula(true), sFunc.evaluate(s));
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void testLessThanFunctionEvaluateStringInput1() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new StringFormula("hello"),
//                    new DoubleFormula(4)));
//    LessThanFunction sFunc = new LessThanFunction(arguments);
//    assertEquals(new BooleanFormula(true), sFunc.evaluate(s));
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void testLessThanFunctionEvaluateStringInput2() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new DoubleFormula(3), new StringFormula("hi")));
//    LessThanFunction sFunc = new LessThanFunction(arguments);
//    assertEquals(new BooleanFormula(true), sFunc.evaluate(s));
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void testLessThanFunctionEvaluateStringInput3() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new StringFormula("hello"),
//                    new StringFormula("j")));
//    LessThanFunction sFunc = new LessThanFunction(arguments);
//    assertEquals(new BooleanFormula(true), sFunc.evaluate(s));
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void testLessThanFunctionEvaluateBooleanInput1() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new BooleanFormula(true), new DoubleFormula(4)));
//    LessThanFunction sFunc = new LessThanFunction(arguments);
//    assertEquals(new BooleanFormula(true), sFunc.evaluate(s));
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void testLessThanFunctionEvaluateBooleanInput2() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new DoubleFormula(3), new BooleanFormula(true)));
//    LessThanFunction sFunc = new LessThanFunction(arguments);
//    assertEquals(new BooleanFormula(true), sFunc.evaluate(s));
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void testLessThanFunctionEvaluateBooleanInput3() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new BooleanFormula(true),
//                    new BooleanFormula(false)));
//    LessThanFunction sFunc = new LessThanFunction(arguments);
//    assertEquals(new BooleanFormula(true), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testLessThanFunctionEvaluateFunction() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new ReferenceFormula(Arrays.asList(new
//                    Coord(1, 3))), new DoubleFormula(4)));
//    LessThanFunction sFunc = new LessThanFunction(arguments);
//    assertEquals(new BooleanFormula(false), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testLessThanFunctionEvaluateFunction2() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new DoubleFormula(3),
//                    new ReferenceFormula(Arrays.asList(new Coord(1, 3)))));
//    LessThanFunction sFunc = new LessThanFunction(arguments);
//    assertEquals(new BooleanFormula(true), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testLessThanFunctionEvaluateFunctionBoth() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new
//                            ReferenceFormula(Arrays.asList(new Coord(1, 3))),
//                    new ReferenceFormula(Arrays.asList(new Coord(1, 3)))));
//    LessThanFunction sFunc = new LessThanFunction(arguments);
//    assertEquals(new BooleanFormula(false), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testConcatFunctionEvaluate() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new StringFormula("hi"), new StringFormula("J")));
//    ConcatFunction sFunc = new ConcatFunction(arguments);
//    assertEquals(new StringFormula("hiJ"), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testConcatFunctionEvaluateOneArg() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new StringFormula("Hi")));
//    ConcatFunction sFunc = new ConcatFunction(arguments);
//    assertEquals(new StringFormula("Hi"), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testConcatFunctionEvaluateNoArg() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList());
//    ConcatFunction sFunc = new ConcatFunction(arguments);
//    assertEquals(new StringFormula(""), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testConcatFunctionEvaluateNumInput2() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new StringFormula("hello"),
//                    new DoubleFormula(4)));
//    ConcatFunction sFunc = new ConcatFunction(arguments);
//    assertEquals(new StringFormula("hello4.0"), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testConcatFunctionEvaluateNumInput1() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new DoubleFormula(3), new StringFormula("hi")));
//    ConcatFunction sFunc = new ConcatFunction(arguments);
//    assertEquals(new StringFormula("3.0hi"), sFunc.evaluate(s));
//  }
//
//
//  @Test
//  public void testConcatFunctionEvaluateBooleanInput1() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new BooleanFormula(true),
//                    new StringFormula("hi")));
//    ConcatFunction sFunc = new ConcatFunction(arguments);
//    assertEquals(new StringFormula("truehi"), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testConcatFunctionEvaluateBooleanInput2() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new StringFormula("h"),
//                    new BooleanFormula(true)));
//    ConcatFunction sFunc = new ConcatFunction(arguments);
//    assertEquals(new StringFormula("htrue"), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testConcatFunctionEvaluateBooleanInput3() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new BooleanFormula(true),
//                    new BooleanFormula(false)));
//    ConcatFunction sFunc = new ConcatFunction(arguments);
//    assertEquals(new StringFormula("truefalse"), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testConcatFunctionEvaluateFunction() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new ReferenceFormula(Arrays.asList(new
//                    Coord(1, 3))), new StringFormula("h")));
//    ConcatFunction sFunc = new ConcatFunction(arguments);
//    assertEquals(new StringFormula("400.0h"), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testConcatFunctionEvaluateFunction2() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new StringFormula("g"),
//                    new ReferenceFormula(Arrays.asList(new Coord(1, 3)))));
//    ConcatFunction sFunc = new ConcatFunction(arguments);
//    assertEquals(new StringFormula("g400.0"), sFunc.evaluate(s));
//  }
//
//  @Test
//  public void testConcatFunctionEvaluateFunctionBoth() throws FileNotFoundException {
//    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//            new FileReader("test/test3.gOOD"));
//
//    List<FormulaInterface> arguments =
//            new ArrayList<>(Arrays.asList(new
//                            ReferenceFormula(Arrays.asList(new Coord(1, 3))),
//                    new ReferenceFormula(Arrays.asList(new Coord(1, 3)))));
//    ConcatFunction sFunc = new ConcatFunction(arguments);
//    assertEquals(new StringFormula("400.0400.0"), sFunc.evaluate(s));
//  }
//
//}
