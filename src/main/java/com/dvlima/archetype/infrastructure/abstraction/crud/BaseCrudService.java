package com.dvlima.archetype.infrastructure.abstraction.crud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BaseCrudService<T, K> {

  T create(T entity);

  Optional<T> delete(K id);

  List<T> findAll();

  Page<T> findAll(Pageable var1);

  Optional<T> findById(K id);

  T update(T entity);
}
