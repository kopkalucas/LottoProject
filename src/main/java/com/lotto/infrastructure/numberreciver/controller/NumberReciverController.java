package com.lotto.infrastructure.numberreciver.controller;

import com.lotto.domain.numberreciver.NumberReceiverFacade;
import com.lotto.domain.numberreciver.dto.InputNumberResultDto;
import com.lotto.domain.numberreciver.dto.RequestNumberDto;
import com.lotto.domain.numberreciver.dto.TicketDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.List;


@RestController
public class NumberReciverController {

    private final NumberReceiverFacade numberReceiverFacade;


    public NumberReciverController(NumberReceiverFacade numberReceiverFacade) {
        this.numberReceiverFacade = numberReceiverFacade;
    }

    @GetMapping("/numberReciver")
    LocalDateTime returnNextDrawDate() {
        return  numberReceiverFacade.retrieveNextDrawDate();
    }

    @GetMapping("/allTickets")
    List<TicketDto> retriveAllTicket() {
        return numberReceiverFacade.retrieveAllTicketsByNextDrawDate();
    }

    @PostMapping("/inputNumbers")
    public ResponseEntity<InputNumberResultDto> inputNumbers(@RequestBody RequestNumberDto request){
        InputNumberResultDto inputNumberResultDto = numberReceiverFacade.inputNumbers(request.numbersFromUser());
        return  ResponseEntity.ok(inputNumberResultDto);
    }

}
