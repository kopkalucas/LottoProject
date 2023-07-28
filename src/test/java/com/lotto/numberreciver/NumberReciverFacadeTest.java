package com.lotto.numberreciver;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NumberReciverFacadeTest {

    NumberReciverFacade numberReciverFacade = new NumberReciverFacade(LocalDateTime.now(), new Validator());
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
        NumberReciverFacade numberReciverFacade = new NumberReciverFacade(LocalDateTime.of(2023, 6, 29, 12, 0),new Validator());
        //When
        CuponDto cuponDto = numberReciverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 99));
        //Then
        assertThat(cuponDto.loterryDate()).isEqualTo(LocalDateTime.of(2023, 7, 1, 12, 0));

    }
    @Test
    public void should_return_validation_error_message_when_user_gave_less_than_six_numbers() {
        //Given
        //When
        CuponDto cuponDto = numberReciverFacade.inputNumbers(Set.of(1, 2, 3, 4, -5));
        //Then
        assertThat(cuponDto.message()).contains("You gave less than six numbers");

    }
    @Test
    public void should_return_validation_error_when_user_gave_more_than_six_numbers() {
        //Given

        //When
        CuponDto cuponDto = numberReciverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 6,7 ));
        //Then
        assertThat(cuponDto.message()).contains("You gave more than six numbers");

    }
    @Test
    public void should_return_validation_error_when_user_gave_numbers_outside_range_1_99() {
        //Given
        //When
        CuponDto cuponDto = numberReciverFacade.inputNumbers(Set.of(-111, 2, 322, 4, 500, 600));
        //Then
        assertThat(cuponDto.message()).contains("which is outside of range (1-99)");
    }
    @Test
    public void should_return_numbers_when_user_gave_date() {
        //Given
        NumberReciverFacade numberReciverFacade = new NumberReciverFacade(LocalDateTime.of(2023, 6, 29, 10, 0), new Validator());
        //When
        numberReciverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 6));
        Set<CuponDto> cupons = numberReciverFacade.retriveNumbersForDate(LocalDateTime.of(2023, 7, 1, 12, 0));
        //Then
        assertThat(cupons).isEqualTo(Set.of(1,2,3,4,5,6));
    }
    @Test
    public void should_return_next_lottery_date() {
        //Given
        NumberReciverFacade numberReciverFacade = new NumberReciverFacade(LocalDateTime.of(2023, 6, 29, 10, 0), new Validator());
        //When
        LocalDateTime localDateTime = numberReciverFacade.retriveDrawDate();
        //Then
        assertThat(localDateTime).isEqualTo(LocalDateTime.of(2023,7,1,12,0));
    }

    @Test
    public void should_return_next_lottery_date_when() {
        //Given
        NumberReciverFacade numberReciverFacade = new NumberReciverFacade(LocalDateTime.of(2023, 7, 1, 12, 0), new Validator());
        //When
        LocalDateTime localDateTime = numberReciverFacade.retriveDrawDate();
        //Then
        assertThat(localDateTime).isEqualTo(LocalDateTime.of(2023,7,8,12,0));
    }
    @Test
    public void should_return_next_saturday_as_lottery_date_when_user_played_at_hold_time() {
        //Given
        NumberReciverFacade numberReciverFacade = new NumberReciverFacade(LocalDateTime.of(2023, 7, 1, 11, 55), new Validator());
        //When
        CuponDto cuponDto = numberReciverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 99));
        //Then
        assertThat(cuponDto.loterryDate()).isEqualTo(LocalDateTime.of(2023, 7, 8, 12, 0));

    }

    @Test
    public void should_return_next_saturday_as_lottery_date_when_user_played_before_hold_time() {
        //Given
        NumberReciverFacade numberReciverFacade = new NumberReciverFacade(LocalDateTime.of(2023, 7, 1, 11, 54), new Validator());
        //When
        CuponDto cuponDto = numberReciverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 99));
        //Then
        assertThat(cuponDto.loterryDate()).isEqualTo(LocalDateTime.of(2023, 7, 1, 12, 0));
    }

}