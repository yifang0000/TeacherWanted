package com.example.teacherwanted.active.dao;

import com.example.teacherwanted.active.model.MemberActive;
import com.example.teacherwanted.active.model.MemberActive;
import com.example.teacherwanted.core.dao.CoreDao;

public interface MemberDaoActive extends CoreDao<MemberActive,Integer,String> {
    MemberActive selectById(Integer id);
}
