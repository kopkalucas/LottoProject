package com.lotto.numberreciver;

import com.lotto.numberreciver.dto.InputNumberResultDto;
import com.lotto.numberreciver.dto.TicketDto;
import lombok.AllArgsConstructor;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;
@AllArgsConstructor
public class NumberReceiverFacade {

    private final NumberValidator validator;
    private final NumberReceiverRepository repository;
    private final Clock clock;


    public LocalDateTime retriveDrawDate(){
        return calculateNextSaturday();
    }
    public InputNumberResultDto inputNumbers(Set<Integer> numbersFromUser) {
        boolean areAllNumbersInRange = validator.areAllNumbersInRange(numbersFromUser);
        if (areAllNumbersInRange) {
            String ticketId = UUID.randomUUID().toString();
            LocalDateTime drawDate = calculateNextSaturday();
            Ticket savedTicket = repository.save(new Ticket(ticketId, drawDate, numbersFromUser));
            return InputNumberResultDto.builder()
                    .drawDate(savedTicket.drawDate())
                    .ticketId(savedTicket.ticketId())
                    .numberFromUser(numbersFromUser)
                    .message("success")
                    .build();
        }
        return InputNumberResultDto.builder()
                .message("failed")
                .build();
    }
    public List<TicketDto> userNumbers(LocalDateTime date){
        List<Ticket> allTicketsByDrawDate = repository.findAllTicketsByDrawDate(date);
        return allTicketsByDrawDate
                .stream()
                .map(TicketMapper::mapFromTicket)
                .toList();
    }

    private LocalDateTime calculateNextSaturday() {
        LocalDateTime date = LocalDateTime.now(clock);
        if(date.getDayOfWeek().equals(DayOfWeek.SATURDAY) && date.getHour() < 12 && date.getMinute() < 55){
            return date.withHour(12).withMinute(0);
        }
        LocalDateTime nextSaturday = date.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        return nextSaturday.withHour(12).withMinute(0);
    }
}

