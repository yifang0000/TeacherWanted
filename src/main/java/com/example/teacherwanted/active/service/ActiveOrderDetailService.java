package com.example.teacherwanted.active.service;


import com.example.teacherwanted.active.model.Active;
import com.example.teacherwanted.active.model.ActiveOrderDetail;
import com.example.teacherwanted.active.model.MemberActive;

import java.util.List;

public interface ActiveOrderDetailService {
    List<ActiveOrderDetail> selectAll() ;
    List<ActiveOrderDetail> selectActiveOrderDetailByMemberId(Integer id);
    boolean insert(ActiveOrderDetail activeOrderDetail);

    MemberActive selectMemBerOrderInfo(Integer memId);

    boolean queryActiveOrderHistory(Integer activityId, Integer memId);

    List<ActiveOrderDetail> findByActiveId(Integer id);

    String deleteById(Integer id);
}
