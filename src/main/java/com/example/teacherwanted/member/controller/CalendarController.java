package com.example.teacherwanted.member.controller;

import com.example.teacherwanted.member.model.MemberCalendar;
import com.example.teacherwanted.member.service.MemberCalendarService;
import com.example.teacherwanted.register_login.entity.User;
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
            @SessionAttribute(value = "userInfo", required = false) User user ) {

        List<MemberCalendar> memberCalendarList = memberCalendarService.selectByMemId(user.getMemId());

        return ResponseEntity.ok(memberCalendarList);
    }

    @PostMapping("/calendarPost")
    public ResponseEntity<?> calendarInsert(
            @RequestBody MemberCalendar memberCalendar,
            @SessionAttribute(value = "userInfo", required = false) User user ) {

        memberCalendar.setMemId(user.getMemId());

        return ResponseEntity.ok(memberCalendarService.insert(memberCalendar));
    }

    @PutMapping ("/calendarPut")
    public ResponseEntity<?> calendarUpdate(
            @RequestBody MemberCalendar memberCalendar,
            @SessionAttribute(value = "userInfo", required = false) User user ) {
        memberCalendar.setMemId(user.getMemId());

        return ResponseEntity.ok(memberCalendarService.update(memberCalendar));
    }

    @DeleteMapping ("/calendarDelete")
    public ResponseEntity<?> calendarDelete(
            @RequestParam Integer calendarId
           ) {


        return ResponseEntity.ok(memberCalendarService.deleteById(calendarId));
    }

}
