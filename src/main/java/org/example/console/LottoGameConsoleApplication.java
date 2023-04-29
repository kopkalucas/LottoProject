package org.example.console;

import java.util.List;
import java.util.Set;

import org.example.*;
import org.example.console.ui.ScannerInputReceiver;



public class LottoGameConsoleApplication {
    public static void main(String[] args) {
        InputReceivable scannerInputReceiver = new ScannerInputReceiver();
        NumberGenerable lottoNumberGenerator = new LottoNumberGenerator();
        UserNumberValidator userNumberValidator = new UserNumberValidator();

        Set<Integer> inputNumbersResult = scannerInputReceiver.inputSixNumbers();

        LottoGame lottoGame = new LottoGame(lottoNumberGenerator,userNumberValidator);
        System.out.println(lottoGame.play(inputNumbersResult).getResultMessege());
    }
}
