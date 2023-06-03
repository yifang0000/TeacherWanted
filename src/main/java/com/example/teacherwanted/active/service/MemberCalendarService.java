package com.example.teacherwanted.active.service;

import com.example.teacherwanted.active.model.MemberCalendar;

import java.util.List;

public interface MemberCalendarService {
    List<MemberCalendar> selectByMemId(Integer id);

    String insert(MemberCalendar memberCalendar);

    String update(MemberCalendar memberCalendar);

    String deleteById(Integer id);
}
