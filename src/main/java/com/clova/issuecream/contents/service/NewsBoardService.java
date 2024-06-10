package com.clova.issuecream.contents.service;

import com.clova.issuecream.contents.repository.NewsBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsBoardService {
    private final NewsBoardRepository newsBoardRepository;
}
