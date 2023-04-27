package org.example.console;

import java.util.List;
import java.util.Set;

import org.example.*;
import org.example.console.ui.ScannerInputReceiver;



public class LottoGameConsoleApplication {
    public static void main(String[] args) {
        InputReceivable scannerInputReceiver = new ScannerInputReceiver();
        NumberGenerable lottoNumberGenerator = new LottoNumberGenerator();
        LottoGame lottoGame = new LottoGame(lottoNumberGenerator);
        Set<Integer> inputNumbersResult = scannerInputReceiver.inputSixNumbers();
        System.out.println(lottoGame.play(inputNumbersResult).getResultMessege());
    }
}
