package com.dvlima.archetype.infrastructure.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageSourceConfiguration {

  @Bean(name = "global")
  public MessageSource messageSource() {
    return new YamlRessourceMessageLoader("global");
  }

  @Bean(name = "validation")
  public MessageSource validationMessageSource() {
    return new YamlRessourceMessageLoader("validation");
  }

}
