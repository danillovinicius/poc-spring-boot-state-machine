package com.dvlima.archetype.business.view;

import lombok.Getter;

import org.springframework.stereotype.Component;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

@Getter
@Component
public class HomeFrame extends JFrame {

  private TextPanel textPanel = new TextPanel();
  private Toolbar toolbar = new Toolbar();

  public HomeFrame() {
    setLayout(new BorderLayout());
    add(toolbar, BorderLayout.NORTH);
    add(textPanel, BorderLayout.CENTER);
    setSize(800, 300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
    setLocation(x, y);
    setVisible(true);
  }

}
