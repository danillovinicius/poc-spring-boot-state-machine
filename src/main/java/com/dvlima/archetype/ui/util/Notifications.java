package com.dvlima.archetype.ui.util;

import javax.swing.JOptionPane;

public class Notifications {

  private static void show(String message, String title, int optionPane) {
    JOptionPane.showMessageDialog(null, message, title, optionPane);
  }

  public static void info(String message) {
    Notifications.show(message, "Atention", JOptionPane.INFORMATION_MESSAGE);
  }

  public static void error(String message) {
    Notifications.show(message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  public static void warning(String message) {
    Notifications.show(message, "Warning", JOptionPane.WARNING_MESSAGE);
  }

}
