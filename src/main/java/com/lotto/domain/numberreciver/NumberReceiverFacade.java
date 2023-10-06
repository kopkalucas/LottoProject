package com.lotto.domain.numberreciver;

import com.lotto.domain.numberreciver.dto.InputNumberResultDto;
import com.lotto.domain.numberreciver.dto.TicketDto;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
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
                .drawDate(savedTicket.getDrawDate())
                .ticketId(savedTicket.getTicketId())
                .numberFromUser(numbersFromUser)
                .message(ValidationResult.INPUT_SUCCESS.info)
                .build();
    }
    public List<TicketDto> retrieveAllTicketsByNextDrawDate() {
        LocalDateTime nextDrawDate = drawDateGenerator.getNextDrawDate();
        return retrieveAllTicketsByNextDrawDate(nextDrawDate);
    }

    public List<TicketDto> retrieveAllTicketsByNextDrawDate(LocalDateTime date){
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

