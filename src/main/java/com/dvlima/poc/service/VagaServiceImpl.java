package com.dvlima.poc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dvlima.poc.entity.Vaga;

import static com.dvlima.poc.enummerator.SituacaoEvent.*;

import com.dvlima.poc.enummerator.SituacaoEvent;
import com.dvlima.poc.enummerator.SituacaoState;
import com.dvlima.poc.statemachine.interceptor.VagaStateChangeInterceptor;
import com.dvlima.poc.repository.VagaRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class VagaServiceImpl implements VagaService {

  public static final String VAGA_ID_HEADER = "vaga_id";

  private final VagaRepository vagaRepository;
  private final StateMachineFactory<SituacaoState, SituacaoEvent> stateMachineFactory;
  private final VagaStateChangeInterceptor vagaStateChangeInterceptor;

  @Override
  public Vaga criarNovaVaga(Vaga vaga) {
    vaga.setStatus(SituacaoState.RASCUNHO);
    return vagaRepository.save(vaga);
  }

  @Override
  public Vaga consultarVagaPorCodigo(Long vagaId) {
    return vagaRepository.getOne(vagaId);
  }

  @Transactional
  @Override
  public StateMachine<SituacaoState, SituacaoEvent> publicarVaga(Long vagaId) {
    StateMachine<SituacaoState, SituacaoEvent> sm = build(vagaId);
    executarEvento(PUBLICACAO, vagaId, sm);
    return sm;
  }

  @Transactional
  @Override
  public StateMachine<SituacaoState, SituacaoEvent> inativarVaga(Long vagaId) {
    StateMachine<SituacaoState, SituacaoEvent> sm = build(vagaId);
    executarEvento(INATIVACAO, vagaId, sm);
    return sm;
  }

  @Transactional
  @Override
  public StateMachine<SituacaoState, SituacaoEvent> prorrogarVaga(Long vagaId, LocalDateTime data) {
    StateMachine<SituacaoState, SituacaoEvent> sm = build(vagaId);
    executarEvento(PRORROGACAO, vagaId, sm);
    return sm;
  }

  @Transactional
  @Override
  public StateMachine<SituacaoState, SituacaoEvent> habilitarCandidaturas(Long vagaId) {
    StateMachine<SituacaoState, SituacaoEvent> sm = build(vagaId);
    executarEvento(CANDIDATURA, vagaId, sm);
    return sm;
  }

  @Transactional
  @Override
  public StateMachine<SituacaoState, SituacaoEvent> iniciarAnaliseCurricular(Long vagaId) {
    StateMachine<SituacaoState, SituacaoEvent> sm = build(vagaId);
    executarEvento(TRIAGEM, vagaId, sm);
    return sm;
  }

  @Transactional
  @Override
  public StateMachine<SituacaoState, SituacaoEvent> finalizarAnaliseCurricular(Long vagaId) {
    StateMachine<SituacaoState, SituacaoEvent> sm = build(vagaId);
    executarEvento(ENCERRAMENTO, vagaId, sm);
    return sm;
  }

  @Transactional
  @Override
  public StateMachine<SituacaoState, SituacaoEvent> cancelarAnaliseCurricular(Long vagaId) {
    StateMachine<SituacaoState, SituacaoEvent> sm = build(vagaId);
    executarEvento(CANCELAMENTO, vagaId, sm);
    return sm;
  }

  private void executarEvento(SituacaoEvent evento, Long vagaId,
      StateMachine<SituacaoState, SituacaoEvent> sm) {
    Message msg = MessageBuilder.withPayload(evento).setHeader(VAGA_ID_HEADER, vagaId).build();
    sm.sendEvent(msg);
  }

  private StateMachine<SituacaoState, SituacaoEvent> build(Long vagaId) {
    Vaga vaga = vagaRepository.getOne(vagaId);
    String id = Long.toString(vaga.getId());
    StateMachine<SituacaoState, SituacaoEvent> sm = stateMachineFactory.getStateMachine(id);
    sm.stop();

    sm.getStateMachineAccessor().doWithAllRegions(sma -> {
      sma.addStateMachineInterceptor(vagaStateChangeInterceptor);
      sma.resetStateMachine(new DefaultStateMachineContext<>(vaga.getStatus(), null, null, null));
    });

    sm.start();
    return sm;
  }
}
