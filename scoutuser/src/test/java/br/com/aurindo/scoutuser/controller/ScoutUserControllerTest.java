package br.com.aurindo.scoutuser.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class ScoutUserControllerTest extends BaseControllerTest {

    private String url;
    private final String URL_BASE_USER = "/scout-user/";

    @BeforeEach
    public void initEachTest() throws MalformedURLException {
        mountHeaders();
        url = mountUrl(URL_BASE_USER);
        cleanRedis();
    }

    @Test
    public void whenRequestByCPFAndUserExists() throws Exception {
        JSONObject searchJO = createSearchObject();
        HttpEntity<String> request = new HttpEntity<>(searchJO.toString(), getHeaders());
        ResponseEntity<String> responsePost = getRestTemplate()
                .exchange(url, HttpMethod.POST, request, String.class);

        request = new HttpEntity<>(getHeaders());
        url += "/" + searchJO.getString("cpf");
        ResponseEntity<String> responseGet = getRestTemplate()
                .exchange(url, HttpMethod.GET, request, String.class);

        assertEquals(HttpStatus.OK, responseGet.getStatusCode());
        String expectedBody = "{\"cpf\":\"123456789\"," +
                "\"lastCheck\":\"2020-05-02T03:01:00.000Z\"," +
                "\"eventType\":\"Pagamento\",\"lastCredCardUse\":\"99.23\"}";
        assertEquals(expectedBody, responsePost.getBody());
    }

    @Test
    public void whenRequestByCPFAndUserNotExists() throws Exception {
        HttpEntity<String> request = new HttpEntity<>(getHeaders());
        url += "/123456789";
        ResponseEntity<String> responseGet = getRestTemplate()
                .exchange(url, HttpMethod.GET, request, String.class);

        assertEquals(HttpStatus.NOT_FOUND, responseGet.getStatusCode());
        assertEquals(
                "LastSearch was not found for parameters {cpf=123456789}",
                responseGet.getBody());
    }

    @Test
    public void whenAddNewSearchFirstTime() throws Exception {
        JSONObject searchJO = createSearchObject();
        HttpEntity<String> request = new HttpEntity<>(searchJO.toString(), getHeaders());
        ResponseEntity<String> responsePost = getRestTemplate()
                .exchange(url, HttpMethod.POST, request, String.class);

        assertEquals(HttpStatus.CREATED, responsePost.getStatusCode());

        List<String> locations = new ArrayList<>();
        locations.add(url + "123456789");
        assertEquals(
                locations,
                responsePost.getHeaders().get("location"));

        String expectedBody = "{\"cpf\":\"123456789\"," +
                "\"lastCheck\":\"2020-05-02T03:01:00.000Z\"," +
                "\"eventType\":\"Pagamento\",\"lastCredCardUse\":\"99.23\"}";
        assertEquals(expectedBody, responsePost.getBody());
    }

    @Test
    public void whenUpdateSearchShouldUpdateValues() throws Exception {
        JSONObject searchJO = createSearchObject();
        HttpEntity<String> request = new HttpEntity<>(searchJO.toString(), getHeaders());
        ResponseEntity<String> responsePost = getRestTemplate()
                .exchange(url, HttpMethod.POST, request, String.class);

        searchJO.put("lastCheck", "2020-05-03T03:01:00.000Z");
        searchJO.put("lastCredCardUse", "100.00");
        request = new HttpEntity<>(searchJO.toString(), getHeaders());
        responsePost = getRestTemplate()
                .exchange(url, HttpMethod.POST, request, String.class);

        assertEquals(HttpStatus.CREATED, responsePost.getStatusCode());

        List<String> locations = new ArrayList<>();
        locations.add(url + "123456789");
        assertEquals(
                locations,
                responsePost.getHeaders().get("location"));

        String expectedBody = "{\"cpf\":\"123456789\"," +
                "\"lastCheck\":\"2020-05-03T03:01:00.000Z\"," +
                "\"eventType\":\"Pagamento\",\"lastCredCardUse\":\"100.00\"}";
        assertEquals(expectedBody, responsePost.getBody());
    }

    private JSONObject createSearchObject() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("cpf", "123456789");
        jsonObject.put("lastCheck", "2020-05-02T03:01:00.000Z");
        jsonObject.put("eventType", "Pagamento");
        jsonObject.put("lastCredCardUse", "99.23");

        return jsonObject;
    }

}
