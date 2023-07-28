package com.lotto.numberreciver;

import java.time.LocalDateTime;
import java.util.Set;

public record CuponDto(String id, LocalDateTime loterryDate, String message, Set<Integer> numbers) {

}
