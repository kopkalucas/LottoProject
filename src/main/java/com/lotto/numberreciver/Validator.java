package com.lotto.numberreciver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class Validator {

    List<String> validateNumbers(Set<Integer> numbersFromUser) {
        ArrayList<String> errors = new ArrayList<>();
        if (numbersFromUser.size() < 6) {
            errors.add("You gave less than six numbers");
        }
        if (numbersFromUser.size() > 6) {
            errors.add("You gave more than six numbers");
        }
        for (int number : numbersFromUser) {
            if (number <= 0 || number >= 100) {
                errors.add("You gave" + number + " which is outside of range (1-99)");
            }
        }
        return errors;
    }
}
