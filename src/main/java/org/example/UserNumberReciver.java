package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserNumberReciver {

    List<Integer> userNumbers = new ArrayList<>();
    InputReceivable inputReceivable;

    public UserNumberReciver(InputReceivable inputReceivable) {
        this.inputReceivable = inputReceivable;
    }

    public InputNumbersResult inputNumbers() {
        System.out.println("!!!Welcome in the Lotto Game!!!\n");
        System.out.println("Please enter number from range (1-49)");

        Set<Integer> numbers = inputReceivable.inputSixNumbers();
        for (Integer number : numbers) {
            if (number >= 1 && number <= 49 && !userNumbers.contains(number)) {
                userNumbers.add(number);
            } else {
                System.out.printf("ERROR: %d is not in range (1-49)! or You have already entered the number to your list. Please try again.%n", number);
                return new InputNumbersResult(userNumbers, false);
            }
        }
        return new InputNumbersResult(userNumbers, true);
    }

}
