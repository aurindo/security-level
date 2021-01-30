package br.com.aurindo.scoutuser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.net.MalformedURLException;
import java.net.URL;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers;

    private final String BASE_URL = "http://localhost:";

    protected String mountUrl(String context) throws MalformedURLException {
        return new URL(getBaseUrl() + getPort() + context).toString();
    }

    protected HttpHeaders mountHeaders() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    protected HttpHeaders getHeaders() {
        return headers;
    }

    protected String getBaseUrl() {
        return BASE_URL;
    }

    protected int getPort() {
        return port;
    }

    protected TestRestTemplate getRestTemplate() {
        return restTemplate;
    }
}
