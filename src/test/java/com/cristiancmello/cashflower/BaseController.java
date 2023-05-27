package com.cristiancmello.cashflower;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseController {
  @Autowired
  protected TestRestTemplate restTemplate;

  @LocalServerPort
  private int serverPort;

  protected URI uri;

  protected HttpHeaders headers;

  protected String resourceRootPath;

  @BeforeEach
  public void setUp() throws URISyntaxException {
    var baseUrl = "http://localhost:" + serverPort + resourceRootPath;
    uri = new URI(baseUrl);

    headers = new HttpHeaders();

    headers.setContentType(MediaType.APPLICATION_JSON);
  }
}
