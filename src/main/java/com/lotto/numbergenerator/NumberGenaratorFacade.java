package com.lotto.numbergenerator;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NumberGenaratorFacade {

    Map<LocalDateTime,Set<Integer>> database = new HashMap<>();
    public Map<LocalDateTime,Set<Integer>> generateNumbers() {
        database.put(LocalDateTime.of(2023, 6, 10, 12, 0),Set.of(7,8,9,10,11,12));
        database.put(LocalDateTime.of(2023, 6, 17, 12, 0),Set.of(1, 2, 3, 4, 5, 6));
        return database;
    }

    public Set<Integer> retriveWonNumbersForDrawDate(LocalDateTime date) {

        database = generateNumbers();

        Set<Integer> set;
        if(date.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            return database.get(date);
        }
        return Set.of();

    }
}
