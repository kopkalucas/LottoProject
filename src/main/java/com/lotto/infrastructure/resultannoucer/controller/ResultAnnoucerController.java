package com.lotto.infrastructure.resultannoucer.controller;

import com.lotto.domain.resultannoucer.ResultAnnouncerFacade;
import com.lotto.domain.resultannoucer.dto.ResultAnnouncerResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResultAnnoucerController {

    private final ResultAnnouncerFacade resultAnnouncerFacade;

    public ResultAnnoucerController(ResultAnnouncerFacade resultAnnouncerFacade) {
        this.resultAnnouncerFacade = resultAnnouncerFacade;
    }

    @GetMapping("/results")
    public ResultAnnouncerResponseDto checkResults(@RequestParam String id) {
        ResultAnnouncerResponseDto resultAnnouncerResponseDto = resultAnnouncerFacade.checkResult(id);
        return resultAnnouncerResponseDto;
    }

}
