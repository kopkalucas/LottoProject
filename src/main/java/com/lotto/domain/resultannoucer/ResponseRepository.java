package com.lotto.domain.resultannoucer;

import java.util.Optional;

public interface ResponseRepository {

    ResultResponse save (ResultResponse resultResponse);

    Optional<ResultResponse> findById (String hash);

    boolean existsById(String hash);
}
