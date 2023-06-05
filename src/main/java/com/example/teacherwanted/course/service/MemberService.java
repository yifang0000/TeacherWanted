package com.example.teacherwanted.course.service;

import com.example.teacherwanted.course.model.vo.MemberVo;

public interface MemberService {
    MemberVo findById(Integer memid);
    MemberVo findByAccount(String account);
    void save(MemberVo member);
    void update(MemberVo member);
    void deleteById(Integer id);
}
