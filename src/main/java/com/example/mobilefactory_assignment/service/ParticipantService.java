package com.example.mobilefactory_assignment.service;

import com.example.mobilefactory_assignment.entity.LottoState;
import com.example.mobilefactory_assignment.entity.LottoStateCumulative;
import com.example.mobilefactory_assignment.entity.Participant;
import com.example.mobilefactory_assignment.repository.LottoStateCumulativeRepository;
import com.example.mobilefactory_assignment.repository.LottoStateRepository;
import com.example.mobilefactory_assignment.repository.ParticipantRepository;
import com.example.mobilefactory_assignment.service.dto.ParticipantDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private final LottoStateRepository lottoStateRepository;
    private final LottoStateCumulativeRepository lottoStateCumulativeRepository;
    private static final int MAX_PARTICIPANTS = 10000; // 최대 참가자 수
    private static final Random random = new Random();

    @Transactional
    public void createParticipate(String phoneNumber) {
        Long participantTotalCount = participantRepository.count();

        if (participantTotalCount >= MAX_PARTICIPANTS) {
            throw new IllegalArgumentException("참가자는 10,000명 이하로 제한됩니다.");
        }

        if (participantRepository.existsByPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("이미 참여한 번호입니다");
        }

        LottoState lottoState = lottoStateRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("로또가 존재하지 않습니다."));
        LottoStateCumulative lottoStateCumulative = lottoStateCumulativeRepository.findByLottoStateId(lottoState.getLottoStateId());

        if (lottoState.getFirstPrizePhoneNumber().equals(phoneNumber)) {
            Participant participant = Participant.builder()
                    .lottoNumber(lottoState.getFirstPrizeLottoNumber())
                    .phoneNumber(lottoState.getFirstPrizePhoneNumber())
                    .isWinning(1)
                    .build();
            participantRepository.save(participant);

            lottoStateCumulative.increaseCumulativeCountRank1();

//          TODO: 문자 전송 로직
        } else {
            int rank = getRankForParticipant(participantTotalCount, lottoState, lottoStateCumulative);
            String lottoNumber = generateLottoNumberForRank(rank, lottoState.getFirstPrizeLottoNumber());

            Participant participant = Participant.builder()
                    .lottoNumber(lottoNumber)
                    .phoneNumber(phoneNumber)
                    .isWinning(rank)
                    .build();
            participantRepository.save(participant);

            updateCumulativeCount(rank, lottoStateCumulative);
//          TODO: 문자 전송 로직

        }

    }

    @Transactional
    public String getAnnouncement(String phoneNumber) {
        Participant participant = participantRepository.findByPhoneNumber(phoneNumber);
        if (participant == null) {
            throw new IllegalArgumentException("참여하지 않은 전화번호 입니다.");
        }

        int rank = participant.getIsWinning();

        if (participant.isChecked()) {
            if (rank == 5) {
                return "미당첨";
            } else {
                return "당첨";
            }
        } else {
            participant.updateIsChecked();

            if (rank == 5) {
                return "미당첨";
            } else {
                return "당첨 등수: " + rank;
            }
        }
    }

    public List<ParticipantDTO> sendLottoResult() {
        List<Participant> participantList = participantRepository.findAllByIsCheckedIsFalse();

        return participantList.stream().map(ParticipantDTO::toDTO).toList();
    }

    // 복권 등수 추출 함수
    private int getRankForParticipant(Long participantTotalCount, LottoState lottoState, LottoStateCumulative lottoStateCumulative) {
        if (participantTotalCount >= 4000 && participantTotalCount <= 6000 && lottoState.getCountRank2() > lottoStateCumulative.getCumulativeCountRank2()) {
            return 2;
        } else if (participantTotalCount >= 2000 && participantTotalCount <= 7000 && lottoState.getCountRank3() > lottoStateCumulative.getCumulativeCountRank3()) {
            return 3;
        } else if (participantTotalCount >= 1000 && participantTotalCount <= 8000 && lottoState.getCountRank4() > lottoStateCumulative.getCumulativeCountRank4()) {
            return 4;
        } else {
            return 5;
        }
    }

    // 복권 번호 추출 함수
    private String generateLottoNumberForRank(int rank, String firstPrizeLottoNumber) {
        if (rank == 2) {
            return generateIdenticalLottoNumber(5, firstPrizeLottoNumber); // 5자리 동일
        } else if (rank == 3) {
            return generateIdenticalLottoNumber(4, firstPrizeLottoNumber); // 4자리 동일
        } else if (rank == 4) {
            return generateIdenticalLottoNumber(3, firstPrizeLottoNumber); // 3자리 동일
        } else {
            return generateRandomLottoNumber(); // 5등 이하 랜덤 번호
        }
    }

    // 등수별 복권 번호 생성 함수
    private String generateIdenticalLottoNumber(int matchingDigits, String firstPrizeLottoNumber) {
        Set<Integer> lottoNumbers = new HashSet<>();
        String[] winningNumbers = firstPrizeLottoNumber.split("\\.");
        Set<Integer> matchedNumbers = new HashSet<>();

        for (int i = 0; i < matchingDigits; i++) {
            matchedNumbers.add(Integer.parseInt(winningNumbers[i]));
        }

        lottoNumbers.addAll(matchedNumbers);

        while (lottoNumbers.size() < 6) {
            int number = random.nextInt(45) + 1;
            lottoNumbers.add(number);
        }

        Integer[] sortedNumbers = lottoNumbers.toArray(new Integer[0]);
        java.util.Arrays.sort(sortedNumbers);
        return String.join(".",
                java.util.Arrays.stream(sortedNumbers)
                        .map(String::valueOf)
                        .toArray(String[]::new)
        );
    }

    // 복권 번호 생성 함수
    private String generateRandomLottoNumber() {
        Set<Integer> lottoNumbers = new HashSet<>();
        while (lottoNumbers.size() < 6) {
            int number = random.nextInt(45) + 1;
            lottoNumbers.add(number);
        }

        Integer[] sortedNumbers = lottoNumbers.toArray(new Integer[0]);
        java.util.Arrays.sort(sortedNumbers);
        return String.join(".",
                java.util.Arrays.stream(sortedNumbers)
                        .map(String::valueOf)
                        .toArray(String[]::new)
        );
    }

    // 누적 복권 카운트 관련 함수
    private void updateCumulativeCount(int rank, LottoStateCumulative lottoStateCumulative) {
        if (rank == 2) {
            lottoStateCumulative.increaseCumulativeCountRank2();
        } else if (rank == 3) {
            lottoStateCumulative.increaseCumulativeCountRank3();
        } else if (rank == 4) {
            lottoStateCumulative.increaseCumulativeCountRank4();
        }
    }
}
