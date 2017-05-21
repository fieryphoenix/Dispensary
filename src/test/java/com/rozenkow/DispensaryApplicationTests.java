package com.rozenkow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.ServletContext;
import java.net.MalformedURLException;
import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DispensaryApplicationTests {
  @Autowired
  private ServletContext servletContext;

  @Test
  public void contextLoads() throws MalformedURLException {
    URL resource = servletContext.getResource("/tld/security.tld");
  }

}
