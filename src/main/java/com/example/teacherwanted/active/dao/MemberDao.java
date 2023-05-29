package com.example.teacherwanted.active.dao;

import com.example.teacherwanted.active.model.Member;
import com.example.teacherwanted.core.dao.CoreDao;

public interface MemberDao extends CoreDao<Member,Integer,String> {
    Member selectById(Integer id);
}
