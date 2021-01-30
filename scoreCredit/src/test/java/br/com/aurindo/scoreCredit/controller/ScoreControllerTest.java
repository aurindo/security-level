package br.com.aurindo.scoreCredit.controller;

import org.junit.jupiter.api.BeforeEach;

import java.net.MalformedURLException;

public class ScoreControllerTest extends BaseControllerTest {

    private String url;
    private final String URL_BASE_USER = "/scout-user/";

    @BeforeEach
    public void initEachTest() throws MalformedURLException {
        mountHeaders();
        url = mountUrl(URL_BASE_USER);
    }

}
