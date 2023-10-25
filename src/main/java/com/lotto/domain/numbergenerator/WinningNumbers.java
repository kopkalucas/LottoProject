package com.lotto.domain.numbergenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WinningNumbers{

    @Id
    LocalDateTime date;
    Set<Integer> winningNumbers;
}

