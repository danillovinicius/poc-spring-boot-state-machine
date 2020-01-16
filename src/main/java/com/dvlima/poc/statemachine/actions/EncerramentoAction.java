package com.dvlima.poc.statemachine.actions;

import org.springframework.stereotype.Component;
import org.springframework.statemachine.action.Action;

import com.dvlima.poc.enummerator.SituacaoEvent;

import org.springframework.statemachine.StateContext;

import com.dvlima.poc.enummerator.SituacaoState;

@Component
public class EncerramentoAction implements Action<SituacaoState, SituacaoEvent> {

  @Override
  public void execute(StateContext<SituacaoState, SituacaoEvent> context) {
    System.out.println("EncerramentoAction:: Enviando email para os candidatos aprovados");
  }

}
