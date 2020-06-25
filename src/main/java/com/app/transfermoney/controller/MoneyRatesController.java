package com.app.transfermoney.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

//    @Autowired
//    private RestTemplate restTemplate;

    @GetMapping("/rates")
    public String getRates() {
        String url = "http://data.fixer.io/api/latest?access_key=61060a053f4283ca9f97d87df091dd0a&format=1";
//        String res = restTemplate.getForObject(url, String.class);

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        return result;
    }
}
