package com.example.mobilefactory_assignment;

import com.example.mobilefactory_assignment.service.ParticipantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MobileFactoryAssignmentApplicationTests {
    @Autowired
    ParticipantService participantService;

    @Test
    public void testCreate3000Participants() {
        // 3000명의 참가자를 생성하는 테스트
        for (int i = 0; i < 3000; i++) {
            // 전화번호는 010-1111-0000부터 010-1234-2999까지
            String phoneNumber = String.format("010-1111-%04d", i);
            participantService.createParticipate(phoneNumber);
        }
    }
}
