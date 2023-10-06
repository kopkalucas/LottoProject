package com.lotto.domain.resultannoucer;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResultResponse {

        @Id
        String hash;
        Set<Integer> numbers;
        Set<Integer> wonNumbers;
        Set<Integer> hitNumbers;
        LocalDateTime drawDate;
        boolean isWinner;
        LocalDateTime createdDate;
}