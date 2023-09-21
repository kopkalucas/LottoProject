package com.lotto.numbergenerator;

import com.lotto.numberreciver.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public interface WinningNumbersRepository {
    WinningNumbers save (WinningNumbers winningNumbers);

    WinningNumbers findNumbersByDrawDate(LocalDateTime date);

    boolean existsByDate(LocalDateTime nextDrawDate);
}
