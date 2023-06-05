package com.example.teacherwanted.member.controller;

import com.example.teacherwanted.member.model.MemberCalendar;
import com.example.teacherwanted.member.service.MemberCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CalendarController {

    @Autowired
    private MemberCalendarService memberCalendarService;

    @GetMapping("/calendarGet")
    public ResponseEntity<?> calendarGet(
            @SessionAttribute(value = "MemberId", required = false) Integer memId) {

        List<MemberCalendar> memberCalendarList = memberCalendarService.selectByMemId(memId);

        return ResponseEntity.ok(memberCalendarList);
    }

    @PostMapping("/calendarPost")
    public ResponseEntity<?> calendarInsert(
            @RequestBody MemberCalendar memberCalendar,
            @SessionAttribute(value = "MemberId", required = false) Integer memId) {

        memberCalendar.setMemId(memId);

        return ResponseEntity.ok(memberCalendarService.insert(memberCalendar));
    }

    @PutMapping ("/calendarPut")
    public ResponseEntity<?> calendarUpdate(
            @RequestBody MemberCalendar memberCalendar,
            @SessionAttribute(value = "MemberId", required = false) Integer memId) {
        memberCalendar.setMemId(memId);

        return ResponseEntity.ok(memberCalendarService.update(memberCalendar));
    }

    @DeleteMapping ("/calendarDelete")
    public ResponseEntity<?> calendarDelete(
            @RequestParam Integer calendarId,
            @SessionAttribute(value = "MemberId", required = false) Integer memId) {


        return ResponseEntity.ok(memberCalendarService.deleteById(calendarId));
    }

}
