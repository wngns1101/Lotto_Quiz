package com.example.mobilefactory_assignment.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class ParticipantController {

    @GetMapping("/")
    public String getIndex(HttpServletRequest request, HttpServletResponse response) {
        // 현재 날짜
        LocalDate today = LocalDate.now();

        // 이벤트 기간
        LocalDate eventStart = LocalDate.of(2025, 2, 1);
        LocalDate eventEnd = LocalDate.of(2025, 3, 31);

        // 발표 기간
        LocalDate announceStart = LocalDate.of(2025, 4, 1);
        LocalDate announceEnd = LocalDate.of(2025, 4, 15);

        // 발표 기간일 때
        if (today.isBefore(announceEnd) && today.isAfter(announceStart)) {
            return "redirect:/announcement";
        }

        // 이벤트 기간일 때
        if (today.isBefore(eventEnd) && today.isAfter(eventStart)) {
            Cookie[] cookies = request.getCookies();

            // 방문 기록이 없다면
            if (cookies == null) {
                setVisitCookie(response);
                return "redirect:/event";
            }

            for (Cookie cookie : cookies) {
                // 방문 기록이 있을 때
                if ("isVisited".equals(cookie.getName())) {
                    // 이전 방문 날짜
                    String isVisitedDate = cookie.getValue();

                    // 현재 날짜
                    String todayDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

                    // 방문 기록이 오늘이 아닐 때
                    if (!isVisitedDate.equals(todayDate)) {
                        setVisitCookie(response);
                        return "redirect:/event";
                    }
                }
            }
        }

        return "index";
    }

    private void setVisitCookie(HttpServletResponse response) {
        String today = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        Cookie cookie = new Cookie("isVisited", today);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24);

        response.addCookie(cookie);
    }

    @GetMapping("/event")
    public String getEvent() {
        return "event";
    }

    @GetMapping("/announcement")
    public String getAnnouncement() {
        return "announcement";
    }
}
