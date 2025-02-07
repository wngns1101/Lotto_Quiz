package com.example.mobilefactory_assignment.facade;

import com.example.mobilefactory_assignment.service.ParticipantService;
import com.example.mobilefactory_assignment.service.dto.ParticipantDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ParticipantFacade {
    private final ParticipantService participantService;

    public void createParticipate(String phoneNumber) {
        participantService.createParticipate(phoneNumber);
    }

    public String getAnnouncement(String phoneNumber) {
        return participantService.getAnnouncement(phoneNumber);
    }

    public void sendLottoResult() {
        List<ParticipantDTO> participantDTOList = participantService.sendLottoResult();
        // TODO: 메세지 전송
    }
}
