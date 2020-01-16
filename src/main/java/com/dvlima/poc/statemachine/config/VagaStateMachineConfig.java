package com.dvlima.poc.statemachine.config;

import static com.dvlima.poc.enummerator.SituacaoEvent.CANCELAMENTO;
import static com.dvlima.poc.enummerator.SituacaoEvent.CANDIDATURA;
import static com.dvlima.poc.enummerator.SituacaoEvent.ENCERRAMENTO;
import static com.dvlima.poc.enummerator.SituacaoEvent.INATIVACAO;
import static com.dvlima.poc.enummerator.SituacaoEvent.PRORROGACAO;
import static com.dvlima.poc.enummerator.SituacaoEvent.PUBLICACAO;
import static com.dvlima.poc.enummerator.SituacaoEvent.TRIAGEM;
import static com.dvlima.poc.enummerator.SituacaoState.ABERTA;
import static com.dvlima.poc.enummerator.SituacaoState.ANALISE;
import static com.dvlima.poc.enummerator.SituacaoState.CANCELADA;
import static com.dvlima.poc.enummerator.SituacaoState.ENCERRADA;
import static com.dvlima.poc.enummerator.SituacaoState.INATIVA;
import static com.dvlima.poc.enummerator.SituacaoState.PUBLICADA;
import static com.dvlima.poc.enummerator.SituacaoState.RASCUNHO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import com.dvlima.poc.enummerator.SituacaoEvent;
import com.dvlima.poc.enummerator.SituacaoState;

import java.util.EnumSet;

@Slf4j
@RequiredArgsConstructor
@EnableStateMachineFactory
@Configuration
public class VagaStateMachineConfig extends
    StateMachineConfigurerAdapter<SituacaoState, SituacaoEvent> {

  private final Action<SituacaoState, SituacaoEvent> encerramentoAction;
  private final Guard<SituacaoState, SituacaoEvent> prorrogacaoGuard;

  /**
   * Configura os Estados de uma Vaga
   *
   * @param states caracterista de cada estado
   * @throws Exception
   */
  @Override
  public void configure(StateMachineStateConfigurer<SituacaoState, SituacaoEvent> states)
      throws Exception {
    states.withStates()
        .initial(RASCUNHO)
        .states(EnumSet.allOf(SituacaoState.class))
        .end(INATIVA)
        .end(ENCERRADA)
        .end(CANCELADA);
  }

  /**
   * Configura os Eventos de uma Vaga
   *
   * @param transitions mapeamento de transição de estados
   * @throws Exception
   */
  @Override
  public void configure(StateMachineTransitionConfigurer<SituacaoState, SituacaoEvent> transitions)
      throws Exception {
    transitions.withExternal()
        .source(RASCUNHO).target(INATIVA).event(INATIVACAO)
        .and().withExternal()

        .source(RASCUNHO).target(PUBLICADA).event(PUBLICACAO)
        .and().withExternal()

        .source(PUBLICADA).target(PUBLICADA).event(PRORROGACAO)
        .guard(prorrogacaoGuard)
        .and().withExternal()

        .source(PUBLICADA).target(ABERTA).event(CANDIDATURA)
        .and().withExternal()

        .source(ABERTA).target(ANALISE).event(TRIAGEM)
        .and().withExternal()

        .source(ANALISE).target(ENCERRADA)
        .event(ENCERRAMENTO)
        .action(encerramentoAction)
        .and().withExternal()

        .source(ANALISE).target(CANCELADA).event(CANCELAMENTO);
  }

  @Override
  public void configure(StateMachineConfigurationConfigurer<SituacaoState, SituacaoEvent> config)
      throws Exception {
    StateMachineListenerAdapter<SituacaoState, SituacaoEvent> adapter = new StateMachineListenerAdapter<>() {
      @Override
      public void stateChanged(State<SituacaoState, SituacaoEvent> from,
          State<SituacaoState, SituacaoEvent> to) {
        log.info(String.format("estado alterado(de: %s, para: %s)", from, to));
      }
    };

    config.withConfiguration().listener(adapter);
  }
}
