package com.lotto.infrastructure.numberreciver.controller;

import com.lotto.domain.numberreciver.NumberReceiverFacade;
import com.lotto.domain.numberreciver.dto.TicketBody;
import com.lotto.domain.numberreciver.dto.TicketDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@AllArgsConstructor
public class NumberReciverController {

    private final NumberReceiverFacade numberReceiverFacade;

    @GetMapping("/numberReciver")
    LocalDateTime returnNextDrawDate() {
        return  numberReceiverFacade.retrieveNextDrawDate();
    }

    @GetMapping("/allTickets")
    List<TicketDto> retriveAllTicket() {
        return numberReceiverFacade.retrieveAllTicketsByNextDrawDate();
    }

    @PostMapping("/inputNumbers")
    public ResponseEntity<TicketBody> inputNumbers(@RequestBody @Valid InputNumbersRequestDto requestDto){
        Set<Integer> distinctNumbers = new HashSet<>(requestDto.inputNumbers());
        TicketBody ticketBody = numberReceiverFacade.inputNumbers(distinctNumbers);
        return  ResponseEntity.ok(ticketBody);
    }

}
