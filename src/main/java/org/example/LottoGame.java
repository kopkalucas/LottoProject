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
//            String message = inputNumbersResult.message();
            System.out.println("something went wrong");
            return new GameResult(false);
        }
        Set<Integer> winningNumbers = lottoNumberGenerator.drawWinningNumbers();

        Game game = new Game();
        return game.checkResult(inputNumbersResult.inputNumbers(), winningNumbers);
    }

}
