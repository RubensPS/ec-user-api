package com.letscode.ecusuarioapi.gateway;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CartGateway {

    private final RestTemplate restTemplate;

    public ResponseEntity<Long> addCart(Long userId) {

        String url =String.format("http://cartAPI:8080/cart/%s", userId);
        restTemplate.getForEntity(url, String.class);
        return restTemplate.postForEntity(url, , Long.class);
    }

}
