package com.example.teacherwanted.member.service.impl;


import com.example.teacherwanted.active.model.ActiveOrderDetail;
import com.example.teacherwanted.member.dao.MemberDao;
import com.example.teacherwanted.member.model.Member;
import com.example.teacherwanted.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.util.List;


@Service
@Qualifier("memberServiceImpl")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;


    //  要確認會員資料是否都可以被抓到, 以下是抓不到時間, 新增更新時間
    @Override
    @Transactional
    public String update(Member member) {
//        Member memberCreateTime = memberDao.selectById(member.getMemId());
//        Member.setCreateTime(memberCreateTime.getCreateTime());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        member.setCreateTime(timestamp);
        try {
            memberDao.update(member);
            return "更新成功";
        } catch (Exception e) {
            return "錯誤：" + e.getMessage();
        }

    }

    @Override
    public Member selectById(Integer id) {
          return memberDao.selectById(id);
      }

    @Override
    public List<ActiveOrderDetail> selectActiveOrderDetailByMemberId(Integer id) {
        return null;
    }

    @Override
    public List<ActiveOrderDetail> selectAll() {
        return null;
    }

    @Override
    public String deleteById(Integer id) {
        return null;
    }

    @Override
    public Member selectBackOrderById(Integer id){ return null;
    }

    //      @Override
//      public List<Member> selectAll() {
//          return memberDao.selectAll();
//    }
}
