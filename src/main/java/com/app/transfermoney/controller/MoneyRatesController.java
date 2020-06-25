package com.app.transfermoney.controller;

import com.app.transfermoney.model.Rates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/money")
public class MoneyRatesController {

    @GetMapping("/rates")
    public Rates getRates() {
        String url = "http://data.fixer.io/api/latest?access_key=61060a053f4283ca9f97d87df091dd0a&format=1";

        RestTemplate restTemplate = new RestTemplate();
        Rates rates = restTemplate.getForObject(url, Rates.class);

//        ResponseEntity<Rates> rateResponse =
//                restTemplate.exchange(url,
//                        HttpMethod.GET, null, new ParameterizedTypeReference<Rates>() {
//                        });

//        Rates rates = rateResponse.getBody();
        return rates;
    }
}
