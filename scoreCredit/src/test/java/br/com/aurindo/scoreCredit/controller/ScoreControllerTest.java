package br.com.aurindo.scoreCredit.controller;

import br.com.aurindo.scoreCredit.model.Address;
import lombok.Getter;
import lombok.NonNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreControllerTest extends BaseControllerTest {

    private String url;
    private final String URL_BASE_USER = "/score-credit";

    @BeforeEach
    public void initEachTest() throws MalformedURLException {
        mountHeaders();
        url = mountUrl(URL_BASE_USER);
    }

    @Test
    public void whenRequestByCPFAndPersonNotExists() throws Exception {
        HttpEntity<String> request = new HttpEntity<>(getHeaders());
        url += "/123456789";
        ResponseEntity<String> responseGet = getRestTemplate()
                .exchange(url, HttpMethod.GET, request, String.class);

        assertEquals(HttpStatus.NOT_FOUND, responseGet.getStatusCode());
        assertEquals(
                "LastSearch was not found for parameters {cpf=123456789}",
                responseGet.getBody());
    }

    public void whenRequestByCPFAndUserExists() throws Exception {
        JSONObject perasonJO = createPersonObject();
        HttpEntity<String> request = new HttpEntity<>(perasonJO.toString(), getHeaders());
        ResponseEntity<String> responsePost = getRestTemplate()
                .exchange(url, HttpMethod.POST, request, String.class);

        request = new HttpEntity<>(getHeaders());
        url += "/" + perasonJO.getString("cpf");
        ResponseEntity<String> responseGet = getRestTemplate()
                .exchange(url, HttpMethod.GET, request, String.class);

        assertEquals(HttpStatus.OK, responseGet.getStatusCode());
        String expectedBody = "{\"cpf\":\"123456789\"," +
                "\"lastCheck\":\"2020-05-02T03:01:00.000Z\"," +
                "\"eventType\":\"Pagamento\",\"lastCredCardUse\":\"99.23\"}";
        assertEquals(expectedBody, responsePost.getBody());
    }

    private JSONObject createPersonObject() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("cpf", "123456789");
        jsonObject.put("birthDate", "1982-05-25T03:01:00.000Z");
        jsonObject.put("sourceIncome", "Pagamento");

        final JSONObject jsonObjectAddress = new JSONObject();
        jsonObjectAddress.put("city", "Fortaleza");
        jsonObject.put("address", jsonObjectAddress);

        return jsonObject;
    }

}
