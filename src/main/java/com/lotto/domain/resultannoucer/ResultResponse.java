package com.lotto.domain.resultannoucer;

import lombok.Builder;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
record ResultResponse(
        String hash,
        Set<Integer> numbers,
        Set<Integer> wonNumbers,
        Set<Integer> hitNumbers,
        LocalDateTime drawDate,
        boolean isWinner,
        LocalDateTime createdDate) {
}