package com.example.teacherwanted.member.service;

import com.example.teacherwanted.member.model.MemberCalendar;

import java.util.List;

public interface MemberCalendarService {
    List<MemberCalendar> selectByMemId(Integer id);

    String insert(MemberCalendar memberCalendar);

    String update(MemberCalendar memberCalendar);

    String deleteById(Integer id);
}
