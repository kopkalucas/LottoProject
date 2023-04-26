package org.example.console;

import java.util.List;

import org.example.*;
import org.example.console.ui.ScannerInputReceiver;

public class LottoGameConsoleApplication {
    public static void main(String[] args) {
        InputReceivable scannerInputReceiver = new ScannerInputReceiver();
        UserNumberReciver userNumberReciver = new UserNumberReciver(scannerInputReceiver);
        NumberGenerable lottoNumberGenerator = new LottoNumberGenerator();
        LottoGame lottoGame = new LottoGame(userNumberReciver,lottoNumberGenerator);
        System.out.println(lottoGame.play().getResultMessege());
    }
}
