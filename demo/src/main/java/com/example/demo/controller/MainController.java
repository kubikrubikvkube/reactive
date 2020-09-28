package com.example.demo.controller;

import com.example.demo.service.CalculationService;
import com.example.demo.service.DatabaseService;
import com.example.demo.service.SummaryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
public class MainController {
    private final DatabaseService databaseService;
    private final CalculationService calculationService;
    private final SummaryService summaryService;

    public MainController(DatabaseService databaseService, CalculationService calculationService, SummaryService summaryService) {
        this.databaseService = databaseService;
        this.calculationService = calculationService;
        this.summaryService = summaryService;
    }

    @RequestMapping("/random")
    public Mono<Integer> getRandom() {
        Mono<String> uuid = Mono.just(UUID.randomUUID().toString());
        return uuid
                .then(databaseService.getRandomInteger(uuid))
                .then(calculationService.getRandomInteger(uuid))
                .then(summaryService.getRandomInteger(uuid));
    }
}
