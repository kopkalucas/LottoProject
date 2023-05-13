package org.example;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
@RequestScope
public class LottoNumberGenerator implements NumberGenerable {
    private final Set<Integer> winningNumbers;
    private final Random random;

    public LottoNumberGenerator(Set<Integer> winningNumbers, Random random) {
        this.winningNumbers = winningNumbers;
        this.random = random;
    }

    @Override
    public Set<Integer> drawWinningNumbers() {
        for (int i = 0; i < 6; i++) {
            Set<Integer> winningNumbers = new HashSet<>();
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
