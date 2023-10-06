package com.lotto.domain.resultchecker;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlayerRepository extends CrudRepository<Player, String> {

    Optional<Player> findById (String hash);
}
