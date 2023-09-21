package com.lotto.resultchecker;

import com.lotto.numbergenerator.NumberGenaratorFacade;
import com.lotto.numbergenerator.dto.WinningNumbersDto;
import com.lotto.numberreciver.dto.TicketDto;
import com.lotto.numberreciver.NumberReceiverFacade;
import com.lotto.resultchecker.dto.PlayersDto;
import com.lotto.resultchecker.dto.ResultDto;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Set;

import static com.lotto.resultchecker.ResultCheckerMapper.mapPlayersToResults;

@AllArgsConstructor
public class ResultCheckerFacade {

    NumberGenaratorFacade numberGenaratorFacade;
    NumberReceiverFacade numberReceiverFacade;
    PlayerRepository playerRepository;
    WinnersRetriever winnerGenerator;

    public PlayersDto generateResults() {
        List<TicketDto> allTicketsByDate = numberReceiverFacade.retrieveAllTicketsByNextDrawDate();
        List<Ticket> tickets = ResultCheckerMapper.mapFromTicketDto(allTicketsByDate);
        WinningNumbersDto winningNumbersDto = numberGenaratorFacade.generateWinningNumbers();
        Set<Integer> winningNumbers = winningNumbersDto.getWinningNumbers();
        if (winningNumbers == null || winningNumbers.isEmpty()) {
            return PlayersDto.builder()
                    .message("Winners failed to retrieve")
                    .build();
        }
        List<Player> players = winnerGenerator.retrieveWinners(tickets, winningNumbers);
        playerRepository.saveAll(players);
        return PlayersDto.builder()
                .results(mapPlayersToResults(players))
                .message("Winners succeeded to retrieve")
                .build();
    }

    public ResultDto findByTicketId(String ticketId) {
        Player player = playerRepository.findById(ticketId)
                .orElseThrow(() -> new PlayerResultNotFoundException("Not found for id: " + ticketId));
        return ResultDto.builder()
                .hash(ticketId)
                .numbers(player.numbers())
                .hitNumbers(player.hitNumbers())
                .drawDate(player.drawDate())
                .wonNumbers(player.wonNumbers())
                .isWinner(player.isWinner())
                .build();
    }
}