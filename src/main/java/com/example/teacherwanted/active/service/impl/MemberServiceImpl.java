package com.example.teacherwanted.active.service.impl;

import com.example.teacherwanted.active.dao.MemberDaoActive;
import com.example.teacherwanted.active.dao.MemberDaoActive;
import com.example.teacherwanted.active.model.MemberActive;
import com.example.teacherwanted.active.model.MemberActive;
import com.example.teacherwanted.active.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDaoActive memberDao;


    @Override
    public MemberActive selectById(Integer id) {
        return memberDao.selectById(id);
    }

}
