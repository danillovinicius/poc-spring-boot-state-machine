package com.dvlima.archetype.infrastructure.abstraction.validation;

import java.util.Optional;

interface Validator<K> {

  Optional<ValidationError> validate(K k);

}
