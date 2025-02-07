package com.example.mobilefactory_assignment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Table(name = "lotto_state_cumulative")
@Entity
@Getter
public class LottoStateCumulative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lottoStateCumulativeId;

    @Column(name = "lotto_state_id")
    private Long lottoStateId;

    @Column(name = "cumulative_count_rank1")
    private int cumulativeCountRank1;

    @Column(name = "cumulative_count_rank2")
    private int cumulativeCountRank2;

    @Column(name = "cumulative_count_rank3")
    private int cumulativeCountRank3;

    @Column(name = "cumulative_count_rank4")
    private int cumulativeCountRank4;

    public void increaseCumulativeCountRank1() {
        cumulativeCountRank1++;
    }

    public void increaseCumulativeCountRank2() {
        cumulativeCountRank2++;
    }

    public void increaseCumulativeCountRank3() {
        cumulativeCountRank3++;
    }

    public void increaseCumulativeCountRank4() {
        cumulativeCountRank4++;
    }
}
