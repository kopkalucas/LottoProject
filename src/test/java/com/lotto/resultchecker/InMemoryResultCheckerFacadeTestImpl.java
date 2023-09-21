package com.lotto.resultchecker;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryResultCheckerFacadeTestImpl implements PlayerRepository{

    private final Map<String, Player> playersList = new ConcurrentHashMap<>();

    @Override
    public List<Player> saveAll(List<Player> player) {
       player.forEach(player1 -> playersList.put(player1.hash(), player1));
       return player;
    }

    @Override
    public Optional<Player> findById(String hash) {
        return Optional.ofNullable(playersList.get(hash));
    }
}
