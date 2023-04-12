package org.example;

import java.util.ArrayList;

public class Game {

    private final ArrayList<Integer> winningNumbers = new LottoNumberGenerator().winningNumber();
    private final ArrayList<Integer> userNumbers = new UserNumberReciver().inputNumbers();





    public void resultCheck(){

        System.out.printf("Winning numbers: %s%n", userNumbers);
        System.out.printf("Your numbers: %s%n", winningNumbers);

        userNumbers.retainAll(winningNumbers);

        if (userNumbers.size() >= 3) {
            System.out.println("!!You won a lottery!! Congratulation");

        } else {
            System.out.println("This time you lose. Next time will be better :)");

        }
    }




}
