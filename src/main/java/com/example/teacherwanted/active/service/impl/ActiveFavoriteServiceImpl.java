package com.example.teacherwanted.active.service.impl;

import com.example.teacherwanted.active.dao.ActiveFavoriteDao;
import com.example.teacherwanted.active.model.ActiveFavorite;
import com.example.teacherwanted.active.service.ActiveFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Service
public class ActiveFavoriteServiceImpl implements ActiveFavoriteService {

    @Autowired
    private ActiveFavoriteDao activeFavoriteDao;
    @Transactional
    @Override
    public boolean insert(ActiveFavorite activeFavorite) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        activeFavorite.setCreateTime(timestamp);
        activeFavoriteDao.insert(activeFavorite);
        return true;
    }


    @Override
    public boolean queryActiveFavoriteHistory(Integer activityId, Integer memId) {
        List<ActiveFavorite> activeFavoriteList = activeFavoriteDao.selectActiveFavoriteByMemberId(memId);
        if (activeFavoriteList.size() == 0) {
            return true;
        } else {
            for (int i = 0; i < activeFavoriteList.size(); i++) {
                if (Objects.equals(activeFavoriteList.get(i).getActivityId(), activityId)) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    @Transactional
    public String deleteByIdAndMemId(Integer id,Integer memId) {
        try {
            activeFavoriteDao.deleteByIdAndMemId(id,memId);
            return "刪除成功";
        } catch (Exception e) {
            return "錯誤：" + e.getMessage();
        }
    }
}
