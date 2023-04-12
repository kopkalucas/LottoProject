package org.example;

import java.util.ArrayList;
import java.util.List;

public class ResultChecker {

    private ArrayList userNumbers = new UserNumberReciver().inputNumbers();
    private ArrayList winningNumbers = new LottoNumberGenerator().winningNumber();




    public void resultCheck(){

        System.out.printf("Winning numbers: %s%n", userNumbers);
        System.out.printf("Your numbers: %s%n", winningNumbers);

        userNumbers.retainAll(winningNumbers);

        if (userNumbers.size() > 3) {
            System.out.println("!!You won a lottery!! Congratulaion");

        } else {
            System.out.println("This time you lose. Next time will be better :)");

        }
    }




}
