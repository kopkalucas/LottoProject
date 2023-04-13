package org.example;

import java.util.ArrayList;
import java.util.Random;

public class LottoNumberGenerator {
    private final ArrayList<Integer> winningNumbers = new ArrayList<>();
    private final Random random = new Random();

    public ArrayList<Integer> drawWinningNumbers() {
        for (int i = 0; i < 6; i++) {
            while (true) {
                int winningNumber = random.nextInt(49) + 1;
                if (!winningNumbers.contains(winningNumber)) {
                    winningNumbers.add(winningNumber);
                    break;
                }
            }
        }
        return winningNumbers;
    }

}
