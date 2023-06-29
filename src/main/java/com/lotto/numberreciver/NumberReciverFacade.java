package com.lotto.numberreciver;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class NumberReciverFacade {

    private AtomicInteger ID = new AtomicInteger(0);
    private LocalDateTime date;

    public NumberReciverFacade(LocalDateTime date) {
        this.date = date;
    }

    Map<LocalDateTime,Set<Integer>> database = new HashMap<>();
    public Set<Integer> retriveNumbersForDate(LocalDateTime date){
        return database.get(date);
    }
    public CuponDto inputNumbers(Set<Integer> numbersFromUser) {
        if (numberValidator(numbersFromUser)){

            LocalDateTime loterryDate = calculateNextSaturday();
            database.put(loterryDate, numbersFromUser);
            return new CuponDto(ID.incrementAndGet(), loterryDate);
        }
        return new CuponDto(-1,null);
    }
    boolean numberValidator(Set<Integer> numbersFromUser) {
        if (numbersFromUser.size() != 6) {
            return false;
        }
        for (int number : numbersFromUser) {
            if (number <= 0 || number >= 100) {
                return false;
            }
        }
        return true;
    }
    LocalDateTime calculateNextSaturday() {
        return date.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
    }
}

