package org.example;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Set;

@Component
@AllArgsConstructor
@RequestScope
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
