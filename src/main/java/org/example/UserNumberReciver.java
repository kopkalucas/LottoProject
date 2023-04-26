package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserNumberReciver {

    Set<Integer> userNumbers = new HashSet<>();
    InputReceivable inputReceivable;

    public UserNumberReciver(InputReceivable inputReceivable) {
        this.inputReceivable = inputReceivable;
    }

    public InputNumbersResult inputNumbers() {
        Set<Integer> numbers = inputReceivable.inputSixNumbers();
        for (Integer number : numbers) {
            if (number >= 1 && number <= 49 && !userNumbers.contains(number) && numbers.size() == 6) {
                userNumbers.add(number);
            } else {
                System.out.println("ERROR: Numbers are incorrect!");
                return new InputNumbersResult(userNumbers, false);
            }
        }
        return new InputNumbersResult(userNumbers, true);
    }

}
