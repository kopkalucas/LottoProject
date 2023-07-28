package com.lotto.resultchecker;

import com.lotto.numbergenerator.NumberGenaratorFacade;
import com.lotto.numberreciver.CuponDto;
import com.lotto.numberreciver.NumberReciverFacade;
import com.lotto.resultchecker.dto.ResultDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ResultCheckerFacade {
    private NumberReciverFacade numberReciverFacade;
    private NumberGenaratorFacade numberGenaratorFacade;
    private ResultCheckerRepository repository;

    public ResultCheckerFacade(NumberReciverFacade numberReciverFacade, NumberGenaratorFacade numberGenaratorFacade) {
        this.numberReciverFacade = numberReciverFacade;
        this.numberGenaratorFacade = numberGenaratorFacade;
    }

    public void calculateWinners() {
        LocalDateTime drawDate = numberReciverFacade.retriveDrawDate();
        Set<Integer> wonNumbersForDrawDate = numberGenaratorFacade.retriveWonNumbersForDrawDate(drawDate);
        Set<ResultEntity> cuponsForDrawDate = numberReciverFacade.retriveNumbersForDate(drawDate).stream()
                .map(cuponDto -> new ResultEntity(cuponDto.id(),cuponDto.loterryDate(),cuponDto.numbers(),wonNumbersForDrawDate,false))
                .collect(Collectors.toSet());



        for (ResultEntity cupon : cuponsForDrawDate) {
            int matchingNumbersCount = 0;
            for(Integer number : cupon.userNumbers()) {
                if(wonNumbersForDrawDate.contains(number)){
                    matchingNumbersCount++;
                }
            }
            if(matchingNumbersCount >= 3) {
                cupon.isWinner();
            }
        }
        repository.saveAll(winnigCupons);
    }


    public ResultDto checkResultById(String id) {
        Optional<CuponDto> resultById = repository.findById(id);
        if(resultById.isEmpty()){
            return new ResultDto("Not Found");
        }
        return new ResultDto("Sucess");
    }
}
