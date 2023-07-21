package com.lotto.resultchecker;

import com.lotto.numbergenerator.NumberGenaratorFacade;
import com.lotto.numberreciver.CuponDto;
import com.lotto.numberreciver.NumberReciverFacade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ResultCheckerFacade {
    private NumberReciverFacade numberReciverFacade;
    private NumberGenaratorFacade numberGenaratorFacade;

    public ResultCheckerFacade(NumberReciverFacade numberReciverFacade, NumberGenaratorFacade numberGenaratorFacade) {
        this.numberReciverFacade = numberReciverFacade;
        this.numberGenaratorFacade = numberGenaratorFacade;
    }

    public List<CuponDto> checkWinners() {
        LocalDateTime drawDate = numberReciverFacade.retriveDrawDate();
        Set<CuponDto> cuponsForDrawDate = numberReciverFacade.retriveNumbersForDate(drawDate);
        Set<Integer> wonNumbersForDrawDate = numberGenaratorFacade.retriveWonNumbersForDrawDate(drawDate);
        List<CuponDto> winnigCupons = new ArrayList<>();

        for (CuponDto cupon : cuponsForDrawDate) {
            int matchingNumbersCount = 0;
            for(Integer number : cupon.numbers()) {
                if(wonNumbersForDrawDate.contains(number)){
                    matchingNumbersCount++;
                }
            }
            if(matchingNumbersCount >= 3) {
                winnigCupons.add(cupon);
            }
        }
        return winnigCupons;
    }

}
