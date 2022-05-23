package com.letscode.ecuserapi.gateway;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CartGateway {

    private final RestTemplate restTemplate;

    public ResponseEntity<String> addCart(Long userId) {
        String url =String.format("http://cartAPI:8080/cart/%s", userId);
        //HttpEntity<String> request = new HttpEntity<>(null, null);
        return restTemplate.postForEntity(url, null, String.class);
    }

}
