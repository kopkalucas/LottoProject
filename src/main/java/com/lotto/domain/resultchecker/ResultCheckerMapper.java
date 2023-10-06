package com.lotto.domain.resultchecker;

import com.lotto.domain.numberreciver.dto.TicketDto;
import com.lotto.domain.resultchecker.dto.ResultDto;

import java.util.List;
import java.util.stream.Collectors;

class ResultCheckerMapper {

    static List<ResultDto> mapPlayersToResults(List<Player> players) {
        return players.stream()
                .map(player -> ResultDto.builder()
                        .hash(player.getHash())
                        .numbers(player.getNumbers())
                        .hitNumbers(player.getHitNumbers())
                        .drawDate(player.getDrawDate())
                        .isWinner(player.isWinner())
                        .wonNumbers(player.getWonNumbers())
                        .build())
                .collect(Collectors.toList());
    }

    static List<Ticket> mapFromTicketDto(List<TicketDto> allTicketsByDate) {
        return allTicketsByDate.stream()
                .map(ticketDto -> Ticket.builder()
                        .drawDate(ticketDto.drawDate())
                        .hash(ticketDto.ticketId())
                        .numbers(ticketDto.numbersFromUser())
                        .build())
                .toList();
    }


}