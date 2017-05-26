package com.rozenkow.listener;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by Poul Rozenkow.
 */
public class UserNameAuditor implements AuditorAware<String> {
  @Override
  public String getCurrentAuditor() {
    return SecurityContextHolder.getContext().getAuthentication().getName();
  }
}
