# POC Spring boot State Machine

## How to run application
---------------------------------------------
```bash
mvn clean install
mvn spring-boot:run
## Profile
mvn spring-boot:run -Dspring.profiles.active=prod
```

## Spring State Machine

- Dependências:

````xml
<dependency>
    <groupId>org.springframework.statemachine</groupId>
    <artifactId>spring-statemachine-core</artifactId>
    <version>2.1.3.RELEASE</version>
</dependency>
````

- Conceito de uma máquina de estado: 
    - `Autômatos finitos`:
        - Conjunto finito de estados.
        - Conjunto de entradas.
        - Estado inicial.
        - Estado final.
        - Função de transição.

- Terminologia de uma máquina de estado:
    - `States`: O estado específico da máquina de estados. Valores finitos e predeterminados.
    - `Events`: Algo que acontece com o sistema - pode ou não alterar o estado.
    - `Action`: A resposta da Máquina de Estado aos eventos. Pode estar mudando variáveis, chamando um método ou mudando para um estado diferente.
        - `Transitions`: Tipo de ação que muda de estado. 
    - `Guards`: Condições booleanas.
    - `Extended State`: Variáveis ​​de máquina de estado (além do estado). 

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


