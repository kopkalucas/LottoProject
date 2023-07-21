package com.lotto.numberreciver;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class NumberReciverFacade {

    private AtomicInteger ID = new AtomicInteger(0);
    private LocalDateTime date;
    private final Validator validator;

    public NumberReciverFacade(LocalDateTime date, Validator validator) {
        this.date = date;
        this.validator = validator;
    }

    Map<Integer,CuponDto> database = new HashMap<>();
    public Set<CuponDto> retriveNumbersForDate(LocalDateTime date){
        return database.values()
                .stream()
                .filter(cuponDto -> cuponDto.loterryDate()
                        .equals(date))
                .collect(Collectors.toSet());
    }
    public LocalDateTime retriveDrawDate(){
        return calculateNextSaturday();
    }
    public CuponDto inputNumbers(Set<Integer> numbersFromUser) {

        List<String> validationErrors = validator.validateNumbers(numbersFromUser);
        if (validationErrors.isEmpty()){
            LocalDateTime loterryDate = calculateNextSaturday();
            int id = ID.incrementAndGet();
            CuponDto cupon = new CuponDto(id, loterryDate, "Success", numbersFromUser);
            database.put(id, cupon);
            return cupon;
        }
        return new CuponDto(null,null, String.join(", ", validationErrors), numbersFromUser);
    }

    private LocalDateTime calculateNextSaturday() {
        if(date.getDayOfWeek().equals(DayOfWeek.SATURDAY) && date.getHour() < 12 && date.getMinute() < 55){
            return date.withHour(12).withMinute(0);
        }
        LocalDateTime nextSaturday = date.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        return nextSaturday.withHour(12).withMinute(0);
    }
}

