package com.lotto.domain.numberreciver;


import org.springframework.data.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface NumberReceiverRepository extends Repository<Ticket, String> {
    Ticket save(Ticket entity);

    List<Ticket> findAllTicketsByDrawDate(LocalDateTime date);

    Optional<Ticket> findTicketByTicketId(String id);

}
