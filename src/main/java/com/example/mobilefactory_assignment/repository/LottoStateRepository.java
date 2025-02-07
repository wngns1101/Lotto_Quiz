package com.example.mobilefactory_assignment.repository;

import com.example.mobilefactory_assignment.entity.LottoState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LottoStateRepository extends JpaRepository<LottoState, Long> {
}
