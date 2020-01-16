package com.dvlima.poc.statemachine.guards;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

import com.dvlima.poc.enummerator.SituacaoEvent;
import com.dvlima.poc.enummerator.SituacaoState;
import com.dvlima.poc.service.VagaServiceImpl;

@Component
public class ProrrogacaoGuard implements Guard<SituacaoState, SituacaoEvent> {

  @Override
  public boolean evaluate(StateContext<SituacaoState, SituacaoEvent> context) {
    return context.getMessageHeader(VagaServiceImpl.VAGA_ID_HEADER) != null;
  }
}
