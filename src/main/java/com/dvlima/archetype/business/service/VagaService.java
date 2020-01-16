package com.dvlima.archetype.business.service;

import org.springframework.statemachine.StateMachine;

import com.dvlima.archetype.business.entity.Vaga;
import com.dvlima.archetype.business.enummerator.SituacaoEvent;
import com.dvlima.archetype.business.enummerator.SituacaoState;

import java.time.LocalDateTime;

public interface VagaService {

  Vaga criarNovaVaga(Vaga vaga);

  Vaga consultarVagaPorCodigo(Long vagaId);

  StateMachine<SituacaoState, SituacaoEvent> publicarVaga(Long vagaId);

  StateMachine<SituacaoState, SituacaoEvent> inativarVaga(Long vagaId);

  StateMachine<SituacaoState, SituacaoEvent> prorrogarVaga(Long vagaId, LocalDateTime data);

  StateMachine<SituacaoState, SituacaoEvent> habilitarCandidaturas(Long vagaId);

  StateMachine<SituacaoState, SituacaoEvent> iniciarAnaliseCurricular(Long vagaId);

  StateMachine<SituacaoState, SituacaoEvent> finalizarAnaliseCurricular(Long vagaId);

  StateMachine<SituacaoState, SituacaoEvent> cancelarAnaliseCurricular(Long vagaId);

}
