package com.lotto.feature;

import com.lotto.BaseIntegrationTest;
import com.lotto.domain.numbergenerator.NumberGenaratorFacade;
import com.lotto.domain.numberreciver.dto.InputNumberResultDto;
import com.lotto.infrastructure.numbergenerator.scheduler.WinningNumberScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserPlayedLottoAndWonIntegrationTest extends BaseIntegrationTest {

    @Autowired
    NumberGenaratorFacade numberGenaratorFacade;

    @Autowired
    WinningNumberScheduler winningNumberScheduler;


    @Test
    public void should_user_win_and_system_generate_winners() throws Exception {
        //step 1: system fetched winning numbers for draw date: 19.11.2022 12:00
        //given
        LocalDateTime drawDate = LocalDateTime.of(2022,11,19,12,0,0);
        //numberGenaratorFacade.generateWinningNumbers();
        //when && then
        await()
                .atMost(Duration.ofSeconds(20))
                .pollInterval(Duration.ofSeconds(1))
                .until(() -> {
                            return !numberGenaratorFacade.retrieveWinningNumberByDate(drawDate).getWinningNumbers().isEmpty();
                }
                );

        //step 2 user made POST .inputnumbers with 6 numbers (1, 2, 3, 4, 5, 6) at 19.11.2022 12:00 and system returned OK(200)
        //given
        //when
        ResultActions perform = mockMvc.perform(post("/inputNumbers")
                .content("""
                        {
                        "numbersFromUser": [1,2,3,4,5,6]
                        }
                        """.trim()
                ).contentType(MediaType.APPLICATION_JSON)
        );
        //then
        MvcResult mvcResult = perform.andExpect(status().isOk()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        InputNumberResultDto inputNumberResultDto = objectMapper.readValue(json, InputNumberResultDto.class);

        assertAll(
                () -> assertThat(inputNumberResultDto.drawDate()).isEqualTo(drawDate),
                () -> assertThat(inputNumberResultDto.ticketId()).isNotNull(),
                () -> assertThat(inputNumberResultDto.message()).isEqualTo("SUCCESS")
        );

        //step 4

        //given
        //when
        ResultActions performGetResultWithNotExistingId = mockMvc.perform(get("/results" + "/notExistingId"));
        //then
        performGetResultWithNotExistingId.andExpect(status().isNotFound())
                .andExpect(content().json("""
                    {
                    "message": "Not found for id: notExistingId",
                    "status": "NOT_FOUND"
                    }
                    """.trim()
                ));
    }
}
