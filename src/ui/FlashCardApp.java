package ui;

import ui.gui.SelectCardPackPanel;

import javax.swing.*;
import java.awt.*;

/**
 * The FlashCardApp class starts the application.
 */
public class FlashCardApp extends JFrame {

  private SelectCardPackPanel selectCardPackPanel;

  public FlashCardApp() {
    super("Flashcard App");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    selectCardPackPanel = new SelectCardPackPanel();
    getContentPane().add(selectCardPackPanel);

    // init size and location
    setPreferredSize(new Dimension(500, 410));
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  /**
   * Method switches JPanel in the JFrame Gui to the Jpanel in arg.
   *
   * @param panel is the Jpanel to be displayed in the gui.
   */
  public void changePanel(JPanel panel) {
    getContentPane().removeAll();
    invalidate();
    getContentPane().add(panel);
    validate();
    pack();
  }

  public static void main(String[] args) {
    new FlashCardApp();
  }
}
