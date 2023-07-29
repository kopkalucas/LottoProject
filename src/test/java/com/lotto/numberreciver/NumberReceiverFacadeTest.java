package com.lotto.numberreciver;

import com.lotto.AdjustableClock;
import com.lotto.numberreciver.dto.InputNumberResultDto;
import com.lotto.numberreciver.dto.TicketDto;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeTest {

    AdjustableClock clock = AdjustableClock(LocalDateTime.of(2023, 7, 29, 12, 0, 0).toInstant(ZoneOffset.UTC);
    NumberReceiverFacade numberReceiverFacade;
    {

        numberReceiverFacade = new NumberReceiverFacade(
                new NumberValidator(),
                new InMemoryNumberReceiverRepositoryTestImpl(),
                clock
        );
    }

    @Test
    public void should_return_id_when_user_gave_six_unique_numbers_from_a_range_1_99() {
        //Given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 99);
        //When
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //Then
        assertThat(result.ticketId()).isEqualTo(1);
    }
    @Test
    public void should_return_next_saturday_as_lottery_date_when_user_gave_six_unique_numbers_from_a_range_1_99() {
        //Given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 99);
        LocalDateTime drawDate = LocalDateTime.of(2023, 7, 1, 12, 0);
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                LocalDateTime.of(2023, 6, 29, 12, 0),
                new NumberValidator(),
                new InMemoryNumberReceiverRepositoryTestImpl());

        //When
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //Then
        assertThat(result.drawDate()).isEqualTo(drawDate);
    }
    @Test
    public void should_return_validation_error_message_when_user_gave_less_than_six_numbers() {
        //Given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, -5);
        //When
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //Then
        assertThat(result.message()).contains("You gave less than six numbers");

    }
    @Test
    public void should_return_validation_error_when_user_gave_more_than_six_numbers() {
        //Given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6, 7);
        //When
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //Then
        assertThat(result.message()).contains("You gave more than six numbers");
    }
    @Test
    public void should_return_validation_error_when_user_gave_numbers_outside_range_1_99() {
        //Given
        Set<Integer> numbersFromUser = Set.of(-111, 2, 322, 4, 500, 600);
        //When
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //Then
        assertThat(result.message()).contains("which is outside of range (1-99)");
    }
    @Test
    public void should_return_numbers_when_user_gave_date() {
        //Given
        LocalDateTime drawDate = LocalDateTime.of(2023, 7, 1, 12, 0);
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                LocalDateTime.of(2023, 6, 29, 10, 0),
                new NumberValidator(),
                new InMemoryNumberReceiverRepositoryTestImpl());
        //When
        numberReceiverFacade.inputNumbers(numbersFromUser);
        List<TicketDto> ticket = numberReceiverFacade.userNumbers(drawDate);
        //Then
        assertThat(ticket).isEqualTo(Set.of(1,2,3,4,5,6));
    }
    @Test
    public void should_return_save_to_database_when_user_gave_six_numbers(){
        //Given
        Set<Integer> numberFromUser= Set.of(1,2,3,4,5,6);
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numberFromUser);
        LocalDateTime drawDate = LocalDateTime.of(2023, 7, 1, 12, 0);
        //When
        List<TicketDto> ticketDtos = numberReceiverFacade.userNumbers(drawDate);
        //Then
        assertThat(ticketDtos).contains(
                TicketDto.builder()
                        .ticketId(result.ticketId())
                        .drawDate(drawDate)
                        .numbersFromUser(result.numberFromUser())
                        .build()
        );
    }
    @Test
    public void should_return_next_lottery_date() {
        //Given
        LocalDateTime drawDate = LocalDateTime.of(2023, 7, 1, 12, 0);
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                LocalDateTime.of(2023, 6, 29, 10, 0),
                new NumberValidator(),
                new InMemoryNumberReceiverRepositoryTestImpl());
        //When
        LocalDateTime localDateTime = numberReceiverFacade.retriveDrawDate();
        //Then
        assertThat(localDateTime).isEqualTo(drawDate);
    }

    @Test
    public void should_return_next_lottery_date_when() {
        //Given
        LocalDateTime drawDate = LocalDateTime.of(2023, 7, 8, 12, 0);
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                LocalDateTime.of(2023, 7, 1, 12, 0),
                new NumberValidator(),
                new InMemoryNumberReceiverRepositoryTestImpl());
        //When
        LocalDateTime localDateTime = numberReceiverFacade.retriveDrawDate();
        //Then
        assertThat(localDateTime).isEqualTo(drawDate);
    }
    @Test
    public void should_return_next_saturday_as_lottery_date_when_user_played_at_hold_time() {
        //Given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 99);
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                LocalDateTime.of(2023, 7, 1, 11, 55),
                new NumberValidator(),
                new InMemoryNumberReceiverRepositoryTestImpl());
        //When
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //Then
        assertThat(result.drawDate()).isEqualTo(LocalDateTime.of(2023, 7, 8, 12, 0));
    }

    @Test
    public void should_return_next_saturday_as_lottery_date_when_user_played_before_hold_time() {
        //Given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 99);
        LocalDateTime drawDate = LocalDateTime.of(2023, 7, 1, 12, 0);
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                LocalDateTime.of(2023, 7, 1, 11, 54),
                new NumberValidator(),
                new InMemoryNumberReceiverRepositoryTestImpl());
        //When
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //Then
        assertThat(result.drawDate()).isEqualTo(drawDate);
    }

}