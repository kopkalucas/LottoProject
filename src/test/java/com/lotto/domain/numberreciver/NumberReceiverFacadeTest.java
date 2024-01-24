package com.lotto.domain.numberreciver;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeTest {

    InMemoryNumberReceiverRepositoryTestImpl inMemoryNumberReceiverRepositoryTest = new InMemoryNumberReceiverRepositoryTestImpl();
    NumberValidator numberValidator = new NumberValidator();
    Clock clock = Clock.systemUTC();
    DrawDateGenerator drawDateGenerator = new DrawDateGenerator(clock);


    @Test
    public void should_return_correct_response_when_user_input_six_numbers_in_range(){
        //Given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(numberValidator, inMemoryNumberReceiverRepositoryTest, drawDateGenerator, clock);
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 99);
        //When
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //Then
        assertThat(result.message()).isEqualTo(ValidationResult.INPUT_SUCCESS.info);
    }

    @Test
    public void should_return_validation_error_when_user_gave_numbers_outside_range_1_99() {
        //Given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(numberValidator, inMemoryNumberReceiverRepositoryTest, drawDateGenerator, clock);
        Set<Integer> numbersFromUser = Set.of(-111, 2, 322, 4, 500, 600);;
        //When
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //Then
        assertThat(result.message()).isEqualTo(ValidationResult.NOT_IN_RANGE.info);
    }

    @Test
    public void should_return_validation_error_message_when_user_gave_less_than_six_numbers() {
        //Given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(numberValidator, inMemoryNumberReceiverRepositoryTest, drawDateGenerator, clock);
        Set<Integer> numbersFromUser = Set.of(1,2,3,4,5);;
        //When
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //Then
        assertThat(result.message()).isEqualTo(ValidationResult.NOT_SIX_NUMBERS_GIVEN.info);
    }

    @Test
    public void should_return_validation_error_when_user_gave_more_than_six_numbers() {
        //Given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(numberValidator, inMemoryNumberReceiverRepositoryTest, drawDateGenerator, clock);
        Set<Integer> numbersFromUser = Set.of(1,2,3,4,5,6,7);;
        //When
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //Then
        assertThat(result.message()).isEqualTo(ValidationResult.NOT_SIX_NUMBERS_GIVEN.info);
    }

    @Test
    public void should_return_next_lottery_date() {
        //Given
        Clock clock = Clock.fixed(LocalDateTime.of(2023, 9, 16, 10, 00, 0).toInstant(ZoneOffset.UTC), ZoneId.of("Europe/London"));
        DrawDateGenerator drawDateGenerator = new DrawDateGenerator(clock);
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(numberValidator, inMemoryNumberReceiverRepositoryTest, drawDateGenerator, clock);
        //When
        LocalDateTime testedDrawDate = numberReceiverFacade.retrieveNextDrawDate();
        //Then
        assertThat(testedDrawDate).isEqualTo(LocalDateTime.of(2023, 9, 16, 12, 0, 0));
    }

    @Test
    public void should_return_next_saturday_as_lottery_date_when_user_played_at_hold_time() {
        //Given
        Clock clock = Clock.fixed(LocalDateTime.of(2023, 9, 16, 11, 00, 0).toInstant(ZoneOffset.UTC), ZoneId.of("Europe/London"));
        DrawDateGenerator drawDateGenerator = new DrawDateGenerator(clock);
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(numberValidator, inMemoryNumberReceiverRepositoryTest, drawDateGenerator, clock);
        //When
        LocalDateTime testedDrawDate = numberReceiverFacade.retrieveNextDrawDate();
        //Then
        assertThat(testedDrawDate).isEqualTo(LocalDateTime.of(2023, 9, 23, 12, 0, 0));
    }

    @Test
    public void should_return_numbers_when_user_gave_date() {
        //Given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(numberValidator, inMemoryNumberReceiverRepositoryTest, drawDateGenerator, clock);
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 99);
        //When
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //Then
        assertThat(result.numberFromUser()).isEqualTo(numbersFromUser);
    }

}