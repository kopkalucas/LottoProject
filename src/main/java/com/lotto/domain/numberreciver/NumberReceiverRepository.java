package com.lotto.domain.numberreciver;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface NumberReceiverRepository extends Repository<Ticket, String> {
    Ticket save(Ticket entity);

    @Query("SELECT t FROM Ticket t WHERE t.drawDate = :date")
    List<Ticket> findAllTicketsByDrawDate(LocalDateTime date);

    Optional<Ticket> findTicketByTicketId(String id);

}
