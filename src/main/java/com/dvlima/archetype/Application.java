package com.dvlima.archetype;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.dvlima.archetype.business.controller.HomeController;
import com.dvlima.archetype.ui.util.LookAndFeels;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    LookAndFeels.setWindowsLookAndFeel();
    ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class)
        .headless(false).run(args);
    HomeController controller = context.getBean(HomeController.class);
    controller.open();
  }
}