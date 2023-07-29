package com.lotto.numberreciver;

import java.time.LocalDateTime;
import java.util.Set;

public record Ticket(String ticketId, LocalDateTime drawDate, Set<Integer> numbersFromUser) {
}
