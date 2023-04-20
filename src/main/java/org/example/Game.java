package org.example;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Game {

    public static final int WINNER_HIT_COUNT = 3;

    public Game() {
    }

    public GameResult checkResult(Set<Integer> userNumbers, Set<Integer> winningNumbers) {
        int result = 0;

        for (Integer userNumber : userNumbers) {
            for (Integer winningNumber : winningNumbers) {
                if (Objects.equals(userNumber, winningNumber)) {
                    result++;
                }
            }
        }
        if (result >= WINNER_HIT_COUNT) {
            System.out.println("\n!!You won a lottery!! Congratulation\n");
            printSummaryMessage(userNumbers, winningNumbers);
            return new GameResult(true);
        }
        System.out.println("This time you lose. Next time will be better :)\n");
        printSummaryMessage(userNumbers, winningNumbers);
        return new GameResult(false);
    }

    private void printSummaryMessage(Set<Integer> userNumbers, Set<Integer> winningNumbers) {
        System.out.printf("Winning numbers: %s%n", userNumbers);
        System.out.printf("Your numbers: %s%n", winningNumbers);
    }
}
