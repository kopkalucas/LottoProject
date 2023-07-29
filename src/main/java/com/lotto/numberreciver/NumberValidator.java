package com.lotto.numberreciver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class NumberValidator {

    public static final int MINIMAL_NUMBER_FROM_USER = 1;
    public static final int MAXIMAL_NUMBER_FROM_USER = 99;
    public static final int MAX_NUMBERS_FROM_USER = 6;

    boolean areAllNumbersInRange(Set<Integer> numbersFromUser) {
        return numbersFromUser.stream()
                .filter(number -> number >= MINIMAL_NUMBER_FROM_USER)
                .filter(number -> number <= MAXIMAL_NUMBER_FROM_USER)
                .count() == MAX_NUMBERS_FROM_USER;
    }
}
