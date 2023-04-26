package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LottoNumberGenerator implements NumberGenerable {
    private final Set<Integer> winningNumbers = new HashSet<>();
    private final Random random = new Random();

    @Override
    public Set<Integer> drawWinningNumbers() {
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
