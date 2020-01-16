package com.dvlima.archetype.business.interceptor;

import lombok.RequiredArgsConstructor;

import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import com.dvlima.archetype.business.entity.Vaga;
import com.dvlima.archetype.business.enummerator.SituacaoEvent;
import com.dvlima.archetype.business.enummerator.SituacaoState;
import com.dvlima.archetype.business.repository.VagaRepository;
import com.dvlima.archetype.business.service.VagaServiceImpl;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VagaStateChangeInterceptor extends
    StateMachineInterceptorAdapter<SituacaoState, SituacaoEvent> {

  private final VagaRepository vagaRepository;

  @Override
  public void preStateChange(State<SituacaoState, SituacaoEvent> state,
      Message<SituacaoEvent> message, Transition<SituacaoState, SituacaoEvent> transition,
      StateMachine<SituacaoState, SituacaoEvent> stateMachine) {

    Optional.ofNullable(message).ifPresent(msg -> {
      Object key = msg.getHeaders().getOrDefault(VagaServiceImpl.VAGA_ID_HEADER, -1L);
      Long value = Long.class.cast(key);

      Optional.ofNullable(value).ifPresent(paymentId -> {
        Vaga vaga = vagaRepository.getOne(paymentId);
        vaga.setStatus(state.getId());
        vagaRepository.save(vaga);
      });
    });
  }
}
