package org.example;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
@Scope(value = "prototype")
public class UserNumberValidator {

    Set<Integer> userNumbers = new HashSet<>();

    public InputNumbersResult validate(Set<Integer> numbers) {
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
