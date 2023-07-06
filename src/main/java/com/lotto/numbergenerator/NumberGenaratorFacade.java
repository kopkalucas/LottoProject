package com.lotto.numbergenerator;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;

public class NumberGenaratorFacade {

    public static final int LOTTERY_HOUR = 12;
    Map<LocalDateTime,Set<Integer>> database = new HashMap<>();
    public Set<Integer> retriveWonNumbersForDrawDate(LocalDateTime date) {

        if(date.getDayOfWeek().equals(DayOfWeek.SATURDAY) && date.getHour() >= LOTTERY_HOUR) {
            return database.get(date);
        }
        return Set.of();
    }

    Set<Integer> generateNumbers(LocalDateTime date) {
        Set<Integer> set = new HashSet<>();
        Random random = new Random();
        while (set.size() != 6) {
            set.add(random.nextInt(99) + 1);
        }
        database.put(date,set);
        return set;
    }


}
