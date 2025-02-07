package com.example.mobilefactory_assignment.api;

import com.example.mobilefactory_assignment.api.http.req.CreateLottoRequestDTO;
import com.example.mobilefactory_assignment.facade.ParticipantFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ParticipantApiController {
    private final ParticipantFacade participantFacade;

    @PostMapping("/submit")
    public void createParticipate (
        @RequestBody CreateLottoRequestDTO request
    ) {
        participantFacade.createParticipate(request.getPhoneNumber());
    }

    @GetMapping("/announcement")
    public String getAnnouncement(
        @RequestParam String phoneNumber
    ) {
        return participantFacade.getAnnouncement(phoneNumber);
    }
}
