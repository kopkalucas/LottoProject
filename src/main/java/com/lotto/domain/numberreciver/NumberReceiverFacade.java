package com.lotto.domain.numberreciver;

import com.lotto.domain.numberreciver.dto.TicketBody;
import com.lotto.domain.numberreciver.dto.TicketDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.*;

@Component
@AllArgsConstructor
public class NumberReceiverFacade {

    private final NumberValidator validator;
    private final NumberReceiverRepository repository;
    private final DrawDateGenerator drawDateGenerator;
    private final Clock clock;

    public TicketBody inputNumbers(Set<Integer> numbersFromUser) {
        List<ValidationResult> validationResultList = validator.validate(numbersFromUser);
        if(!validationResultList.isEmpty()) {
            String resultMessage = validator.createResultMessage();
            return TicketBody.builder()
                    .message(resultMessage)
                    .build();
        }
        String ticketId = UUID.randomUUID().toString();
        LocalDateTime drawDate = drawDateGenerator.getNextDrawDate();
        Ticket savedTicket = repository.save(new Ticket(ticketId, drawDate, numbersFromUser));
        return TicketBody.builder()
                .ticketDto(TicketDto.builder()
                        .drawDate(savedTicket.getDrawDate())
                        .ticketId(savedTicket.getTicketId())
                        .numbersFromUser(numbersFromUser)
                        .build())
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

    public boolean ticketExistsByIdAndTimeIsBeforeDrawDate(String id){
        Optional<Ticket> ticketById = repository.findTicketByTicketId(id);
        if(ticketById.isEmpty()){
            return false;
        }
        Ticket ticket = ticketById.get();
        return ticket.drawDate.isAfter(LocalDateTime.now(clock));
    }


    public LocalDateTime retrieveNextDrawDate() {
        return drawDateGenerator.getNextDrawDate();
    }
}

