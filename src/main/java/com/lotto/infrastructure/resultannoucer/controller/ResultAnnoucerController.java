package com.lotto.infrastructure.resultannoucer.controller;

import com.lotto.domain.resultannoucer.ResultAnnouncerFacade;
import com.lotto.domain.resultannoucer.dto.ResultAnnouncerResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ResultAnnoucerController {

    private final ResultAnnouncerFacade resultAnnouncerFacade;


    @GetMapping("/results/{id}")
    public ResponseEntity<ResultAnnouncerResponseDto> checkResultsById(@PathVariable String id) {
        ResultAnnouncerResponseDto resultAnnouncerResponseDto = resultAnnouncerFacade.checkResult(id);
        return ResponseEntity.ok(resultAnnouncerResponseDto);
    }

}
