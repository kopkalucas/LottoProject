package com.lotto.resultchecker;

import com.lotto.numberreciver.CuponDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ResultCheckerRepository {
    private Map<String, CuponDto> inMemoryDatabase = new ConcurrentHashMap<>();


    public void saveAll(List<CuponDto> winnigCupons) {
        winnigCupons
                .forEach(cuponDto -> inMemoryDatabase.put(cuponDto.id(), cuponDto) );
    }

    public Optional<CuponDto> findById(String id) {
        CuponDto cuponDto = inMemoryDatabase.get(id);
        if(cuponDto == null) {
            return Optional.empty();
        }
        return Optional.of(cuponDto);
    }
}
