package com.example.teacherwanted.member.dao;

import com.example.teacherwanted.core.dao.CoreDao;
import com.example.teacherwanted.member.model.MemberCalendar;

import java.util.List;

public interface MemberCalendarDao extends CoreDao<MemberCalendar,Integer,String> {
    List<MemberCalendar> selectByMemId(Integer id);
}
