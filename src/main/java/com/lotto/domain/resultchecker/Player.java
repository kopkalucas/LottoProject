package com.lotto.domain.resultchecker;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Player {
    @Id
    String hash;
    Set<Integer> numbers;
    Set<Integer> hitNumbers;
    LocalDateTime drawDate;
    boolean isWinner;
    Set<Integer> wonNumbers;
}

