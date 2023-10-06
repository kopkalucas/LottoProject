package com.lotto.domain.numberreciver;

import com.lotto.domain.numberreciver.dto.TicketDto;

public class TicketMapper {

    public static TicketDto mapFromTicket(Ticket ticket) {
        return TicketDto.builder()
                .numbersFromUser(ticket.getNumbersFromUser())
                .ticketId(ticket.getTicketId())
                .drawDate(ticket.getDrawDate())
                .build();
    }
}
