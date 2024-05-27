package com.memtize.image.service.service;

import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class I18N {

  public static final Locale DEFAULT_LOCALE = new Locale("pl");

  private final MessageSource messageSource;

  private static MessageSource staticMessageSource;

  public static String getErrorMessage(String message, Object... params) {
    return getMessage("error." + message, params);
  }

  public static String getMessage(String message, Object... params) {
    try {
      return staticMessageSource.getMessage(message, params, LocaleContextHolder.getLocale());
    } catch (NoSuchMessageException e) {
      log.error(e.getMessage(), e);
      if (!DEFAULT_LOCALE.getLanguage().equals(LocaleContextHolder.getLocale().getLanguage())) {
        try {
          return staticMessageSource.getMessage(message, params, DEFAULT_LOCALE);
        } catch (NoSuchMessageException ex) {
          log.error(ex.getMessage(), ex);
          return message;
        }
      } else {
        return message;
      }
    }
  }

  public static String getMessage(String message, Exception exception, Object... params) {
    try {
      return getMessage(message, params);
    } catch (NoSuchMessageException e) {
      log.error(e.getMessage(), e);
      return exception != null ? exception.getMessage() : "error";
    }
  }

  public static String getEntityName(Class<?> entity) {
    return getMessage(entity.getName());
  }

}
