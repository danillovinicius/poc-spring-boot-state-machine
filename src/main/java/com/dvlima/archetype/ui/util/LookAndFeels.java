package com.dvlima.archetype.ui.util;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class LookAndFeels {

  private static final String ERROR_MESSAGE = "There was an error while loading look an feel: ";

  public static void setWindowsLookAndFeel() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, ERROR_MESSAGE + e, "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

}
