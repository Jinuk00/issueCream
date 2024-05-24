package com.clova.issuecream.proxyTest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TestController {

    @PostMapping("/test/proxy")
    public String testProxy2() {
        return "성공2";
    }

    @PostMapping("/api/test/proxy")
    public String testProxy3() {
        return "성공3";
    }
}
