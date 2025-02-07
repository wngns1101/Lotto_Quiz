package com.example.mobilefactory_assignment.config;

import com.example.mobilefactory_assignment.facade.ParticipantFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchedulerConfig {
    private final ParticipantFacade participantFacade;

    @Scheduled(cron = "0 0 0 25 4 ?")
    public void run() {
        participantFacade.sendLottoResult();
    }
}
