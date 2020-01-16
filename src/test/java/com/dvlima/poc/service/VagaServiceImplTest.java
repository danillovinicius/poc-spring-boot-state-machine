package com.dvlima.poc.service;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.context.SpringBootTest;

import com.dvlima.poc.entity.Vaga;
import com.dvlima.poc.repository.VagaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.transaction.annotation.Transactional;

import com.dvlima.poc.enummerator.SituacaoEvent;
import com.dvlima.poc.enummerator.SituacaoState;

@SpringBootTest
class VagaServiceImplTest {

  @Autowired
  VagaService vagaService;

  @Autowired
  VagaRepository vagaRepository;

  Vaga vaga;

  @BeforeEach
  void setUp() {
    vaga = Vaga.builder()
        .nome("Vaga Executiva")
        .ativo(true)
        .build();
  }

  @Transactional
  @Test
  public void publicarVaga() {
    Vaga savedVaga = vagaService.criarNovaVaga(vaga);

    System.out.println("Should be RASCUNHO");
    System.out.println(savedVaga.getStatus());

    StateMachine<SituacaoState, SituacaoEvent> sm = vagaService.publicarVaga(savedVaga.getId());

    Vaga vagaPublicada = vagaRepository.getOne(savedVaga.getId());

    System.out.println("Should be PUBLICADA");
    System.out.println(vagaPublicada);
    assertSame(SituacaoState.PUBLICADA, vagaPublicada.getStatus());
  }


  @Transactional
  @RepeatedTest(5)
  public void inativarVaga() {
    Vaga savedVaga = vagaService.criarNovaVaga(vaga);

    StateMachine<SituacaoState, SituacaoEvent> sm = vagaService.inativarVaga(savedVaga.getId());
    Vaga vagaInativada = vagaRepository.getOne(savedVaga.getId());

    assertSame(sm.getState().getId(), vagaInativada.getStatus());
    assertSame(SituacaoState.INATIVA, vagaInativada.getStatus());
    System.out.println("INATIVA");
    System.out.println(vagaInativada);
  }

}