package com.lotto.resultchecker;

import com.lotto.numbergenerator.NumberGenaratorFacade;
import com.lotto.numberreciver.CuponDto;
import com.lotto.numberreciver.NumberReciverFacade;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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

        List<CuponDto> winningCupons = resultCheckerFacade.checkWinners();
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
        List<CuponDto> winningCupons = resultCheckerFacade.checkWinners();
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
        List<CuponDto> winningCupons = resultCheckerFacade.checkWinners();
        //Then
        assertThat(winningCupons).hasSize(2);
    }




}