package com.clova.issuecream.news.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NewsTransferServiceTest {
    @Autowired
    NewsTransferService newsTransferService;

    @Test
    void test() {
        newsTransferService.transNews();
    }
}