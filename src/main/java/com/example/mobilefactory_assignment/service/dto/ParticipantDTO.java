package com.example.mobilefactory_assignment.service.dto;

import com.example.mobilefactory_assignment.entity.Participant;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ParticipantDTO {
    private Long participantId;
    private String phoneNumber;
    private String lottoNumber;
    private int isWinning;
    private boolean isChecked;
    private LocalDateTime createdAt;

    public static ParticipantDTO toDTO(Participant participant) {
        return ParticipantDTO.builder()
                .participantId(participant.getParticipantId())
                .phoneNumber(participant.getPhoneNumber())
                .lottoNumber(participant.getLottoNumber())
                .isWinning(participant.getIsWinning())
                .isChecked(participant.isChecked())
                .createdAt(LocalDateTime.from(participant.getCreatedAt()))
                .build();
    }
}
