package com.lotto.domain.numberreciver.dto;

import java.util.Set;

public record RequestNumberDto(
        Set<Integer> numbersFromUser) {
}
