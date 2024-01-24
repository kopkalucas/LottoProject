package com.lotto.domain.numberreciver.dto;

import lombok.Builder;
import org.springframework.context.annotation.Bean;

@Builder
public record TicketBody (
        TicketDto ticketDto,
        String message
){
}
