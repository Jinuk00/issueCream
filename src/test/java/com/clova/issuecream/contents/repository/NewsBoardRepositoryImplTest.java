package com.clova.issuecream.contents.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NewsBoardRepositoryImplTest {

    @Autowired
    NewsBoardRepository newsBoardRepository;

    @Test
    void 랜덤뽑기() {
        List<Long> random = newsBoardRepository.findRandom();
        System.out.println(random);
    }
}