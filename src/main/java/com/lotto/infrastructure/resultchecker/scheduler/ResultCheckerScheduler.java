package com.lotto.infrastructure.resultchecker.scheduler;

import com.lotto.domain.resultchecker.ResultCheckerFacade;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ResultCheckerScheduler {

    private final ResultCheckerFacade resultCheckerFacade;

    public ResultCheckerScheduler(ResultCheckerFacade resultCheckerFacade) {
        this.resultCheckerFacade = resultCheckerFacade;
    }

    @Scheduled(cron = "${result.checker.scheduler}")
    public void checkWinners() {
        log.info("Result Checking");
        resultCheckerFacade.generateResults();
    }

}
