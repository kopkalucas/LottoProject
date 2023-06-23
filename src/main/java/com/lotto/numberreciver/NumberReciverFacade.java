package com.lotto.numberreciver;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NumberReciverFacade {

    private static final int ID = 0;

    Map<LocalDateTime,Set<Integer>> database = new HashMap<>();
    public Set<Integer> retriveNumbersForDate(LocalDateTime date){
        return database.get(date);
    }
    public int inputNumbers(Set<Integer> numbersFromUser, LocalDateTime date) {
        if (numberValidator(numbersFromUser)){
            database.put(date, numbersFromUser);
        }
        return ID + 1;
    }
    boolean numberValidator(Set<Integer> numbersFromUser) {
        if (numbersFromUser.size() != 6) {
            return false;
        }
        for (int number : numbersFromUser) {
            if (number < 1 || number > 99) {
                return false;
            }
        }
        return true;
    }
}

