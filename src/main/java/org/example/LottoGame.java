package org.example;

import org.example.console.ui.ScannerInputReceiver;

import java.util.List;
import java.util.Set;

public class LottoGame {

    private final UserNumberReciver userNumberReciver;
    private final NumberGenerable lottoNumberGenerator;

    public LottoGame(UserNumberReciver userNumberReciver, NumberGenerable lottoNumberGenerator) {
        this.userNumberReciver = userNumberReciver;
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public GameResult play() {

        InputNumbersResult inputNumbersResult = userNumberReciver.inputNumbers();
        if (!inputNumbersResult.isValid()){
            return new GameResult(false, "something went wrong");
        }
        Set<Integer> winningNumbers = lottoNumberGenerator.drawWinningNumbers();
        return new Game().checkResult(inputNumbersResult.inputNumbers(), winningNumbers);
    }

}
