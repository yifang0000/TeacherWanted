package com.example.teacherwanted.active.dao;

import com.example.teacherwanted.active.model.MemberCalendar;
import com.example.teacherwanted.core.dao.CoreDao;

import java.util.List;

public interface MemberCalendarDao extends CoreDao<MemberCalendar,Integer,String> {
    List<MemberCalendar> selectByMemId(Integer id);
}
