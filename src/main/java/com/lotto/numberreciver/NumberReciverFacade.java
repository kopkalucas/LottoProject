package com.lotto.numberreciver;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class NumberReciverFacade {

    private AtomicInteger ID = new AtomicInteger(0);
    private LocalDateTime date;
    private final Validator validator;

    public NumberReciverFacade(LocalDateTime date, Validator validator) {
        this.date = date;
        this.validator = validator;
    }

    Map<LocalDateTime,Set<Integer>> database = new HashMap<>();
    public Set<Integer> retriveNumbersForDate(LocalDateTime date){
        return database.get(date);
    }
    public LocalDateTime retriveDrawDate(){
        return calculateNextSaturday();
    }
    public CuponDto inputNumbers(Set<Integer> numbersFromUser) {

        List<String> validationErrors = validator.validateNumbers(numbersFromUser);
        if (validationErrors.isEmpty()){
            LocalDateTime loterryDate = calculateNextSaturday();
            database.put(loterryDate, numbersFromUser);
            return new CuponDto(ID.incrementAndGet(), loterryDate, "Success");
        }
        return new CuponDto(null,null, validationErrors.toString());
    }

    private LocalDateTime calculateNextSaturday() {
        if(date.getDayOfWeek().equals(DayOfWeek.SATURDAY) && date.getHour() < 12 && date.getMinute() < 55){
            return date.withHour(12).withMinute(0);
        }
        LocalDateTime nextSaturday = date.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        return nextSaturday.withHour(12).withMinute(0);
    }
}

