package com.example.teacherwanted.member.dao;

import com.example.teacherwanted.core.dao.CoreDao;
import com.example.teacherwanted.member.model.Member;

public interface MemberDao extends CoreDao<Member,Integer,String> {

        Member selectById(Integer id);
}
