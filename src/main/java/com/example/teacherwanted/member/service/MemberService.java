package com.example.teacherwanted.member.service;


import com.example.teacherwanted.active.model.ActiveOrderDetail;
import com.example.teacherwanted.member.dao.MemberDao;
import com.example.teacherwanted.member.model.Member;

import java.util.List;

public interface MemberService {


    String update(Member member);

    Member selectById(Integer id);
    //會員課程訂單查詢

    //會員活動訂單查詢
    List<ActiveOrderDetail> selectActiveOrderDetailByMemberId(Integer id);

    //data 取資料方法
    List<ActiveOrderDetail> selectAll();

    //刪除
    String deleteById(Integer id);
}
