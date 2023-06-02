package com.example.teacherwanted.member.service;


import com.example.teacherwanted.member.dao.MemberDao;
import com.example.teacherwanted.member.model.Member;

import java.util.List;

public interface MemberService {


    String update(Member member);

    Member selectById(Integer id);





}
