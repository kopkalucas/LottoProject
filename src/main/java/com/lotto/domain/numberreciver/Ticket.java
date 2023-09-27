package com.lotto.domain.numberreciver;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ticket {
    @Id
    String ticketId;
    LocalDateTime drawDate;
    @ElementCollection
    Set<Integer> numbersFromUser;
    public Ticket() {
    }

    public String ticketId() {
        return ticketId;
    }

    public LocalDateTime drawDate() {
        return drawDate;
    }

    public Set<Integer> numbersFromUser() {
        return numbersFromUser;
    }

    public Ticket(String ticketId, LocalDateTime drawDate, Set<Integer> numbersFromUser) {
        this.ticketId = ticketId;
        this.drawDate = drawDate;
        this.numbersFromUser = numbersFromUser;
    }
}
