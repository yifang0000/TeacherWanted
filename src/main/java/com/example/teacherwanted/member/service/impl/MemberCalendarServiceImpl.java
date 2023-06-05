package com.example.teacherwanted.member.service.impl;

import com.example.teacherwanted.member.dao.MemberCalendarDao;
import com.example.teacherwanted.member.model.MemberCalendar;
import com.example.teacherwanted.member.service.MemberCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class MemberCalendarServiceImpl implements MemberCalendarService {

    @Autowired
    private MemberCalendarDao memberCalendarDao;

    @Override
    public List<MemberCalendar> selectByMemId(Integer id) {
        return memberCalendarDao.selectByMemId(id);
    }

    @Transactional
    @Override
    public String insert(MemberCalendar memberCalendar) {
        try {
            memberCalendarDao.insert(memberCalendar);
            return "新增成功";
        } catch (Exception e) {
            return "錯誤:" + e;
        }


    }

    @Transactional
    @Override
    public String update(MemberCalendar memberCalendar) {

        try {
            memberCalendarDao.update(memberCalendar);
            return "修改成功";
        } catch (Exception e) {
            return "錯誤:" + e;
        }
    }
    @Transactional
    @Override
    public String deleteById(Integer id) {
        try {
            memberCalendarDao.deleteById(id);
            return "刪除成功";
        } catch (Exception e) {
            return "錯誤:" + e;
        }
    }
}
