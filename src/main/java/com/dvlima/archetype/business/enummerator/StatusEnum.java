package com.dvlima.archetype.business.enummerator;

/**
 * 1) RASCUNHO  - salvar
 * <p>
 * 2) PUBLICADO - status atual = rascunho, torna visível para todos.
 * <p>
 * 3) ABERTO - status atual = publicado e datas ok
 * <p>
 * 4) ENCERRADO - Status que possui como base 1 dia após o término do processo seletivo (finalizada
 * todas as etapas).
 * <p>
 * 5) ANDAMENTO - Status que possui como base 1 dia após o término do período de inscrição até data
 * término da última etapa do processo seletivo.
 * <p>
 * 6) INATIVO - Quando couber, ao acionar essa opção o Administrador e/ou Gestor de Oportunidades
 * poderá inativar a vaga. (Sempre informando a justificativa)
 */
public enum StatusEnum {

}
