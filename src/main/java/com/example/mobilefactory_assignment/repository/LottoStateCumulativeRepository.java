package com.example.mobilefactory_assignment.repository;

import com.example.mobilefactory_assignment.entity.LottoStateCumulative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LottoStateCumulativeRepository extends JpaRepository<LottoStateCumulative, Long> {
    LottoStateCumulative findByLottoStateId(Long lottoStateId);
}
