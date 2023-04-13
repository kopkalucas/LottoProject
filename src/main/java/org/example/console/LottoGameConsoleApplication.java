package org.example.console;

import java.util.List;
import org.example.Game;
import org.example.InputNumbersResult;
import org.example.InputReceivable;
import org.example.LottoNumberGenerator;
import org.example.UserNumberReciver;
import org.example.console.ui.ScannerInputReceiver;

public class LottoGameConsoleApplication {
    public static void main(String[] args) {
        InputReceivable scannerInputReceiver = new ScannerInputReceiver();
        UserNumberReciver userNumberReciver = new UserNumberReciver(scannerInputReceiver);
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();

        InputNumbersResult inputNumbersResult = userNumberReciver.inputNumbers();
        if (!inputNumbersResult.isValid()){
//            String message = inputNumbersResult.message();
            System.out.println("something went wrong");
            return;
        }
        List<Integer> winningNumbers = lottoNumberGenerator.drawWinningNumbers();

        Game game = new Game();
        game.checkResult(inputNumbersResult.inputNumbers(), winningNumbers);
    }
}
