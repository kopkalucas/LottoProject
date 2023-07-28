package com.lotto.resultchecker;

import java.time.LocalDateTime;
import java.util.Set;

public record ResultEntity(String id, LocalDateTime loterryDate, Set<Integer> userNumbers, Set<Integer> winnerNumbers, boolean isWinner) {
}
