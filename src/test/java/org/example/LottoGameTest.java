package org.example;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGameTest {

    NumberGenerable lottoNumberGenerator = () -> Set.of(1,2,3,4,5,6);
    UserNumberValidator userNumberValidator = new UserNumberValidator();

    @Test
    public void should_return_winner_when_user_gave_six_correct_numbers_winners_numbers_are_the_same(){
        //Given
        Set<Integer> inputNumbers = Set.of(1,2,3,4,5,6);
        LottoGame lottoGame = new LottoGame(lottoNumberGenerator,userNumberValidator);
        //When
        GameResult gameResult = lottoGame.play(inputNumbers);
        //Then
        assertThat(gameResult.isWinner()).isEqualTo(true);

    }

    @Test
    public void should_return_looser_when_user_gave_six_correct_numbers_winners_numbers_are_not_the_same(){
        //Given
        Set<Integer> inputNumbers = Set.of(7,8,9,10,11,12);
        LottoGame lottoGame = new LottoGame(lottoNumberGenerator,userNumberValidator);
        //When
        GameResult gameResult = lottoGame.play(inputNumbers);
        //Then
        assertThat(gameResult.isWinner()).isEqualTo(false);
    }

    @Test
    public void should_return_looser_when_user_gave_atleast_one_incorect_numbers(){
        //Given
        Set<Integer> inputNumbers = Set.of(1,-5,3,150,5,6);
        LottoGame lottoGame = new LottoGame(lottoNumberGenerator,userNumberValidator);
        //When
        GameResult gameResult = lottoGame.play(inputNumbers);
        //Then
        assertThat(gameResult.isWinner()).isEqualTo(false);
        //assertThat(gameResult.getResultMessege()).isEqualTo("");
    }

    @Test
    public void should_return_looser_when_user_gave_more_than_six_numbers(){
        //Given
        Set<Integer> inputNumbers = Set.of(1,2,3,4,5,6,7,8,9);
        LottoGame lottoGame = new LottoGame(lottoNumberGenerator,userNumberValidator);
        //When
        GameResult gameResult = lottoGame.play(inputNumbers);
        //Then
        assertThat(gameResult.isWinner()).isEqualTo(false);
    }

    @Test
    public void should_return_looser_when_user_gave_less_than_six_numbers(){
        //Given
        Set<Integer> inputNumbers = Set.of(1,2,3);
        LottoGame lottoGame = new LottoGame(lottoNumberGenerator,userNumberValidator);
        //When
        GameResult gameResult = lottoGame.play(inputNumbers);
        //Then
        assertThat(gameResult.isWinner()).isEqualTo(false);
    }


}
