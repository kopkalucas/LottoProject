package com.lotto.domain.resultannoucer;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ResponseRepository extends Repository<ResultResponse, String> {

    ResultResponse save (ResultResponse resultResponse);

    Optional<ResultResponse> findById (String hash);

    boolean existsById(String hash);
}
