package org.example;

import java.util.ArrayList;

public class Game {

    private final ArrayList<Integer> winningNumbers = new LottoNumberGenerator().winningNumber();
    private final ArrayList<Integer> userNumbers = new UserNumberReciver().inputNumbers();





    public void resultCheck(){


        int result = 0;

        for (int i = 0; i < userNumbers.size(); i++) {
            for (int j = 0; j < winningNumbers.size(); j++) {
                if (userNumbers.get(i) == winningNumbers.get(j)){
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
