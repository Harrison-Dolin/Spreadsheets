package edu.cs3500.spreadsheets.view;

import javax.swing.JFrame;


import edu.cs3500.spreadsheets.controller.Features;

/**
 * This is a VisualView class, that is a view that represents a model in gui form. Represents a
 * spreadsheet in gui form.
 */
public class VisualView extends JFrame implements View {

  private VisualPanel viewPanel;

  /**
   * Creates a visual view. Initialized the offset in both the rows and columns.
   */
  public VisualView(VisualPanel p) {
    this.viewPanel = p;
    this.add(viewPanel);
    this.pack();
  }

  @Override
  public void render() {
    viewPanel.draw();
    this.setVisible(true);
    this.pack();
  }

  @Override
  public void addFeatures(Features features) {
    // This method needs to be empty because it is necessary in the interface for views, and
    // enables the creation of the EditingView. However, in the VisualView there is no need for
    // a mouselistener.
  }


  @Override
  public void acceptMouseListener(MouseListener m) {
    viewPanel.getCellGrid().addMouseListener(m);
  }

}
