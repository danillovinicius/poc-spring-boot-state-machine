package com.dvlima.archetype.infrastructure.configuration;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.support.AbstractResourceBasedMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.dvlima.archetype.infrastructure.exception.ConfigurationException;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;

public class YamlRessourceMessageLoader extends AbstractResourceBasedMessageSource {

  private String fileName;

  public YamlRessourceMessageLoader(String fileName) {
    this.fileName = fileName;
  }

  @Override
  protected MessageFormat resolveCode(String code, Locale locale) {
    String filePath = "messages/" + fileName;

    if (locale.getLanguage().equals(Locale.getDefault().getLanguage())) {
      filePath = filePath + ".yml";
    } else {
      filePath = filePath + "_" + locale.toLanguageTag() + ".yml";
    }

    try {
      Resource resource = new ClassPathResource(filePath);

      YamlPropertiesFactoryBean yamlBeanFactory = new YamlPropertiesFactoryBean();
      yamlBeanFactory.setResources(resource);

      Properties propertieFile = yamlBeanFactory.getObject();

      return new MessageFormat(propertieFile.getProperty(code), locale);
    } catch (Exception ex) {
      throw new ConfigurationException("Erro ao resolver internacionalização (i18n)!", ex);
    }
  }

}