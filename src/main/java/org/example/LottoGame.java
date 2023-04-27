package org.example;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class LottoGame {


    private final NumberGenerable lottoNumberGenerator;
    private final UserNumberValidator userNumberValidator;


    public GameResult play(Set<Integer> numbers) {

        InputNumbersResult validate = userNumberValidator.validate(numbers);
        if (!validate.isValid()){
            return new GameResult(false, "something went wrong");
        }
        Set<Integer> winningNumbers = lottoNumberGenerator.drawWinningNumbers();
        return new Game().checkResult(validate.inputNumbers(), winningNumbers);
    }

}
