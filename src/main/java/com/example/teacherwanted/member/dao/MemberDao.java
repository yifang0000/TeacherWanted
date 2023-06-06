package com.example.teacherwanted.member.dao;

import com.example.teacherwanted.core.dao.CoreDao;
import com.example.teacherwanted.member.model.Member;
import com.example.teacherwanted.wish.entity.Wish;

import java.util.List;

public interface MemberDao extends CoreDao<Member,Integer,String> {

        Member selectById(Integer id);


    List<Wish> getWishByMemId(Integer memId);
}
