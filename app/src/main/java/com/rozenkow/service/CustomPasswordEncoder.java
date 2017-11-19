package com.rozenkow.service;

import com.rozenkow.util.EncryptUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.encoding.BasePasswordEncoder;

/**
 * Created by Poul Rozenkow.
 */
public class CustomPasswordEncoder extends BasePasswordEncoder {

  private final Logger logger = LoggerFactory.getLogger(CustomPasswordEncoder.class);

  @Override
  public String encodePassword(String rawPass, Object salt) {
    try {
      Pair<String, String> hashSaltForPassword = EncryptUtils.createHashSaltForPassword(rawPass, (String) salt);
      return hashSaltForPassword.getFirst();
    } catch (Exception e) {
      logger.error("Encode password failed", e);
    }
    return null;
  }

  @Override
  public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
    String encPassToCheck = encodePassword(rawPass, salt);
    return StringUtils.equals(encPass, encPassToCheck);
  }
}
