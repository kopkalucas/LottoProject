package org.example;

import org.example.console.ui.ScannerInputReceiver;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGameTest {

    @Test
    public void should_return_winner_when_user_gave_six_correct_numbers_winners_numbers_are_the_same(){
        //Given
        InputReceivable scannerInputReceiver = () -> Set.of(1,2,3,4,5,6);
        UserNumberReciver userNumberReciver = new UserNumberReciver(scannerInputReceiver);
        NumberGenerable lottoNumberGenerator = () -> Set.of(1,2,3,4,5,6);
        LottoGame lottoGame = new LottoGame(userNumberReciver,lottoNumberGenerator);
        //When
        GameResult gameResult = lottoGame.play();
        //Then
        assertThat(gameResult.isWinner()).isEqualTo(true);

    }

    @Test
    public void should_return_looser_when_user_gave_six_correct_numbers_winners_numbers_are_not_the_same(){
        //Given
        InputReceivable scannerInputReceiver = () -> Set.of(1,2,3,4,5,6);
        UserNumberReciver userNumberReciver = new UserNumberReciver(scannerInputReceiver);
        NumberGenerable lottoNumberGenerator = () -> Set.of(15,21,30,41,51,6);
        LottoGame lottoGame = new LottoGame(userNumberReciver,lottoNumberGenerator);
        //When
        GameResult gameResult = lottoGame.play();
        //Then
        assertThat(gameResult.isWinner()).isEqualTo(false);
    }

    @Test
    public void should_return_looser_when_user_gave_atleast_one_incorect_numbers(){
        //Given
        InputReceivable scannerInputReceiver = () -> Set.of(1,-5,3,150,5,6);
        UserNumberReciver userNumberReciver = new UserNumberReciver(scannerInputReceiver);
        NumberGenerable lottoNumberGenerator = () -> Set.of(1,2,3,4,5,6);
        LottoGame lottoGame = new LottoGame(userNumberReciver,lottoNumberGenerator);
        //When
        GameResult gameResult = lottoGame.play();
        //Then
        assertThat(gameResult.isWinner()).isEqualTo(false);
    }

    @Test
    public void should_return_looser_when_user_gave_more_than_six_numbers(){
        //Given
        InputReceivable scannerInputReceiver = () -> Set.of(1,2,3,4,5,6,8,9);
        UserNumberReciver userNumberReciver = new UserNumberReciver(scannerInputReceiver);
        NumberGenerable lottoNumberGenerator = () -> Set.of(1,2,3,4,5,6);
        LottoGame lottoGame = new LottoGame(userNumberReciver,lottoNumberGenerator);
        //When
        GameResult gameResult = lottoGame.play();
        //Then
        assertThat(gameResult.isWinner()).isEqualTo(false);
    }

    @Test
    public void should_return_looser_when_user_gave_less_than_six_numbers(){
        //Given
        InputReceivable scannerInputReceiver = () -> Set.of(1,2,3);
        UserNumberReciver userNumberReciver = new UserNumberReciver(scannerInputReceiver);
        NumberGenerable lottoNumberGenerator = () -> Set.of(1,2,3,4,5,6);
        LottoGame lottoGame = new LottoGame(userNumberReciver,lottoNumberGenerator);
        //When
        GameResult gameResult = lottoGame.play();
        //Then
        assertThat(gameResult.isWinner()).isEqualTo(false);
    }




}
