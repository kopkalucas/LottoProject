package org.example;

import java.util.ArrayList;
import java.util.Objects;

public class Game {

    private final ArrayList<Integer> winningNumbers = new LottoNumberGenerator().winningNumber();
    private final ArrayList<Integer> userNumbers = new UserNumberReciver().inputNumbers();





    public void resultCheck(){


        int result = 0;

        for (Integer userNumber : userNumbers) {
            for (Integer winningNumber : winningNumbers) {
                if (Objects.equals(userNumber, winningNumber)) {
                    result++;
                }
            }
        }

        if (result >= 3) {
            System.out.println("\n!!You won a lottery!! Congratulation\n");

        } else {
            System.out.println("This time you lose. Next time will be better :)\n");

        }

        System.out.printf("Winning numbers: %s%n", userNumbers);
        System.out.printf("Your numbers: %s%n", winningNumbers);
    }




}
