package com.dvlima.archetype.business.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.dvlima.archetype.business.view.HomeFrame;

import java.awt.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.swing.JButton;

@Slf4j
@Controller
public class HomeController {

  private String name;
  private HomeFrame frame;

  @Autowired
  public HomeController(@Value("${spring.application.name}") String name, HomeFrame frame) {
    this.name = name;
    this.frame = frame;
  }

  @PostConstruct
  private void prepareListeners() {
    log.info("message");
    this.frame.getToolbar().getSairButton().addActionListener(this::sairActionPerformed);
    this.frame.getToolbar().getRascunhoButton().addActionListener(this::execActionPerformed);
  }

  public void open() {
    this.frame.setVisible(true);
    this.frame.setTitle(name);
  }

  private void execActionPerformed(ActionEvent e) {
    log(((JButton) e.getSource()).getText());
  }

  private void sairActionPerformed(ActionEvent e) {
    System.exit(0);
  }

  private void log(String text) {
    this.frame.getTextPanel().append(text);
  }

}
