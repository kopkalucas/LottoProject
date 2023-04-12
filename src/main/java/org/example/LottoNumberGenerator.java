package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LottoNumberGenerator {
    private ArrayList<Integer> winningNumbers = new ArrayList<>();
    private Random random = new Random();

    public ArrayList<Integer> winningNumber() {

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
