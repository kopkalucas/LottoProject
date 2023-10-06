package com.lotto.domain.numbergenerator;

import com.lotto.domain.numbergenerator.dto.WinningNumbersDto;
import com.lotto.domain.numberreciver.NumberReceiverFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@Component
public class NumberGenaratorFacade {

    private final RandomNumberGenerable winningNumberGenerator;
    private final WinningNumberValidator winningNumberValidator;
    private final WinningNumbersRepository winningNumbersRepository;
    private final NumberReceiverFacade numberReceiverFacade;


    public WinningNumbersDto generateWinningNumbers() {
        LocalDateTime nextDrawDate = numberReceiverFacade.retrieveNextDrawDate();
        Set<Integer> winningNumbers = winningNumberGenerator.genereteSixRandomNumbers();
        winningNumberValidator.validate(winningNumbers);
        WinningNumbers build = WinningNumbers.builder()
                .winningNumbers(winningNumbers)
                .date(nextDrawDate)
                .build();
        winningNumbersRepository.save(build);
        return WinningNumbersDto.builder()
                .winningNumbers(winningNumbers)
                .date(nextDrawDate)
                .build();
    }

    public WinningNumbersDto retrieveWinningNumberByDate(LocalDateTime date) {
        WinningNumbers numbersByDate = winningNumbersRepository.findNumbersByDate(date);
        return WinningNumbersDto.builder()
                .winningNumbers(numbersByDate.getWinningNumbers())
                .date(numbersByDate.getDate())
                .build();
    }

    public boolean areWinningNumbersGeneratedByDate() {
        LocalDateTime nextDrawDate = numberReceiverFacade.retrieveNextDrawDate();
        return winningNumbersRepository.existsByDate(nextDrawDate);
    }
}
