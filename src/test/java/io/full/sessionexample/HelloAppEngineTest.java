package io.full.sessionexample;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Assert;
import org.junit.Test;

import io.full.sessionexample.auth.LoginController;

public class HelloAppEngineTest {

  @Test
  public void test() throws IOException, ServletException {
    MockHttpServletResponse response = new MockHttpServletResponse();
    new LoginController().doGet(null, response);
    Assert.assertEquals("text/plain", response.getContentType());
    Assert.assertEquals("UTF-8", response.getCharacterEncoding());
    Assert.assertEquals("Hello App Engine!\r\n", response.getWriterContent().toString());
  }
}
