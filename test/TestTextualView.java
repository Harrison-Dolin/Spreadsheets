import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import edu.cs3500.spreadsheets.model.SpreadSheetModel;
import edu.cs3500.spreadsheets.model.WorksheetBuilder;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.view.IViewModel;
import edu.cs3500.spreadsheets.view.TextualView;
import edu.cs3500.spreadsheets.view.View;
import edu.cs3500.spreadsheets.view.ViewModel;

import static org.junit.Assert.assertEquals;

/**
 * All tests for the TextualView class are here.
 */
public class TestTextualView {

  @Test
  public void testMasterVisualWorksheet() throws FileNotFoundException {
    //clear the file
    PrintWriter writer = new PrintWriter("test/emptyFile.gOOD");
    writer.print("");
    writer.close();
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/masterTextualTest"));
    IViewModel viewModel = new ViewModel(s);
    try {
      FileWriter f = new FileWriter("test/emptyFile.gOOD", true);
      View tView = new TextualView(viewModel, f);
      tView.render();
      f.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    SpreadSheetModel sView = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/emptyFile.gOOD"));
    assertEquals(true, sView.equals(s));
  }

  @Test
  public void testSimpleSmallWorksheet() throws FileNotFoundException {
    //clear the file
    PrintWriter writer = new PrintWriter("test/emptyFile.gOOD");
    writer.print("");
    writer.close();
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test1.gOOD"));
    IViewModel viewModel = new ViewModel(s);
    try {
      FileWriter f = new FileWriter("test/emptyFile.gOOD", true);
      View tView = new TextualView(viewModel, f);
      tView.render();
      f.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    SpreadSheetModel sView = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/emptyFile.gOOD"));
    assertEquals(true, sView.equals(s));
  }

  @Test
  public void testFunctionsWorksheet() throws FileNotFoundException {
    //clear the file
    PrintWriter writer = new PrintWriter("test/emptyFile.gOOD");
    writer.print("");
    writer.close();
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test2.gOOD"));
    IViewModel viewModel = new ViewModel(s);
    try {
      FileWriter f = new FileWriter("test/emptyFile.gOOD", true);
      View tView = new TextualView(viewModel, f);
      tView.render();
      f.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    SpreadSheetModel sView = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/emptyFile.gOOD"));
    assertEquals(true, sView.equals(s));
  }

  @Test
  public void testComplexWorksheet() throws FileNotFoundException {
    //clear the file
    PrintWriter writer = new PrintWriter("test/emptyFile.gOOD");
    writer.print("");
    writer.close();
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/test3.gOOD"));
    IViewModel viewModel = new ViewModel(s);
    try {
      FileWriter f = new FileWriter("test/emptyFile.gOOD", true);
      View tView = new TextualView(viewModel, f);
      tView.render();
      f.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    SpreadSheetModel sView = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/emptyFile.gOOD"));
    assertEquals(true, sView.equals(s));
  }

  @Test
  public void testConcatWorksheet() throws FileNotFoundException {
    //clear the file
    PrintWriter writer = new PrintWriter("test/emptyFile.gOOD");
    writer.print("");
    writer.close();
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testConcat.gOOD"));
    IViewModel viewModel = new ViewModel(s);
    try {
      FileWriter f = new FileWriter("test/emptyFile.gOOD", true);
      View tView = new TextualView(viewModel, f);
      tView.render();
      f.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    SpreadSheetModel sView = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/emptyFile.gOOD"));
    assertEquals(true, sView.equals(s));
  }

  @Test
  public void testCyclicalWorksheet() throws FileNotFoundException {
    //clear the file
    PrintWriter writer = new PrintWriter("test/emptyFile.gOOD");
    writer.print("");
    writer.close();
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/testCyclicReference2.gOOD"));
    IViewModel viewModel = new ViewModel(s);
    try {
      FileWriter f = new FileWriter("test/emptyFile.gOOD", true);
      View tView = new TextualView(viewModel, f);
      tView.render();
      f.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    SpreadSheetModel sView = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/emptyFile.gOOD"));
    assertEquals(true, sView.equals(s));
  }

  @Test
  public void testFib15Worksheet() throws FileNotFoundException {
    //clear the file
    PrintWriter writer = new PrintWriter("test/emptyFile.gOOD");
    writer.print("");
    writer.close();
    SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/fib15.gOOD"));
    IViewModel viewModel = new ViewModel(s);
    try {
      FileWriter f = new FileWriter("test/emptyFile.gOOD", true);
      View tView = new TextualView(viewModel, f);
      tView.render();
      f.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    SpreadSheetModel sView = WorksheetReader.read(new WorksheetBuilder(),
            new FileReader("test/emptyFile.gOOD"));
    assertEquals(true, sView.equals(s));
  }
}
