package com.rozenkow.util;

import org.junit.Test;
import org.springframework.data.util.Pair;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by Poul Rozenkow on 5/20/2017.
 */
public class EncryptUtilsTest {

  @Test
  public void hashAndSaltAreCreated() throws Exception {
    Pair<String, String> hashSaltForPassword = EncryptUtils.createHashSaltForPassword("Rozenkow1!", null);

    assertThat(hashSaltForPassword.getFirst(), not(isEmptyOrNullString()));
    assertThat(hashSaltForPassword.getSecond(), not(isEmptyOrNullString()));
  }

  @Test
  public void hashAndSaltAreSameByInputSalt() throws Exception {
    Pair<String, String> hashSaltForPassword = EncryptUtils.createHashSaltForPassword("Rozenkow1!", "KzANaoaAs0HZZg==");

    assertThat("Incorrect hash", hashSaltForPassword.getFirst(), equalTo("HJqihf31Jkg8fg=="));
    assertThat("Incorrect Salt", hashSaltForPassword.getSecond(), equalTo("KzANaoaAs0HZZg=="));
  }
}