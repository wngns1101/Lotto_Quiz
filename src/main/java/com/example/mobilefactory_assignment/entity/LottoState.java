package com.example.mobilefactory_assignment.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "lotto_state")
@Getter
public class LottoState extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lotto_state_id")
    private Long lottoStateId;

    @Column(name = "first_prize_phone_number")
    private String firstPrizePhoneNumber;

    @Column(name = "first_prize_lotto_number")
    private String firstPrizeLottoNumber;

    @Column(name = "count_rank_1")
    private int countRank1;

    @Column(name = "count_rank_2")
    private int countRank2;

    @Column(name = "count_rank_3")
    private int countRank3;

    @Column(name = "count_rank_4")
    private int countRank4;

}
