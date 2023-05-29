package com.example.teacherwanted.active.service.impl;

import com.example.teacherwanted.active.dao.MemberDao;
import com.example.teacherwanted.active.model.Member;
import com.example.teacherwanted.active.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;


    @Override
    public Member selectById(Integer id) {
        return memberDao.selectById(id);
    }

}
