package com.dvlima.archetype.infrastructure.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ValidationMessageHandler {

  @Autowired
  @Qualifier("validation")
  private MessageSource messageSource;

  public String getMessage(String message, Locale locale, Object... args) {
    return messageSource.getMessage(message, args, locale);
  }

  public String getMessage(String message, Object... args) {
    return messageSource.getMessage(message, args, Locale.getDefault());
  }

}
