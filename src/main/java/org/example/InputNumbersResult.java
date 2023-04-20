package org.example;

import java.util.List;
import java.util.Set;

public record InputNumbersResult(
        Set<Integer> inputNumbers,
        boolean isValid
) {
}
