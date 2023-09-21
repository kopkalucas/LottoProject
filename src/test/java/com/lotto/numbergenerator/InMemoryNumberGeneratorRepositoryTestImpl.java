package com.lotto.numbergenerator;

import com.lotto.numberreciver.Ticket;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryNumberGeneratorRepositoryTestImpl implements WinningNumbersRepository{
    Map<LocalDateTime, WinningNumbers> inMemoryDatabase = new ConcurrentHashMap<>();

    @Override
    public WinningNumbers save (WinningNumbers winningNumbers){
        inMemoryDatabase.put(winningNumbers.date(), winningNumbers);
        return winningNumbers;
    };

    @Override
    public WinningNumbers findNumbersByDrawDate(LocalDateTime date){
        return inMemoryDatabase.get(date);
    };

    @Override
    public boolean existsByDate(LocalDateTime nextDrawDate){
        inMemoryDatabase.get(nextDrawDate);
        return true;
    };

}
