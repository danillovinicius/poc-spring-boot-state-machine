package com.dvlima.archetype.business.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dvlima.archetype.business.entity.Vaga;
import com.dvlima.archetype.business.repository.VagaRepository;
import com.dvlima.archetype.infrastructure.abstraction.crud.BaseCrudServiceImpl;

@Slf4j
@Service
public class VagaService extends BaseCrudServiceImpl<Vaga, Long, VagaRepository> {

  private VagaRepository vagaRepository;

  @Autowired
  public VagaService(VagaRepository vagaRepository) {
    this.vagaRepository = vagaRepository;
  }

  @Override
  protected VagaRepository getRepository() {
    return this.vagaRepository;
  }
}
