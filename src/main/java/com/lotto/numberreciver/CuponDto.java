package com.lotto.numberreciver;

import java.time.LocalDateTime;
import java.util.Set;

public record CuponDto(Integer id, LocalDateTime loterryDate, String message, Set<Integer> numbers) {

}
