package org.example;

public class GameResult {
    private final boolean isWinner;

    public GameResult(boolean isWinner) {
        this.isWinner = isWinner;
    }

    public boolean isWinner() {
        return isWinner;
    }
}
