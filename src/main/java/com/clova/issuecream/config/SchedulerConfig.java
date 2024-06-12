package com.clova.issuecream.config;

import com.clova.issuecream.news.service.NewsTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchedulerConfig {
    private final NewsTransferService newsTransferService;

//    @Scheduled(cron = "")
    public void run() {
        newsTransferService.transNews();
    }
}
