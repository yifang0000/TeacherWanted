package com.example.teacherwanted.member.service.impl;

import com.example.teacherwanted.active.dao.ActiveDao;
import com.example.teacherwanted.active.dao.ActiveOrderDetailDao;
import com.example.teacherwanted.active.model.ActiveOrderDetail;
import com.example.teacherwanted.member.dao.MemberDao;
import com.example.teacherwanted.member.model.Member;
import com.example.teacherwanted.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@Qualifier("memberServiceOrderDetailImpl")
public class MemberServiceOrderDetailImpl implements MemberService{

    @Autowired
    private ActiveDao activeDao;

    @Autowired
    private ActiveOrderDetailDao activeOrderDetailDao;

    @Autowired
    private MemberDao memberDao;

    @Override
    public String update(Member member) {
        return null;
    }

    @Override
    public Member selectById(Integer id) {
        return null;
    }

    @Override
    public List<ActiveOrderDetail> selectActiveOrderDetailByMemberId(Integer id) {
        System.out.println(id);
        return activeOrderDetailDao.selectActiveOrderDetailByMemberId(id);

    }

    @Override
    public List<ActiveOrderDetail> selectAll() {
          return activeOrderDetailDao.selectAll();

    }

    @Override
    @Transactional
    public String deleteById(Integer id) {
        try {
            memberDao.deleteById(id);
            return "刪除成功";
        } catch (Exception e) {
            return "錯誤：" + e.getMessage();
        }
    }


}
