package com.example.teacherwanted.course.service.impl;

import com.example.teacherwanted.course.model.vo.MemberVo;
import com.example.teacherwanted.course.service.MemberService;
import com.example.teacherwanted.course.util.MemberRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class MembersServiceImpl implements MemberService {
    @Resource
    private MemberRepository memberRepository;

    @Override
    public MemberVo findById(Integer id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public MemberVo findByAccount(String account) {
        return memberRepository.findByMemAccount(account);
    }

    @Override
    public void save(MemberVo member) {
        memberRepository.save(member);
    }

    @Override
    public void update(MemberVo member) {
        MemberVo oldMember = memberRepository.findById(member.getMemId()).orElse(null);

        if (oldMember != null) {
            // 設定 create_time 的值為原本資料的值
            member.setCreateTime(oldMember.getCreateTime());
            member.setUpdateTime(oldMember.getUpdateTime());
        }
        memberRepository.save(member);
    }

    @Override
    public void deleteById(Integer id) {
        memberRepository.deleteById(id);
    }
}
