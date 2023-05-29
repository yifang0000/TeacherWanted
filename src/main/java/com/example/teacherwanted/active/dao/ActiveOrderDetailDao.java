package com.example.teacherwanted.active.dao;

import com.example.teacherwanted.active.model.ActiveOrderDetail;
import com.example.teacherwanted.core.dao.CoreDao;

import java.util.List;

public interface ActiveOrderDetailDao extends CoreDao<ActiveOrderDetail,Integer,String> {
    List<ActiveOrderDetail> selectActiveOrderDetailByMemberId(Integer id);
}
