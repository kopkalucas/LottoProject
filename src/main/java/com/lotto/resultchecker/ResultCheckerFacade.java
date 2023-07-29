//package com.lotto.resultchecker;
//
//import com.lotto.numbergenerator.NumberGenaratorFacade;
//import com.lotto.numberreciver.dto.TicketDto;
//import com.lotto.numberreciver.NumberReceiverFacade;
//import com.lotto.resultchecker.dto.ResultDto;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//public class ResultCheckerFacade {
//    private NumberReceiverFacade numberReceiverFacade;
//    private NumberGenaratorFacade numberGenaratorFacade;
//    private ResultCheckerRepository repository;
//
//    public ResultCheckerFacade(NumberReceiverFacade numberReceiverFacade, NumberGenaratorFacade numberGenaratorFacade) {
//        this.numberReceiverFacade = numberReceiverFacade;
//        this.numberGenaratorFacade = numberGenaratorFacade;
//    }
//
//    public void calculateWinners() {
//        LocalDateTime drawDate = numberReceiverFacade.retriveDrawDate();
//        Set<Integer> wonNumbersForDrawDate = numberGenaratorFacade.retriveWonNumbersForDrawDate(drawDate);
//        Set<ResultEntity> cuponsForDrawDate = numberReceiverFacade.userNumbers(drawDate).stream()
//                .map(ticketDto -> new ResultEntity(ticketDto.ticketId(), ticketDto.drawDate(), ticketDto.numbers(),wonNumbersForDrawDate,false))
//                .collect(Collectors.toSet());
//
//
//
//        for (ResultEntity cupon : cuponsForDrawDate) {
//            int matchingNumbersCount = 0;
//            for(Integer number : cupon.userNumbers()) {
//                if(wonNumbersForDrawDate.contains(number)){
//                    matchingNumbersCount++;
//                }
//            }
//            if(matchingNumbersCount >= 3) {
//                cupon.isWinner();
//            }
//        }
//        //repository.saveAll(winnigCupons);
//    }
//
//
//    public ResultDto checkResultById(String id) {
//        Optional<TicketDto> resultById = repository.findById(id);
//        if(resultById.isEmpty()){
//            return new ResultDto("Not Found");
//        }
//        return new ResultDto("Sucess");
//    }
//}
