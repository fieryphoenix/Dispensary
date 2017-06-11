package com.rozenkow.util;

import org.junit.Test;
import org.springframework.data.util.Pair;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

/**
 * Created by Poul Rozenkow.
 */
public class EncryptUtilsTest {

  @Test
  public void hashAndSaltAreCreated() throws Exception {
    Pair<String, String> hashSaltForPassword = EncryptUtils.createHashSaltForPassword("admin", null);

    assertThat(hashSaltForPassword.getFirst(), not(isEmptyOrNullString()));
    assertThat(hashSaltForPassword.getSecond(), not(isEmptyOrNullString()));
  }

  @Test
  public void hashAndSaltAreSameByInputSalt() throws Exception {
    Pair<String, String> hashSaltForPassword = EncryptUtils.createHashSaltForPassword("Rozenkow1!", "-6384650620671928090");

    assertThat("Incorrect hash", hashSaltForPassword.getFirst(), equalTo("cLEcKpY8k+ZTCQ=="));
    assertThat("Incorrect Salt", hashSaltForPassword.getSecond(), equalTo("-6384650620671928090"));
  }
}