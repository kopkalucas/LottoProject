package com.lotto.domain.numbergenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;


@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WinningNumbers{

    @Id
    @GeneratedValue()
    Long id;
    Set<Integer> winningNumbers;
    LocalDateTime date;
}

