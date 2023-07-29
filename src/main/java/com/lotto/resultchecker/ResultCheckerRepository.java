package com.lotto.resultchecker;

import com.lotto.numberreciver.dto.TicketDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ResultCheckerRepository {
    private Map<String, TicketDto> inMemoryDatabase = new ConcurrentHashMap<>();


    public void saveAll(List<TicketDto> winnigCupons) {
        winnigCupons
                .forEach(ticketDto -> inMemoryDatabase.put(ticketDto.ticketId(), ticketDto) );
    }

    public Optional<TicketDto> findById(String id) {
        TicketDto ticketDto = inMemoryDatabase.get(id);
        if(ticketDto == null) {
            return Optional.empty();
        }
        return Optional.of(ticketDto);
    }
}
