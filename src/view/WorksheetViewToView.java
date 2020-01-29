package edu.cs3500.spreadsheets.view;

import java.io.IOException;

import edu.cs3500.spreadsheets.controller.Features;
import edu.cs3500.spreadsheets.provider.view.WorksheetView;

/**
 * This is an adapter that allows you to use a WorksheetView as a View.
 * Adapts the provider's view implementation to our view, so that it can be used in our controller.
 */
public class WorksheetViewToView implements View {
  WorksheetView viewAdaptee;

  /**
   * Constructs a WorksheetViewToView.
   * @param viewAdaptee the view to be adapted, which is the provider's view
   *     (interface WorksheetView)
   */
  public WorksheetViewToView(WorksheetView viewAdaptee) {
    this.viewAdaptee = viewAdaptee;
  }

  @Override
  public void render() throws IOException {
    viewAdaptee.render();
    viewAdaptee.makeVisible();
  }

  @Override
  public void addFeatures(Features features) {
    //This method is not called nor needed to make the provider's view with with ours.
    // Additionally, since our code and the providers code implement features in a different
    // way there is no need to implement this to get their code to work.
  }

  @Override
  public void acceptMouseListener(MouseListener m) {
    //This method is not called nor needed to make the provider's view with with ours.
    // Additionally, since our code and the providers code implement listeners in a different
    // way there is no need to implement this to get their code to work.
  }
}
