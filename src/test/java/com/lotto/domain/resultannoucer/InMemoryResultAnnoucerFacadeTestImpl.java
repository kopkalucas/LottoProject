package com.lotto.domain.resultannoucer;

import com.lotto.domain.resultannoucer.ResponseRepository;
import com.lotto.domain.resultannoucer.ResultResponse;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryResultAnnoucerFacadeTestImpl implements ResponseRepository {

    private final Map<String, ResultResponse> inMemoryDatabase = new ConcurrentHashMap<>();

    @Override
    public ResultResponse save (ResultResponse resultResponse) {
        inMemoryDatabase.put(resultResponse.getHash(), resultResponse);
        return  resultResponse;
    }

    @Override
    public Optional<ResultResponse> findById(String hash) {
        return Optional.ofNullable(inMemoryDatabase.get(hash));
    }

    @Override
    public boolean existsById(String hash) {
        return inMemoryDatabase.containsKey(hash);
    }

}
