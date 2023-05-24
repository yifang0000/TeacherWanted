package com.example.teacherwanted.active.dao;

import com.example.teacherwanted.active.model.Active;
import com.example.teacherwanted.core.dao.CoreDao;

import java.util.List;


public interface ActiveDao extends CoreDao<Active,Integer,String> {

    List<Active> recommendActivities(String activityType);
    int updateStatus(Active active);
}
