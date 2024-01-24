package com.lotto.domain.resultchecker;

import com.lotto.domain.numbergenerator.NumberGenaratorFacade;
import com.lotto.domain.numbergenerator.dto.WinningNumbersDto;
import com.lotto.domain.numberreciver.dto.TicketDto;
import com.lotto.domain.numberreciver.NumberReceiverFacade;
import com.lotto.domain.resultchecker.dto.PlayersDto;
import com.lotto.domain.resultchecker.dto.ResultDto;
import com.lotto.domain.resultchecker.dto.ResultState;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;


@Component
@AllArgsConstructor
public class ResultCheckerFacade {

    NumberGenaratorFacade numberGenaratorFacade;
    NumberReceiverFacade numberReceiverFacade;
    PlayerRepository playerRepository;
    WinnersRetriever winnerGenerator;

    public PlayersDto generateResults() {
        List<TicketDto> allTicketsByDate = numberReceiverFacade.retrieveAllTicketsByNextDrawDate();
        List<Ticket> tickets = ResultCheckerMapper.mapFromTicketDto(allTicketsByDate);
        WinningNumbersDto winningNumbersDto = numberGenaratorFacade.retrieveWinningNumberByDate(tickets.get(0).drawDate());
        Set<Integer> winningNumbers = winningNumbersDto.getWinningNumbers();
        if (winningNumbers == null || winningNumbers.isEmpty()) {
            return PlayersDto.builder()
                    .message("Winners failed to retrieve")
                    .build();
        }
        List<Player> players = winnerGenerator.retrieveWinners(tickets, winningNumbers);
        playerRepository.saveAll(players);
        return PlayersDto.builder()
                .results(ResultCheckerMapper.mapPlayersToResults(players))
                .message("Winners succeeded to retrieve")
                .build();
    }

    public ResultDto findByTicketId(String ticketId) {
//        if(numberReceiverFacade.ticketExistsByIdAndTimeIsBeforeDrawDate(ticketId)){
//            return ResultDto.builder()
//                    .hash(ticketId)
//                    .resultState(ResultState.WAIT)
//                    .build();
//        }
        Player player = playerRepository.findById(ticketId)
                .orElseThrow(() -> new PlayerResultNotFoundException("Not found for id: " + ticketId));
        return ResultDto.builder()
                .hash(ticketId)
                .numbers(player.getNumbers())
                .hitNumbers(player.getHitNumbers())
                .drawDate(player.getDrawDate())
                .wonNumbers(player.getWonNumbers())
                .isWinner(player.isWinner())
                .resultState(ResultState.SUCCESS)
                .build();
    }
}