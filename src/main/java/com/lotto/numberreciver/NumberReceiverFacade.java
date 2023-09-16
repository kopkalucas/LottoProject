package com.lotto.numberreciver;

import com.lotto.numberreciver.dto.InputNumberResultDto;
import com.lotto.numberreciver.dto.TicketDto;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
public class NumberReceiverFacade {

    private final NumberValidator validator;
    private final NumberReceiverRepository repository;
    private final DrawDateGenerator drawDateGenerator;

    public InputNumberResultDto inputNumbers(Set<Integer> numbersFromUser) {
        List<ValidationResult> validationResultList = validator.validate(numbersFromUser);
        if(!validationResultList.isEmpty()) {
            String resultMessage = validator.createResultMessage();
            return InputNumberResultDto.builder()
                    .message(resultMessage)
                    .build();
        }
        String ticketId = UUID.randomUUID().toString();
        LocalDateTime drawDate = drawDateGenerator.getNextDrawDate();
        Ticket savedTicket = repository.save(new Ticket(ticketId, drawDate, numbersFromUser));
        return InputNumberResultDto.builder()
                .drawDate(savedTicket.drawDate())
                .ticketId(savedTicket.ticketId())
                .numberFromUser(numbersFromUser)
                .message(ValidationResult.INPUT_SUCCESS.info)
                .build();
    }
    public List<TicketDto> retriveAllTicketsByNextDrawDate(LocalDateTime date){
        List<Ticket> allTicketsByDrawDate = repository.findAllTicketsByDrawDate(date);
        return allTicketsByDrawDate
                .stream()
                .map(TicketMapper::mapFromTicket)
                .toList();
    }


    public LocalDateTime retrieveNextDrawDate() {
        return drawDateGenerator.getNextDrawDate();
    }
}

