package org.example;

public class GameResult {
    private final boolean isWinner;
    private final String resultMessege;

    public GameResult(boolean isWinner,String resultMessege) {
        this.isWinner = isWinner;
        this.resultMessege = resultMessege;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public String getResultMessege() {
        return resultMessege;
    }
}
