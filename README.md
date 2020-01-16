# POC Spring boot State Machine

How to run application
---------------------------------------------
```bash
## Build application from base directory
mvn clean install

## Run Spring boot application with embedded H2 database
mvn spring-boot:run

## Or if You want to run application with production profile
mvn spring-boot:run -Dspring.profiles.active=prod
```

## Spring State Machine

- Dependencies:

````xml
<dependency>
    <groupId>org.springframework.statemachine</groupId>
    <artifactId>spring-statemachine-core</artifactId>
    <version>2.1.3.RELEASE</version>
</dependency>
````

- Concept of a State Machine: 
    - `Finite Automata`:
        - Finite set of states
        - Set of inputs
        - Initial State
        - Final State
        - Transition Function. 

- State Machine Terminology
    - `States`: The specific state of the state machine. Finite and predetermined values. Enum.
    - `Events`: Something that happens to the system - may or may not change the state.
    - `Action`: The response of the State Machine to events. Can be changing variables, calling a method or changing to a different state.
        - `Transitions`: Type of action which changes state. 
    - `Guards`: Boolean conditions 
    - `Extended State`: State Machine variables (in addition to state) 

## Exemplo: Candidatura em uma vaga de emprego.

- *Estados*:
    - `RASCUNHO`  : Possível realizar alterações nas informações da vaga.
    - `INATIVA`   : Uma vaga que estava em rascunho e foi deletada.
    - `PUBLICADA` : Visível para todos os candidatos. 
    - `ABERTA`    : Permite que os candidatos realizem a candidatura.
    - `ANALISE`   : Vaga em periodo de triagem dos candidatos.
    - `ENCERRADA` : Finalizado o processo para a vaga.
    - `CANCELADA` : Cancelado em triagem.

- *Eventos*: 
    - `INATIVACAO`   : Inativar uma vaga que se encontra com status RASCUNHO.
    - `PUBLICACAO`   : Publica uma vaga com status RASCUNHO.
    - `PRORROGACAO`  : Prorrogar o periodo para início para incrições em uma vaga.
    - `CANDIDATURA`  : Iniciar periodo para incrições em uma vaga.
    - `TRIAGEM`      : Iniciar periodo de análise currícular de uma vaga com status ABERTA.
    - `ENCERRAMENTO` : Finaliza a análise dos candidatos e publica os resultados de uma vaga com status ANALISE.
    - `CANCELAMENTO` : Cancela uma vaga durante a análise dos candidatos de uma vaga com status ANALISE.

````script
                        RASCUNHO
                           |
                          / \ ((INATIVACAO))
          ((PUBLICACAO)) /   \
                        /     o INATIVA
                       |
                       v
                    PUBLICADA ---> ((PRORROGACAO))
                       |   |             |
                       |    -------------     
                       |
                       | ((CANDIDATURA)) //  
                       | 
                       v 
                     ABERTA
                       |
                       | ((TRIAGEM))
                       v
                    ANALISE
                       |
                      / \
    ((ENCERRAMENTO)) /   \ ((CANCELAMENTO)) 
                    /     \
        ENCERRADA  o        o CANCELADA
````


