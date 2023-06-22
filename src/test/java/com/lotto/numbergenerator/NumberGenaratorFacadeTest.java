package com.lotto.numbergenerator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;



class NumberGenaratorFacadeTest {

    private final LocalDateTime LOTERRY_DATE = LocalDateTime.of(2023, 6, 17, 12, 0);
    NumberGenaratorFacade numberGenaratorFacade = new NumberGenaratorFacade();
    @Test
    public void should_return_winning_numbers_when_won_numbers_exists_for_saturday_past_twelve() {
        //Given
        Set<Integer> generatedNumbers = numberGenaratorFacade.generateNumbers(LOTERRY_DATE);
        //When
        Set<Integer> wonNumbers = numberGenaratorFacade.retriveWonNumbersForDrawDate(LOTERRY_DATE);
        //Then
        assertThat(wonNumbers).isEqualTo(generatedNumbers);
    }
    @Test
    public void should_return_empty_winning_numbers_when_wanted_to_retirve_for_diffrent_day_than_saturday() {
        //Given
        LocalDateTime date = LocalDateTime.of(2023, 6, 15, 15, 41);
        numberGenaratorFacade.generateNumbers(date);
        //When
        Set<Integer> wonNumbers = numberGenaratorFacade.retriveWonNumbersForDrawDate(date);
        //Then
        assertThat(wonNumbers).isEqualTo(Set.of());
    }

    @Test
    public void should_return_winning_numbers_when_won_numbers_existed_for_past_draw() {
        //Given
        LocalDateTime date = LocalDateTime.of(2023, 6, 10, 15, 41);
        Set<Integer> generatedNumbersInPast = numberGenaratorFacade.generateNumbers(date);
        Set<Integer> generatedNumbers = numberGenaratorFacade.generateNumbers(LOTERRY_DATE);
        //When
        Set<Integer> wonNumbers = numberGenaratorFacade.retriveWonNumbersForDrawDate(date);
        //Then
        assertThat(wonNumbers).isEqualTo(generatedNumbersInPast);
    }

    @Test
    public void should_return_six_winning_numbers() {
        //Given
        numberGenaratorFacade.generateNumbers(LOTERRY_DATE);
        //When
        Set<Integer> wonNumbers = numberGenaratorFacade.retriveWonNumbersForDrawDate(LOTERRY_DATE);
        //Then
        assertThat(wonNumbers).hasSize(6);
    }
    @Test
    public void should_return_unique_winning_numbers() {
        //Given
        numberGenaratorFacade.generateNumbers(LOTERRY_DATE);
        //When
        Set<Integer> wonNumbers = numberGenaratorFacade.retriveWonNumbersForDrawDate(LOTERRY_DATE);
        //Then
        assertThat(wonNumbers).doesNotHaveDuplicates();
    }

    @Test
    public void should_return_empty_winning_numbers_when_is_saturday_before_noon() {
        //Given
        LocalDateTime date = LocalDateTime.of(2023, 6, 10, 11, 41);
        numberGenaratorFacade.generateNumbers(date);
        //When
        Set<Integer> wonNumbers = numberGenaratorFacade.retriveWonNumbersForDrawDate(date);
        //Then
        assertThat(wonNumbers).isEqualTo(Set.of());
    }
    @Test
    public void should_return_random_numbers_when_generated_few_times_in_a_row() {
        //Given
        //When
        Set<Integer> firstGeneratedNumbers = numberGenaratorFacade.generateNumbers(LOTERRY_DATE);
        Set<Integer> secondGeneratedNumbers = numberGenaratorFacade.generateNumbers(LOTERRY_DATE);
        Set<Integer> thirdGeneratedNumbers = numberGenaratorFacade.generateNumbers(LOTERRY_DATE);
        //Then
        assertAll(
                () -> assertThat(firstGeneratedNumbers).isNotEqualTo(secondGeneratedNumbers),
                () -> assertThat(secondGeneratedNumbers).isNotEqualTo(thirdGeneratedNumbers),
                () -> assertThat(thirdGeneratedNumbers).isNotEqualTo(firstGeneratedNumbers)
        );
    }
    @Test
    public void winning_numbers_should_be_from_the_range_1_99() {
        //Given
        Set<Integer> generatedNumbers = numberGenaratorFacade.generateNumbers(LOTERRY_DATE);
        //When
        Set<Integer> wonNumbers = numberGenaratorFacade.retriveWonNumbersForDrawDate(LOTERRY_DATE);
        //Then
        assertThat(wonNumbers)
                .hasOnlyElementsOfTypes(Integer.class)
                .allSatisfy(number -> assertThat(number).isBetween(1, 99));
    }




}