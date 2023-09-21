package com.lotto.numbergenerator;

import java.util.Set;

public class WinningNumberGeneratorTestImpl implements RandomNumberGenerable{
    private final Set<Integer> generatedNumbers;

    WinningNumberGeneratorTestImpl() {
        this.generatedNumbers = Set.of(1,2,3,4,5,6);
    }

    WinningNumberGeneratorTestImpl(Set<Integer> generatedNumbers) {
        this.generatedNumbers = generatedNumbers;
    }

    @Override
    public Set<Integer> genereteSixRandomNumbers() {
        return  generatedNumbers;
    }
}
