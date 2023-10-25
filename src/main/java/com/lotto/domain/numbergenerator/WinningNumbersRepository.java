package com.lotto.domain.numbergenerator;


import org.springframework.data.repository.Repository;
import java.time.LocalDateTime;
import java.util.Optional;


public interface WinningNumbersRepository extends Repository<WinningNumbers, Long> {
    WinningNumbers save (WinningNumbers winningNumbers);

    Optional<WinningNumbers> findNumbersByDate(LocalDateTime date);

    boolean existsByDate(LocalDateTime nextDrawDate);
}
