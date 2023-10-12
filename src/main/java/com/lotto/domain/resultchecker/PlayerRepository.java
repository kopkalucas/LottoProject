package com.lotto.domain.resultchecker;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends Repository<Player, String> {

    Optional<Player> findById(String hash);

    <S extends Player> Iterable<S> saveAll(Iterable<S> entities);
}
