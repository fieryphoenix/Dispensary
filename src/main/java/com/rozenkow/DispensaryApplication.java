package com.rozenkow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@SpringBootApplication
public class DispensaryApplication extends WebMvcConfigurerAdapter {

  private static final String DEFAULT_LOCALE = "ru";

  public static void main(String[] args) {
    SpringApplication.run(DispensaryApplication.class, args);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {

    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    localeChangeInterceptor.setParamName("language");

    return localeChangeInterceptor;
  }

  @Bean(name = "localeResolver")
  public CookieLocaleResolver localeResolver() {

    CookieLocaleResolver localeResolver = new CookieLocaleResolver();
    Locale defaultLocale = new Locale(DEFAULT_LOCALE);
    localeResolver.setDefaultLocale(defaultLocale);

    return localeResolver;
  }

  @Bean
  public ReloadableResourceBundleMessageSource messageSource() {

    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:lang/messages");
    messageSource.setCacheSeconds(10); //reload messages every 10 seconds

    return messageSource;
  }
}
