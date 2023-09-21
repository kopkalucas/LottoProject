package com.lotto.domain.numberreciver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


public interface NumberReceiverRepository {
    Ticket save (Ticket ticket);

    List<Ticket> findAllTicketsByDrawDate(LocalDateTime date);
}
