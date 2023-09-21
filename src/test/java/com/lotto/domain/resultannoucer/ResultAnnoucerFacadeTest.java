package com.lotto.domain.resultannoucer;
import com.lotto.domain.resultannoucer.dto.ResponseDto;
import com.lotto.domain.resultannoucer.dto.ResultAnnouncerResponseDto;
import com.lotto.domain.resultchecker.ResultCheckerFacade;
import com.lotto.domain.resultchecker.dto.ResultDto;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Set;

import static com.lotto.domain.resultannoucer.MessageResponse.LOSE_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ResultAnnoucerFacadeTest {
    ResponseRepository responseRepository = new InMemoryResultAnnoucerFacadeTestImpl();
    ResultCheckerFacade resultCheckerFacade = mock(ResultCheckerFacade.class);

    @Test
    public void should_return_response_with_lose_message_if_ticket_is_not_winning_ticket() {
        //Given
        LocalDateTime drawDate = LocalDateTime.of(2023,9,23,12,0,0);
        String hash = "123";
        ResultAnnoucerFacade resultAnnoucerFacade = new ResultAnnoucerFacade(resultCheckerFacade, responseRepository, Clock.systemUTC());
        ResultDto resultDto = ResultDto.builder()
                .hash("123")
                .numbers(Set.of(1,2,3,4,5,6))
                .hitNumbers(Set.of())
                .drawDate(drawDate)
                .isWinner(false)
                .build();
        when(resultCheckerFacade.findByTicketId(hash)).thenReturn(resultDto);
        //When
        ResultAnnouncerResponseDto resultAnnouncerResponseDto = resultAnnoucerFacade.checkResult(hash);
        //Then
        ResponseDto responseDto = ResponseDto.builder()
                .hash("123")
                .numbers(Set.of(1,2,3,4,5,6))
                .hitNumbers(Set.of())
                .drawDate(drawDate)
                .isWinner(false)
                .build();

        ResultAnnouncerResponseDto expectedResult = new ResultAnnouncerResponseDto(responseDto, LOSE_MESSAGE.info);
        assertThat(resultAnnouncerResponseDto).isEqualTo(expectedResult);
    }
}