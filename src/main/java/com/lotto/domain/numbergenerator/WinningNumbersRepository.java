package com.lotto.domain.numbergenerator;

import java.time.LocalDateTime;

public interface WinningNumbersRepository {
    WinningNumbers save (WinningNumbers winningNumbers);

    WinningNumbers findNumbersByDrawDate(LocalDateTime date);

    boolean existsByDate(LocalDateTime nextDrawDate);
}
