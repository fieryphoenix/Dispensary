package com.rozenkow.util;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.util.Pair;

import javax.crypto.SecretKeyFactory;
import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * Created by Poul Rozenkow.
 */
public class EncryptUtils {
  private static final int ROUNDS = 20;
  private static final String ALGORITHM = "PBKDF2WithHmacSHA1";

  @NotNull
  public static Pair<String, String> createHashSaltForPassword(@NotNull String password, @Nullable String salt) throws
      InvalidKeySpecException,
      NoSuchAlgorithmException {
    SecureRandom secureRandom = new SecureRandom();
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
    if (StringUtils.isBlank(salt)) {
      salt = String.valueOf(secureRandom.nextLong());
    }
    String passWithSalt = password + salt;
    KeySpec keySpec = new PBEKeySpec(passWithSalt.toCharArray(), salt.getBytes(), ROUNDS, password.length() * 8);
    PBEKey secretKey = (PBEKey) keyFactory.generateSecret(keySpec);

    Base64.Encoder encoder = Base64.getEncoder();
    String encodedPasswordHash = encoder.encodeToString(secretKey.getEncoded());

    return Pair.of(encodedPasswordHash, salt);
  }
}
