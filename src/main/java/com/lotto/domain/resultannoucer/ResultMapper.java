package com.lotto.domain.resultannoucer;

import com.lotto.domain.resultannoucer.dto.ResponseDto;

public class ResultMapper {
    static ResponseDto mapToDto(ResultResponse resultResponse) {
        return ResponseDto.builder()
                .drawDate(resultResponse.getDrawDate())
                .hash(resultResponse.getHash())
                .hitNumbers(resultResponse.getHitNumbers())
                .numbers(resultResponse.getNumbers())
                .wonNumbers(resultResponse.getWonNumbers())
                .isWinner(resultResponse.isWinner())
                .build();
    }
}