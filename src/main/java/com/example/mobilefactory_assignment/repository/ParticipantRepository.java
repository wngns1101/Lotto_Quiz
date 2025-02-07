package com.example.mobilefactory_assignment.repository;

import com.example.mobilefactory_assignment.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    Boolean existsByPhoneNumber(String phoneNumber);
    Participant findByPhoneNumber(String phoneNumber);
    List<Participant> findAllByIsCheckedIsFalse();
}
