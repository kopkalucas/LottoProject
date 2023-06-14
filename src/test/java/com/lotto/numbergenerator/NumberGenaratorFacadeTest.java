package com.lotto.numbergenerator;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NumberGenaratorFacadeTest {

    private final LocalDateTime LOTERRY_DATE = LocalDateTime.of(2023, 6, 17, 12, 0);
    @Test
    public void should_return_winning_numbers_when_won_numbers_exists_for_current_date() {
        //Given

        NumberGenaratorFacade numberGenaratorFacade = new NumberGenaratorFacade();
        //When
        Set<Integer> wonNumbers = numberGenaratorFacade.retriveWonNumbersForDrawDate(LOTERRY_DATE);
        //Then
        assertThat(wonNumbers).isEqualTo(Set.of(1,2,3,4,5,6));
    }
    @Test
    public void should_return_empty_winning_numbers_when_won_numbers_does_not_for_given_date() {
        //Given
        LocalDateTime date = LocalDateTime.of(2023, 6, 15, 15, 41);
        NumberGenaratorFacade numberGenaratorFacade = new NumberGenaratorFacade();
        //When
        Set<Integer> wonNumbers = numberGenaratorFacade.retriveWonNumbersForDrawDate(date);
        //Then
        assertThat(wonNumbers).isEqualTo(Set.of());
    }
    @Test
    public void should_return_winning_numbers_when_won_numbers_existed_for_past_draw() {
        //Given
        LocalDateTime date = LocalDateTime.of(2023, 6, 10, 12, 0);
        NumberGenaratorFacade numberGenaratorFacade = new NumberGenaratorFacade();
        //When
        Set<Integer> wonNumbers = numberGenaratorFacade.retriveWonNumbersForDrawDate(date);
        //Then
        assertThat(wonNumbers).isEqualTo(Set.of(7,8,9,10,11,12));
    }

    @Test
    public void should_return_six_winning_numbers() {
        //Given
        NumberGenaratorFacade numberGenaratorFacade = new NumberGenaratorFacade();
        //When
        Set<Integer> wonNumbers = numberGenaratorFacade.retriveWonNumbersForDrawDate(LOTERRY_DATE);
        //Then
        assertThat(wonNumbers).hasSize(6);
    }
    @Test
    public void should_return_unique_winning_numbers() {
        //Given
        NumberGenaratorFacade numberGenaratorFacade = new NumberGenaratorFacade();
        //When
        Set<Integer> wonNumbers = numberGenaratorFacade.retriveWonNumbersForDrawDate(LOTERRY_DATE);
        //Then
        assertThat(wonNumbers).doesNotHaveDuplicates();
    }


}