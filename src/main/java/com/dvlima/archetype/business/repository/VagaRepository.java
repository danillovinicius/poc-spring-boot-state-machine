package com.dvlima.archetype.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dvlima.archetype.business.entity.Vaga;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {

}
