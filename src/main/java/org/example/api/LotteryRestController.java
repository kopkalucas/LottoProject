package org.example.api;

import lombok.AllArgsConstructor;
import org.example.GameResult;
import org.example.InputNumbersResult;
import org.example.LottoGame;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
public class LotteryRestController {

    private final LottoGame lottoGame;

    @PostMapping("/numbers")

    public ResponseEntity<GameResult> inputNumbers(@RequestBody NumberRequest numbers) {
        GameResult play = lottoGame.play(numbers.numbers());
        return ResponseEntity.ok(play);
    }

}
