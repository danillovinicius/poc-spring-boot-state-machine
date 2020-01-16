package com.dvlima.archetype.business.statemachine;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import com.dvlima.archetype.business.enummerator.SituacaoEvent;
import com.dvlima.archetype.business.enummerator.SituacaoState;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@SpringBootTest
class StateMachineConfigTest {

  @Autowired
  StateMachineFactory<SituacaoState, SituacaoEvent> factory;

  @Test
  void testNewStateMachine() {
    StateMachine<SituacaoState, SituacaoEvent> sm = factory.getStateMachine(UUID.randomUUID());

    sm.start();

    assertSame(sm.getState().getId(), SituacaoState.RASCUNHO);

    sm.sendEvent(SituacaoEvent.PUBLICACAO);

    System.out.println(sm.getState().toString());

    sm.sendEvent(SituacaoEvent.CANDIDATURA);

    System.out.println(sm.getState().toString());

    sm.sendEvent(SituacaoEvent.TRIAGEM);

    System.out.println(sm.getState().toString());

  }
}