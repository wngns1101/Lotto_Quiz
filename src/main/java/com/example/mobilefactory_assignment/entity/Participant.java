package com.example.mobilefactory_assignment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "participant")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Participant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participant_id")
    private Long participantId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "lotto_number")
    private String lottoNumber;

    @Column(name = "is_winning")
    private int isWinning;

    @Column(name = "is_checked")
    private boolean isChecked = false;

    @Builder
    public Participant(String phoneNumber, String lottoNumber, int isWinning) {
        this.phoneNumber = phoneNumber;
        this.lottoNumber = lottoNumber;
        this.isWinning = isWinning;
    }

    public void updateIsChecked() {
        this.isChecked = true;
    }
}
