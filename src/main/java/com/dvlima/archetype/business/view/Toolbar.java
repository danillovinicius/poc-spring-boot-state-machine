package com.dvlima.archetype.business.view;

import lombok.Getter;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;


@Getter
public class Toolbar extends JPanel {

  // actions
  private JButton sairButton = new JButton("SAIR");
  private JButton printButton = new JButton("PRINT");
  // status
  private JButton rascunhoButton = new JButton("RASCUNHO");
  private JButton publicadoButton = new JButton("PUBLICADO");
  private JButton abertoButton = new JButton("ABERTO");
  private JButton encerradoButton = new JButton("ENCERRADO");
  private JButton andamentoButton = new JButton("ANDAMENTO");
  private JButton inativoButton = new JButton("INATIVO");

  public Toolbar() {
    setLayout(new FlowLayout(FlowLayout.RIGHT));
    setBorder(BorderFactory.createEtchedBorder());
    add(rascunhoButton);
    add(publicadoButton);
    add(abertoButton);
    add(encerradoButton);
    add(andamentoButton);
    add(inativoButton);

    add(customButton(printButton, Color.white, Color.BLUE));
    add(customButton(sairButton, Color.white, Color.RED));
  }

  private JButton customButton(JButton btn, Color foreground, Color background) {
    btn.setForeground(foreground);
    btn.setBackground(background);
    btn.setContentAreaFilled(false);
    btn.setOpaque(true);
    return btn;
  }

}
