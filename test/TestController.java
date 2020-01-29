import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import javax.swing.JTextField;

import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.controller.Controller;
import edu.cs3500.spreadsheets.controller.Features;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.SpreadSheet;
import edu.cs3500.spreadsheets.model.SpreadSheetModel;
import edu.cs3500.spreadsheets.model.WorksheetBuilder;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.view.EditingView;
import edu.cs3500.spreadsheets.view.IViewModel;
import edu.cs3500.spreadsheets.view.View;
import edu.cs3500.spreadsheets.view.ViewModel;
import edu.cs3500.spreadsheets.view.VisualPanel;
import edu.cs3500.spreadsheets.view.VisualView;

/**
 * All tests for the Controller class found here.
 */
public class TestController {

  @Test(expected = IllegalArgumentException.class)
  public void testControllerWithNullModel() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      Features c = new Controller(null, new VisualView(new VisualPanel(viewModel)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerWithNullView() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      Features c = new Controller(s, null);
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelNumberToNumber() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals("10", s.getEvaluatedContentsAsString(new Coord(1, 1)));
      c.editModel(1, 1, new JTextField("44"));
      assertEquals("44", s.getEvaluatedContentsAsString(new Coord(1, 1)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelReferenceOfCells() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals("20", s.getEvaluatedContentsAsString(new Coord(1, 3)));
      c.editModel(1, 3, new JTextField("=(SUM A4:A5)"));
      assertEquals("105", s.getEvaluatedContentsAsString(new Coord(1, 3)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelReferenceOfCells2() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals("20", s.getEvaluatedContentsAsString(new Coord(1, 3)));
      c.editModel(1, 3, new JTextField("=A4:A5"));
      assertEquals("100", s.getEvaluatedContentsAsString(new Coord(1, 3)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelNumberToBool() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals("10", s.getEvaluatedContentsAsString(new Coord(1, 1)));
      c.editModel(1, 1, new JTextField("true"));
      assertEquals("true", s.getEvaluatedContentsAsString(new Coord(1, 1)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelBoolToNumber() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals("true", s.getEvaluatedContentsAsString(new Coord(1, 8)));
      c.editModel(1, 8, new JTextField("44"));
      assertEquals("44", s.getEvaluatedContentsAsString(new Coord(1, 8)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelBoolToString() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals("true", s.getEvaluatedContentsAsString(new Coord(1, 8)));
      c.editModel(1, 8, new JTextField("\"dog\""));
      assertEquals("dog", s.getEvaluatedContentsAsString(new Coord(1, 8)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelNumToString() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals("10", s.getEvaluatedContentsAsString(new Coord(1, 1)));
      c.editModel(1, 1, new JTextField("\"hi\""));
      assertEquals("hi", s.getEvaluatedContentsAsString(new Coord(1, 1)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelStringToNum() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals("cat", s.getEvaluatedContentsAsString(new Coord(1, 6)));
      c.editModel(1, 6, new JTextField("10"));
      assertEquals("10", s.getEvaluatedContentsAsString(new Coord(1, 6)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelStringToBool() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals("cat", s.getEvaluatedContentsAsString(new Coord(1, 6)));
      c.editModel(1, 6, new JTextField("false"));
      assertEquals("false", s.getEvaluatedContentsAsString(new Coord(1, 6)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }


  @Test
  public void testControllerEditModelNumberToSum() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals("10", s.getEvaluatedContentsAsString(new Coord(1, 1)));
      c.editModel(1, 1, new JTextField("=(SUM A2 A5)"));
      assertEquals("15", s.getEvaluatedContentsAsString(new Coord(1, 1)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelNumberToProd() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals("10", s.getEvaluatedContentsAsString(new Coord(1, 1)));
      c.editModel(1, 1, new JTextField("=(PRODUCT A2 A5)"));
      assertEquals("50", s.getEvaluatedContentsAsString(new Coord(1, 1)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelNumberToConcat() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals("10", s.getEvaluatedContentsAsString(new Coord(1, 1)));
      c.editModel(1, 1, new JTextField("=(CONCAT A2 A5)"));
      assertEquals("10.05.0", s.getEvaluatedContentsAsString(new Coord(1, 1)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelNumberToConcat2() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals("10", s.getEvaluatedContentsAsString(new Coord(1, 1)));
      c.editModel(1, 1, new JTextField("=(CONCAT " + "\"i\"" + " A5)"));
      assertEquals("i5.0", s.getEvaluatedContentsAsString(new Coord(1, 1)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelNumberToConcatBad() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals("10", s.getEvaluatedContentsAsString(new Coord(1, 1)));
      c.editModel(1, 1, new JTextField("=(CONCAT " + "\"i\"" + " A5"));
      assertEquals("=(CONCAT " + "\"i\"" + " A5",
              s.cellOriginalContents(new Coord(1, 1)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelNumberToProdBad() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals("10", s.getEvaluatedContentsAsString(new Coord(1, 1)));
      c.editModel(1, 1, new JTextField("=PRODUCT 2 A5)"));
      assertEquals("=PRODUCT 2 A5)",
              s.cellOriginalContents(new Coord(1, 1)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelNumberToSumBad() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals("10", s.getEvaluatedContentsAsString(new Coord(1, 1)));
      c.editModel(1, 1, new JTextField("=SUM 2 A5"));
      assertEquals("=SUM 2 A5",
              s.cellOriginalContents(new Coord(1, 1)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelBadExp() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      Features c = new Controller(s, editingView);
      assertEquals("10", s.getEvaluatedContentsAsString(new Coord(1, 1)));
      c.editModel(1, 1, new JTextField("="));
      assertEquals("=",
              s.cellOriginalContents(new Coord(1, 1)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }


  @Test
  public void testControllerEditModelStringNewCell() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals(null, s.getCell(new Coord(11, 1)));
      c.editModel(11, 1, new JTextField("\"hi\""));
      assertEquals("hi", s.getEvaluatedContentsAsString(new Coord(11, 1)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelNumNewCell() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals(null, s.getCell(new Coord(11, 6)));
      c.editModel(11, 6, new JTextField("10"));
      assertEquals("10", s.getEvaluatedContentsAsString(new Coord(11, 6)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelBoolNewCell() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals(null, s.getCell(new Coord(1, 16)));
      c.editModel(1, 16, new JTextField("false"));
      assertEquals("false", s.getEvaluatedContentsAsString(new Coord(1, 16)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }


  @Test
  public void testControllerEditModelSumNewCell() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals(null, s.getCell(new Coord(21, 1)));
      c.editModel(21, 1, new JTextField("=(SUM A2 A5)"));
      assertEquals("15", s.getEvaluatedContentsAsString(new Coord(21, 1)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelProdNewCell() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals(null, s.getCell(new Coord(8, 1)));
      c.editModel(8, 1, new JTextField("=(PRODUCT A2 A5)"));
      assertEquals("50", s.getEvaluatedContentsAsString(new Coord(8, 1)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelConcatNewCell() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals(null, s.getCell(new Coord(9, 1)));
      c.editModel(9, 1, new JTextField("=(CONCAT A2 A5)"));
      assertEquals("10.05.0", s.getEvaluatedContentsAsString(new Coord(9, 1)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelConcat2NewCell() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals(null, s.getCell(new Coord(7, 1)));
      c.editModel(7, 1, new JTextField("=(CONCAT " + "\"i\"" + " A5)"));
      assertEquals("i5.0", s.getEvaluatedContentsAsString(new Coord(7, 1)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelConcatBadNewCell() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals(null, s.getCell(new Coord(7, 1)));
      c.editModel(7, 1, new JTextField("=(CONCAT " + "\"i\"" + " A5"));
      assertEquals("=(CONCAT " + "\"i\"" + " A5",
              s.cellOriginalContents(new Coord(7, 1)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelProdBadNewCell() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals(null, s.getCell(new Coord(8, 1)));
      c.editModel(8, 1, new JTextField("=PRODUCT 2 A5)"));
      assertEquals("=PRODUCT 2 A5)",
              s.cellOriginalContents(new Coord(8, 1)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelSumBadNewCell() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals(null, s.getCell(new Coord(1, 28)));
      c.editModel(1, 28, new JTextField("=SUM 2 A5"));
      assertEquals("=SUM 2 A5",
              s.cellOriginalContents(new Coord(1, 28)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelBadExpNewCell() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      Features c = new Controller(s, editingView);
      assertEquals(null, s.getCell(new Coord(1, 24)));
      c.editModel(1, 24, new JTextField("="));
      assertEquals("=",
              s.cellOriginalContents(new Coord(1, 24)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }


  @Test
  public void testControllerEditModelNewCell() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,12);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals(null, s.getCell(new Coord(1, 12)));
      c.editModel(1, 12, new JTextField("44"));
      assertEquals("44", s.getEvaluatedContentsAsString(new Coord(1, 12)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelInvalidInput() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals("10", s.getEvaluatedContentsAsString(new Coord(1, 1)));
      c.editModel(1, 1, new JTextField("SUM"));
      assertEquals("SUM",
              s.cellOriginalContents(new Coord(1, 1)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelCyclicReference() {
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View editingView = new EditingView(viewModel);
      VisualPanel viewPanel = new VisualPanel(viewModel);
      //MouseListener m = new MouseListener(1,1);
      //editingView.acceptMouseListener(m);
      Features c = new Controller(s, editingView);
      assertEquals("10", s.getEvaluatedContentsAsString(new Coord(1, 1)));
      c.editModel(1, 1, new JTextField("=A1"));
      assertEquals("=A1",
              s.cellOriginalContents(new Coord(1, 1)));
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void testControllerEditModelMockModel() {
    StringBuilder log = new StringBuilder();
    SpreadSheetModel s = new MockSpreadsheet(log);
    IViewModel viewModel = new ViewModel(s);
    View editingView = new EditingView(viewModel);
    VisualPanel viewPanel = new VisualPanel(viewModel);
    //MouseListener m = new MouseListener(1,1);
    //editingView.acceptMouseListener(m);
    Features c = new Controller(s, editingView);
    c.editModel(1, 2, new JTextField("44"));
    assertEquals("getCell:1 2\n"
            + "createCell: 1 2 44", log.toString());
  }

  @Test
  public void testControllerEditModelMockModelBool() {
    StringBuilder log = new StringBuilder();
    SpreadSheetModel s = new MockSpreadsheet(log);
    IViewModel viewModel = new ViewModel(s);
    View editingView = new EditingView(viewModel);
    VisualPanel viewPanel = new VisualPanel(viewModel);
    //MouseListener m = new MouseListener(1,1);
    //editingView.acceptMouseListener(m);
    Features c = new Controller(s, editingView);
    c.editModel(1, 2, new JTextField("true"));
    assertEquals("getCell:1 2\n"
            + "createCell: 1 2 true", log.toString());
  }

  @Test
  public void testControllerEditModelMockModelReference() {
    StringBuilder log = new StringBuilder();
    SpreadSheetModel s = new MockSpreadsheet(log);
    IViewModel viewModel = new ViewModel(s);
    View editingView = new EditingView(viewModel);
    VisualPanel viewPanel = new VisualPanel(viewModel);
    //MouseListener m = new MouseListener(1,1);
    //editingView.acceptMouseListener(m);
    Features c = new Controller(s, editingView);
    c.editModel(1, 2, new JTextField("=A1:A2"));
    assertEquals("getCell:1 2\n"
            + "createCell: 1 2 =A1:A2", log.toString());
  }

  @Test
  public void testControllerEditModelMockModelString() {
    StringBuilder log = new StringBuilder();
    SpreadSheetModel s = new MockSpreadsheet(log);
    IViewModel viewModel = new ViewModel(s);
    View editingView = new EditingView(viewModel);
    VisualPanel viewPanel = new VisualPanel(viewModel);
    //MouseListener m = new MouseListener(1,1);
    //editingView.acceptMouseListener(m);
    Features c = new Controller(s, editingView);
    c.editModel(1, 2, new JTextField("\"i'm so tired\""));
    assertEquals("getCell:1 2\n"
            + "createCell: 1 2 \"i'm so tired\"", log.toString());
  }

  @Test
  public void testControllerEditModelMockModelSum() {
    StringBuilder log = new StringBuilder();
    SpreadSheetModel s = new MockSpreadsheet(log);
    IViewModel viewModel = new ViewModel(s);
    View editingView = new EditingView(viewModel);
    VisualPanel viewPanel = new VisualPanel(viewModel);
    //MouseListener m = new MouseListener(1,1);
    //editingView.acceptMouseListener(m);
    Features c = new Controller(s, editingView);
    c.editModel(1, 2, new JTextField("=(SUM 1 2)"));
    assertEquals("getCell:1 2\n"
            + "createCell: 1 2 =(SUM 1 2)", log.toString());
  }

  @Test
  public void testControllerEditModelMockModelProd() {
    StringBuilder log = new StringBuilder();
    SpreadSheetModel s = new MockSpreadsheet(log);
    IViewModel viewModel = new ViewModel(s);
    View editingView = new EditingView(viewModel);
    VisualPanel viewPanel = new VisualPanel(viewModel);
    //MouseListener m = new MouseListener(1,1);
    //editingView.acceptMouseListener(m);
    Features c = new Controller(s, editingView);
    c.editModel(1, 2, new JTextField("=(PRODUCT A1 2)"));
    assertEquals("getCell:1 2\n"
            + "createCell: 1 2 =(PRODUCT A1 2)", log.toString());
  }

  @Test
  public void testControllerEditModelMockModelConcat() {
    StringBuilder log = new StringBuilder();
    SpreadSheetModel s = new MockSpreadsheet(log);
    IViewModel viewModel = new ViewModel(s);
    View editingView = new EditingView(viewModel);
    VisualPanel viewPanel = new VisualPanel(viewModel);
    //MouseListener m = new MouseListener(1,1);
    //editingView.acceptMouseListener(m);
    Features c = new Controller(s, editingView);
    c.editModel(1, 2, new JTextField("=(CONCAT 1 2)"));
    assertEquals("getCell:1 2\n"
            + "createCell: 1 2 =(CONCAT 1 2)", log.toString());
  }

  @Test
  public void testControllerEditModelMockModelBadExp() {
    StringBuilder log = new StringBuilder();
    SpreadSheetModel s = new MockSpreadsheet(log);
    IViewModel viewModel = new ViewModel(s);
    View editingView = new EditingView(viewModel);
    VisualPanel viewPanel = new VisualPanel(viewModel);
    //MouseListener m = new MouseListener(1,1);
    //editingView.acceptMouseListener(m);
    Features c = new Controller(s, editingView);
    c.editModel(1, 2, new JTextField("="));
    assertEquals("getCell:1 2\n"
            + "createCell: 1 2 =", log.toString());
  }

  @Test
  public void testControllerEditModelMockModelBadSum() {
    StringBuilder log = new StringBuilder();
    SpreadSheetModel s = new MockSpreadsheet(log);
    IViewModel viewModel = new ViewModel(s);
    View editingView = new EditingView(viewModel);
    VisualPanel viewPanel = new VisualPanel(viewModel);
    //MouseListener m = new MouseListener(1,1);
    //editingView.acceptMouseListener(m);
    Features c = new Controller(s, editingView);
    c.editModel(1, 2, new JTextField("=(SUM 1 2"));
    assertEquals("getCell:1 2\n"
            + "createCell: 1 2 =(SUM 1 2", log.toString());
  }

  @Test
  public void testControllerEditModelMockModelBadProd() {
    StringBuilder log = new StringBuilder();
    SpreadSheetModel s = new MockSpreadsheet(log);
    IViewModel viewModel = new ViewModel(s);
    View editingView = new EditingView(viewModel);
    VisualPanel viewPanel = new VisualPanel(viewModel);
    Features c = new Controller(s, editingView);
    c.editModel(1, 2, new JTextField("=1 2)"));
    assertEquals("getCell:1 2\n"
            + "createCell: 1 2 =1 2)", log.toString());
  }

  @Test
  public void testControllerEditModelMockModelSumRef() {
    StringBuilder log = new StringBuilder();
    SpreadSheetModel s = new MockSpreadsheet(log);
    IViewModel viewModel = new ViewModel(s);
    View editingView = new EditingView(viewModel);
    VisualPanel viewPanel = new VisualPanel(viewModel);
    //MouseListener m = new MouseListener(1,1);
    //editingView.acceptMouseListener(m);
    Features c = new Controller(s, editingView);
    c.editModel(1, 2, new JTextField("=(SUM A1:A3)"));
    assertEquals("getCell:1 2\n"
            + "createCell: 1 2 =(SUM A1:A3)", log.toString());
  }

  /**
   * In our mock model, we made it return a cell for the coord 1 1, just to test all parts of the
   * controller method, so this tests when you edit a existing cell.
   */
  @Test
  public void testControllerEditModelMockModelEditExistingCell() {
    StringBuilder log = new StringBuilder();
    SpreadSheetModel s = new MockSpreadsheet(log);
    IViewModel viewModel = new ViewModel(s);
    View editingView = new EditingView(viewModel);
    VisualPanel viewPanel = new VisualPanel(viewModel);
    //MouseListener m = new MouseListener(1,1);
    //editingView.acceptMouseListener(m);
    Features c = new Controller(s, editingView);
    c.editModel(1, 1, new JTextField("44"));
    c.editModel(1, 2, new JTextField("44"));
    c.editModel(1, 1, new JTextField("88"));
    assertEquals("getCell:1 1\n"
            + "editCellContents: 1 1 44\n"
            + "getCell:1 2\n"
            + "createCell: 1 2 44getCell:1 1\n"
            + "editCellContents: 1 1 88\n", log.toString());
  }

  @Test
  public void testSavingWorksheetThroughController() {
    WorksheetBuilder builder = new WorksheetBuilder();
    builder.createCell(1, 2, "=(SUM 2 3)");

    SpreadSheet spreadSheet = builder.createWorksheet();
    IViewModel viewModel = new ViewModel(spreadSheet);
    View editingView = new EditingView(viewModel);
    Controller controller = new Controller(spreadSheet, editingView, "testingFile.txt");

    JTextField textField = new JTextField();
    textField.setText("22");

    controller.editModel(1, 1, textField);
    controller.saveSpreadsheet();

    File file = new File("testingFile.txt");

    Scanner scanner = null;

    try {
      scanner = new Scanner(file);
    } catch (IOException e) {
      // There is no body to the catch here because it is in a testing method, and the only file
      // it will be dealing with was coded to not have errors, so this will never be called
      // and therefore does not need to have an contents.
    }

    StringBuilder str = new StringBuilder();

    while (scanner.hasNext()) {
      str.append(scanner.nextLine()).append("\n");
    }

    WorksheetBuilder newBuilder = new WorksheetBuilder();
    SpreadSheet secondSpreadsheet = WorksheetReader.read(newBuilder,
            new StringReader(str.toString()));

    assertEquals("22",
            secondSpreadsheet.getEvaluatedContentsAsString(new Coord(1, 1)));
  }

}
