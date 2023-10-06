//package com.lotto.domain.resultchecker;
//
//import com.lotto.domain.resultchecker.Player;
//import com.lotto.domain.resultchecker.PlayerRepository;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class InMemoryResultCheckerFacadeTestImpl implements PlayerRepository {
//
//    private final Map<String, Player> inMemoryDatabase = new ConcurrentHashMap<>();
//
//    @Override
//    public List<Player> saveAll(List<Player> player) {
//       player.forEach(player1 -> inMemoryDatabase.put(player1.getHash(), player1));
//       return player;
//    }
//
//    @Override
//    public Optional<Player> findById(String hash) {
//        return Optional.ofNullable(inMemoryDatabase.get(hash));
//    }
//}
