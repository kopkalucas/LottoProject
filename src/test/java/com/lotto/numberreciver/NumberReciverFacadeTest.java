package com.lotto.numberreciver;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NumberReciverFacadeTest {

    NumberReciverFacade numberReciverFacade = new NumberReciverFacade(LocalDateTime.now());
    @Test
    public void should_return_id_when_user_gave_six_unique_numbers_from_a_range_1_99() {
        //Given
        //When
        CuponDto cuponDto = numberReciverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 99));
        //Then
        assertThat(cuponDto.id()).isEqualTo(1);

    }
    @Test
    public void should_return_next_saturday_as_lottery_date_when_user_gave_six_unique_numbers_from_a_range_1_99() {
        //Given
        NumberReciverFacade numberReciverFacade = new NumberReciverFacade(LocalDateTime.of(2023, 6, 29, 12, 0));
        //When
        CuponDto cuponDto = numberReciverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 99));
        //Then
        assertThat(cuponDto.loterryDate()).isEqualTo(LocalDateTime.of(2023, 7, 1, 12, 0));

    }
    @Test
    public void should_return_minus_1_when_user_gave_less_than_six_numbers() {
        //Given
        //When
        CuponDto cuponDto = numberReciverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5));
        //Then
        assertThat(cuponDto.id()).isEqualTo(-1);

    }
    @Test
    public void should_return_minus_1_when_user_gave_more_than_six_numbers() {
        //Given
        //When
        CuponDto cuponDto = numberReciverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 6,7 ));
        //Then
        assertThat(cuponDto.id()).isEqualTo(-1);

    }
    @Test
    public void should_return_minus_1_when_user_gave_not_unique_six_numbers() {
        //Given
        //When
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(6);
        hashSet.add(6);
        hashSet.add(6);
        hashSet.add(6);
        hashSet.add(6);
        hashSet.add(6);
        CuponDto cuponDto = numberReciverFacade.inputNumbers(hashSet);
        //Then
        assertThat(cuponDto.id()).isEqualTo(-1);

    }
    @Test
    public void should_return_minus_1_when_user_gave_numbers_outside_range_1_99() {
        //Given
        //When
        CuponDto cuponDto = numberReciverFacade.inputNumbers(Set.of(-111, 2, 322, 4, 500, 600));
        //Then
        assertThat(cuponDto.id()).isEqualTo(-1);
    }
    @Test
    public void should_return_numbers_when_user_gave_date() {
        //Given
        //When
        numberReciverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 99));
        Set<Integer> set = numberReciverFacade.retriveNumbersForDate(LocalDateTime.of(2023, 7, 1, 12, 0));
        //Then
        assertThat(set).isEqualTo(Set.of(1,2,3,4,5,99));
    }


}