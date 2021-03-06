package com.dvlima.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dvlima.poc.entity.Vaga;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {

}
