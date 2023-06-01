package com.example.teacherwanted.active.service.impl;

import com.example.teacherwanted.active.dao.ActiveDao;
import com.example.teacherwanted.active.dao.ActiveOrderDetailDao;
import com.example.teacherwanted.active.dao.MemberDao;
import com.example.teacherwanted.active.model.Active;
import com.example.teacherwanted.active.model.ActiveOrderDetail;
import com.example.teacherwanted.active.model.Member;
import com.example.teacherwanted.active.service.ActiveOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Service
public class ActiveOrderDetailServiceImpl implements ActiveOrderDetailService {

    @Autowired
    private ActiveDao activeDao;

    @Autowired
    private ActiveOrderDetailDao activeOrderDetailDao;

    @Autowired
    private MemberDao memberDao;

    //    進行是否有購買過訂單判斷
    @Override
    public boolean queryActiveOrderHistory(Integer activityId, Integer memId) {
        List<ActiveOrderDetail> activeOrderDetail = activeOrderDetailDao.selectActiveOrderDetailByMemberId(memId);
        if (activeOrderDetail.size() == 0) {
            return true;
        } else {
            for (int i = 0; i < activeOrderDetail.size(); i++) {
                if (Objects.equals(activeOrderDetail.get(i).getActivityId(), activityId)) {
                    return false;
                }
            }
            return true;
        }


    }

    @Override
    public List<ActiveOrderDetail> findByActiveId(Integer id) {
        return activeOrderDetailDao.findByActiveId(id);
    }

    //    查找訂單使用者簡易資訊
    @Override
    public Member selectMemBerOrderInfo(Integer id) {
        Member member = memberDao.selectById(id);
        Member memberInfo = new Member();
        memberInfo.setMemId(id);
        memberInfo.setMemName(member.getMemName());
        memberInfo.setMemEmail(member.getMemEmail());
        memberInfo.setMemPhone(member.getMemPhone());
        return memberInfo;
    }

    @Override
    public List<ActiveOrderDetail> selectAll() {
        return activeOrderDetailDao.selectAll();
    }

    @Override
    public List<ActiveOrderDetail> selectActiveOrderDetailByMemberId(Integer id) {
        return activeOrderDetailDao.selectActiveOrderDetailByMemberId(id);

    }

    //    添加訂單
    @Transactional
    @Override
    public boolean insert(ActiveOrderDetail activeOrderDetail) {
        //        訂單資料
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        activeOrderDetail.setRegisterTime(timestamp);
        activeOrderDetailDao.insert(activeOrderDetail);
        //        報名人數
        Active active = activeDao.selectById(activeOrderDetail.getActivityId());
        //        沒超過上限人數的話
        if (active.getCurrentNumber().intValue() < active.getMaxNumber().intValue()) {
            active.setCurrentNumber((active.getCurrentNumber() + 1));
            return true;
        } else {
            return false;
        }

    }
}
