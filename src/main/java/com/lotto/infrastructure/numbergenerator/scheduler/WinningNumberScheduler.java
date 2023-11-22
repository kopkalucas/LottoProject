package com.lotto.infrastructure.numbergenerator.scheduler;

import com.lotto.domain.numbergenerator.NumberGenaratorFacade;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Component
@Log4j2
public class WinningNumberScheduler {


    private final NumberGenaratorFacade numberGenaratorFacade;

    public WinningNumberScheduler(NumberGenaratorFacade numberGenaratorFacade) {
        this.numberGenaratorFacade = numberGenaratorFacade;
    }


    @Scheduled(cron = "${winning.number.scheduler}")
    public void generate() {
        if(!numberGenaratorFacade.areWinningNumbersGeneratedByDate()){
            log.info("Generating winning numbers");
            numberGenaratorFacade.generateWinningNumbers();
        }
    }
}
