package br.com.aurindo.scoutuser.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health-check")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<String> listAll() {

        String response = "I am Scout User app and I am alive!!";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
