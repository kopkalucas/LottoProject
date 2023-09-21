package com.lotto.numbergenerator;

import com.lotto.numbergenerator.dto.WinningNumbersDto;
import com.lotto.numberreciver.NumberReceiverFacade;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.configuration.IMockitoConfiguration;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class NumberGenaratorFacadeTest {

    private final WinningNumbersRepository winningNumbersRepository = new InMemoryNumberGeneratorRepositoryTestImpl();
    NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
    @Test
    public void should_return_six_winning_numbers() {
        //Given
        RandomNumberGenerable generator = new WinningNumberGeneratorTestImpl();
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(LocalDateTime.now());
        NumberGenaratorFacade numbersGenerator = new NumberGenaratorFacade(generator, new WinningNumberValidator(), winningNumbersRepository, numberReceiverFacade );
        //When
        WinningNumbersDto generatedNumbers = numbersGenerator.generateWinningNumbers();
        //Then
        assertThat(generatedNumbers.getWinningNumbers().size()).isEqualTo(6);
    }
    @Test
    public void should_return_set_of_required_size_within_required_range() {
        //Given
        RandomNumberGenerable generator = new WinningNumberGeneratorTestImpl();
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(LocalDateTime.now());
        NumberGenaratorFacade numbersGenerator = new NumberGenaratorFacade(generator, new WinningNumberValidator(), winningNumbersRepository, numberReceiverFacade );
        //When
        WinningNumbersDto generatedNumbers = numbersGenerator.generateWinningNumbers();
        //Then
        int upperBand = 99;
        int lowerBand = 1;
        Set<Integer> winningNumbers = generatedNumbers.getWinningNumbers();
        boolean numbersInRange = winningNumbers.stream().allMatch(number -> number >= lowerBand && number <= upperBand);
        assertThat(numbersInRange).isTrue();
    }
    @Test
    public void should_throw_an_exception_when_number_not_in_range() {
        //Given
        Set<Integer> numbersOutOfRange = Set.of(1, 2, 3, 4, 5, 100);
        RandomNumberGenerable generator = new WinningNumberGeneratorTestImpl(numbersOutOfRange);
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(LocalDateTime.now());
        NumberGenaratorFacade numbersGenerator = new NumberGenaratorFacade(generator, new WinningNumberValidator(), winningNumbersRepository, numberReceiverFacade );
        //When
        //Then
        assertThrows(IllegalStateException.class, numbersGenerator::generateWinningNumbers, "Number out of range!");
    }
    @Test
    public void should_return_unique_winning_numbers() {
        //Given
        RandomNumberGenerable generator = new WinningNumberGeneratorTestImpl();
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(LocalDateTime.now());
        NumberGenaratorFacade numbersGenerator = new NumberGenaratorFacade(generator, new WinningNumberValidator(), winningNumbersRepository, numberReceiverFacade );
        //When
        WinningNumbersDto generatedNumbers = numbersGenerator.generateWinningNumbers();
        //Then
        assertThat(generatedNumbers.getWinningNumbers()).doesNotHaveDuplicates();
    }
    @Test
    public void should_return_winning_numbers_by_given_date() {
        //Given
        LocalDateTime drawDate = LocalDateTime.of(2023, 9, 23, 12, 0, 0);
        Set<Integer> generatedWinningNumbers = Set.of(1, 2, 3, 4, 5, 6);
        String id = UUID.randomUUID().toString();
        WinningNumbers winningNumbers = WinningNumbers.builder()
                .id(id)
                .date(drawDate)
                .winningNumbers(generatedWinningNumbers)
                .build();
        winningNumbersRepository.save(winningNumbers);
        RandomNumberGenerable generator = new WinningNumberGeneratorTestImpl();
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(drawDate);
        NumberGenaratorFacade numbersGenerator = new NumberGenaratorFacade(generator, new WinningNumberValidator(), winningNumbersRepository, numberReceiverFacade );
        //When
        WinningNumbersDto winningNumbersDto = numbersGenerator.retrieveWinningNumberByDate(drawDate);
        //Then
        WinningNumbersDto expectedWinningNumbersDto = WinningNumbersDto.builder()
                .date(drawDate)
                .winningNumbers(generatedWinningNumbers)
                .build();
        assertThat(expectedWinningNumbersDto).isEqualTo(winningNumbersDto);
    }

}