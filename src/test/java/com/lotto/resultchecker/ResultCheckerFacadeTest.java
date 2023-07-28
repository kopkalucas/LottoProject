package com.lotto.resultchecker;

import com.lotto.numbergenerator.NumberGenaratorFacade;
import com.lotto.numberreciver.CuponDto;
import com.lotto.numberreciver.NumberReciverFacade;

import com.lotto.resultchecker.dto.ResultDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResultCheckerFacadeTest {

    NumberReciverFacade numberReciverFacade = mock(NumberReciverFacade.class);
    NumberGenaratorFacade numberGenaratorFacade = mock(NumberGenaratorFacade.class);
    ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacade(numberReciverFacade, numberGenaratorFacade);
    @Test
    public void should_return_one_when_one_cupon_won_lottery() {
        //Given
        LocalDateTime drawDate = LocalDateTime.of(2023,7,22,12,0);
        when(numberReciverFacade.retriveDrawDate()).thenReturn(drawDate);
        when(numberReciverFacade.retriveNumbersForDate(drawDate)).thenReturn(Set.of(new CuponDto(1,drawDate,"Sucess",Set.of(1,2,3,4,5,6))));
        when(numberGenaratorFacade.retriveWonNumbersForDrawDate(drawDate)).thenReturn(Set.of(1,2,3,4,5,6));
        //When

        List<CuponDto> winningCupons = resultCheckerFacade.calculateWinners();
        //Then
        assertThat(winningCupons).hasSize(1);
    }
    @Test
    public void should_return_zero_when_none_of_cupons_won_lottery() {
        //Given
        LocalDateTime drawDate = LocalDateTime.of(2023,7,22,12,0);
        when(numberReciverFacade.retriveDrawDate()).thenReturn(drawDate);
        when(numberReciverFacade.retriveNumbersForDate(drawDate)).thenReturn(Set.of(new CuponDto(1,drawDate,"Sucess", Set.of(1,2,3,4,5,6)), new CuponDto(2, drawDate, "Sucess", Set.of(0,1,2,333,21,17))));
        when(numberGenaratorFacade.retriveWonNumbersForDrawDate(drawDate)).thenReturn(Set.of(8,9,10,11,12,13));
        //When
        List<CuponDto> winningCupons = resultCheckerFacade.calculateWinners();
        //Then
        assertThat(winningCupons).hasSize(0);
    }

    @Test
    public void should_return_two_when_two_out_of_three_cupons_won_lottery() {
        //Given
        LocalDateTime drawDate = LocalDateTime.of(2023,7,22,12,0);
        when(numberReciverFacade.retriveDrawDate()).thenReturn(drawDate);
        when(numberReciverFacade.retriveNumbersForDate(drawDate)).thenReturn(Set.of(new CuponDto(1,drawDate,"Sucess", Set.of(1,2,3,4,5,6)), new CuponDto(2, drawDate, "Sucess", Set.of(0,1,2,333,21,17)), new CuponDto(3, drawDate, "Sucess", Set.of(1,2,3,41,42,43))));
        when(numberGenaratorFacade.retriveWonNumbersForDrawDate(drawDate)).thenReturn(Set.of(1,2,3,4,5,6));
        //When
        List<CuponDto> winningCupons = resultCheckerFacade.calculateWinners();
        //Then
        assertThat(winningCupons).hasSize(2);
    }

    @Test
    public void should_return_sucess_message_when_user_with_given_ticket_id_won() {
        //Given
        String winnerTicketId = "123575";
        //When
        ResultDto resultDto = resultCheckerFacade.checkResultById(winnerTicketId);
        //Then
        ResultDto expectedResult = new ResultDto("Sucess");
        assertThat(resultDto).isEqualTo(expectedResult);
    }
    @Test
    public void should_return_failed_message_when_user_with_given_ticket_id_lost() {
        //Given
        String winnerTicketId = "123575";
        //When
        ResultDto resultDto = resultCheckerFacade.checkResultById(winnerTicketId);
        //Then
        ResultDto expectedResult = new ResultDto("You Lost");
        assertThat(resultDto).isEqualTo(expectedResult);
    }
    @Test
    public void should_not_return_result_by_id_when_user_not_play() {

    }
    @Test
    public void should_return_too_early_message_when_user_checked_result_before_calculating_results() {

    }





}