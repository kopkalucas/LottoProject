package com.lotto.domain.resultchecker;

import com.lotto.domain.numbergenerator.NumberGenaratorFacade;
import com.lotto.domain.numbergenerator.dto.WinningNumbersDto;
import com.lotto.domain.numberreciver.NumberReceiverFacade;


import com.lotto.domain.numberreciver.dto.TicketDto;
import com.lotto.domain.resultchecker.dto.PlayersDto;
import com.lotto.domain.resultchecker.dto.ResultDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResultCheckerFacadeTest {

    private final PlayerRepository playerRepository = new InMemoryResultCheckerFacadeTestImpl();
    private final NumberGenaratorFacade numberGenaratorFacade = mock(NumberGenaratorFacade.class);
    private final NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);

    @Test
    public void should_generate_all_players_with_correct_message() {
        //Given
        LocalDateTime drawDate = LocalDateTime.of(2023, 9, 16, 12, 0, 0);
        when(numberGenaratorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .build());
        when(numberReceiverFacade.retrieveAllTicketsByNextDrawDate()).thenReturn(
                List.of(TicketDto.builder()
                                .ticketId("001")
                                .numbersFromUser(Set.of(1, 2, 3, 4, 5, 6))
                                .drawDate(drawDate)
                                .build(),
                        TicketDto.builder()
                                .ticketId("002")
                                .numbersFromUser(Set.of(1, 2, 7, 8, 9, 10))
                                .drawDate(drawDate)
                                .build(),
                        TicketDto.builder()
                                .ticketId("003")
                                .numbersFromUser(Set.of(7, 8, 9, 10, 11, 12))
                                .drawDate(drawDate)
                                .build())
        );
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacade(numberGenaratorFacade, numberReceiverFacade, playerRepository, new WinnersRetriever());
        //When
        PlayersDto playersDto = resultCheckerFacade.generateResults();
        //Then
        List<ResultDto> results = playersDto.results();
        ResultDto resultDto = ResultDto.builder()
                .hash("001")
                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(drawDate)
                .isWinner(true)
                .wonNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .build();
        ResultDto resultDto1 = ResultDto.builder()
                .hash("002")
                .numbers(Set.of(1, 2, 7, 8, 9, 10))
                .hitNumbers(Set.of(1, 2))
                .wonNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(drawDate)
                .isWinner(false)
                .build();
        ResultDto resultDto2 = ResultDto.builder()
                .hash("003")
                .numbers(Set.of(7, 8, 9, 10, 11, 12))
                .hitNumbers(Set.of())
                .wonNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(drawDate)
                .isWinner(false)
                .build();
        assertThat(results).contains(resultDto, resultDto1, resultDto2);
        String message = playersDto.message();
        assertThat(message).isEqualTo("Winners succeeded to retrieve");
    }

    @Test
    public void should_generate_fail_message_when_winningNumbers_equal_null() {
        //given
        when(numberGenaratorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(null)
                .build());
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacade(numberGenaratorFacade, numberReceiverFacade, playerRepository, new WinnersRetriever());
        //when
        PlayersDto playersDto = resultCheckerFacade.generateResults();
        //then
        String message = playersDto.message();
        assertThat(message).isEqualTo("Winners failed to retrieve");

    }

    @Test
    public void should_generate_fail_message_when_winningNumbers_is_empty() {
        //given
        when(numberGenaratorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(Set.of())
                .build());
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacade(numberGenaratorFacade, numberReceiverFacade, playerRepository, new WinnersRetriever());
        //when
        PlayersDto playersDto = resultCheckerFacade.generateResults();
        //then
        String message = playersDto.message();
        assertThat(message).isEqualTo("Winners failed to retrieve");

    }

    @Test
    public void should_generate_result_with_correct_credentials() {
        //given
        LocalDateTime drawDate = LocalDateTime.of(2022, 12, 17, 12, 0, 0);
        Set<Integer> winningNumbers = Set.of(1, 2, 3, 4, 5, 6);
        when(numberGenaratorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(winningNumbers)
                .build());
        String hash = "001";
        when(numberReceiverFacade.retrieveAllTicketsByNextDrawDate()).thenReturn(
                List.of(TicketDto.builder()
                                .ticketId(hash)
                                .numbersFromUser(Set.of(7, 8, 9, 10, 11, 12))
                                .drawDate(drawDate)
                                .build(),
                        TicketDto.builder()
                                .ticketId("002")
                                .numbersFromUser(Set.of(7, 8, 9, 10, 11, 13))
                                .drawDate(drawDate)
                                .build(),
                        TicketDto.builder()
                                .ticketId("003")
                                .numbersFromUser(Set.of(7, 8, 9, 10, 11, 14))
                                .drawDate(drawDate)
                                .build())
        );
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacade(numberGenaratorFacade, numberReceiverFacade, playerRepository, new WinnersRetriever());
        resultCheckerFacade.generateResults();
        //when

        ResultDto resultDto = resultCheckerFacade.findByTicketId(hash);
        //then
        ResultDto expectedResult = ResultDto.builder()
                .hash(hash)
                .numbers(Set.of(7, 8, 9, 10, 11, 12))
                .hitNumbers(Set.of())
                .drawDate(drawDate)
                .isWinner(false)
                .wonNumbers(winningNumbers)
                .build();
        assertThat(resultDto).isEqualTo(expectedResult);
    }
}

