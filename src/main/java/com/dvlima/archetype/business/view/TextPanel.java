package com.dvlima.archetype.business.view;

import lombok.Getter;

import java.awt.BorderLayout;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@Getter
public class TextPanel extends JPanel {

  private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
  private JTextArea textArea;

  public TextPanel() {
    this.textArea = new JTextArea();
    setLayout(new BorderLayout());
    add(new JScrollPane(textArea), BorderLayout.CENTER);
  }

  public void append(String text) {
    String data = LocalTime.now().format(dtf);
    textArea.setText(String.format("%s: %s %n%s", data, text, textArea.getText()));
  }
}
