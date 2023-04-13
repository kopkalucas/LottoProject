package org.example;

import java.util.List;

public record InputNumbersResult(
        List<Integer> inputNumbers,
        boolean isValid
) {
}
