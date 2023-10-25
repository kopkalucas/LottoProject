package com.lotto.domain.numbergenerator;

import com.lotto.domain.numbergenerator.WinningNumbers;
import com.lotto.domain.numbergenerator.WinningNumbersRepository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryNumberGeneratorRepositoryTestImpl implements WinningNumbersRepository {
    Map<LocalDateTime, WinningNumbers> inMemoryDatabase = new ConcurrentHashMap<>();

    @Override
    public WinningNumbers save (WinningNumbers winningNumbers){
        inMemoryDatabase.put(winningNumbers.getDate(), winningNumbers);
        return winningNumbers;
    };

    @Override
    public Optional<WinningNumbers> findNumbersByDate(LocalDateTime date){
        return Optional.ofNullable(inMemoryDatabase.get(date));
    };

    @Override
    public boolean existsByDate(LocalDateTime nextDrawDate){
        inMemoryDatabase.get(nextDrawDate);
        return true;
    };

}
