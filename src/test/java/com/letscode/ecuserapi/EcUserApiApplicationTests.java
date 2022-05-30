package com.letscode.ecuserapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@EnableEurekaClient
@ActiveProfiles("test")
class EcUserApiApplicationTests {

    @Test
    void contextLoads() {
    }

}
