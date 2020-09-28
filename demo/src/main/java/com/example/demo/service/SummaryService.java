package com.example.demo.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Random;

@Slf4j
@Service
public class SummaryService {
    @SneakyThrows
    public Mono<Integer> getRandomInteger(Mono<String> requestId) {
        Thread.sleep(2500);
        var threadID = Thread.currentThread().getId();
        log.info("SummaryService:requestId -> {} in thread {}", requestId.block(), threadID);
        return Mono.just(new Random().nextInt());
    }
}
