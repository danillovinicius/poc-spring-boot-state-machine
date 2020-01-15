package com.dvlima.archetype.infrastructure.abstraction.crud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseCrudServiceImpl<T, K extends Serializable, X extends JpaRepository<T, K>>
    implements BaseCrudService<T, K> {

  @Override
  public T create(T entity) {
    return getRepository().save(entity);
  }

  @Override
  public T update(T entity) {
    getRepository().saveAndFlush(entity);
    return null;
  }

  @Override
  public Optional<T> delete(K id) {
    Optional<T> saved = findById(id);
    saved.ifPresent(x -> this.getRepository().delete(x));
    return saved;
  }

  @Override
  public List<T> findAll() {
    return getRepository().findAll();
  }

  @Override
  public Page<T> findAll(Pageable pageable) {
    return getRepository().findAll(pageable);
  }

  @Override
  public Optional<T> findById(K id) {
    return getRepository().findById(id);
  }


  abstract protected X getRepository();
}
