package com.example.teacherwanted.active.service.impl;

import com.example.teacherwanted.active.dao.ActiveFavoriteDao;
import com.example.teacherwanted.active.model.ActiveFavorite;
import com.example.teacherwanted.active.service.ActiveFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Service
public class ActiveFavoriteServiceImpl implements ActiveFavoriteService {

    @Autowired
    private ActiveFavoriteDao activeFavoriteDao;

    @Override
    public boolean insert(ActiveFavorite activeFavorite) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        activeFavorite.setCreateTime(timestamp);
        activeFavoriteDao.insert(activeFavorite);
        return false;
    }

    @Override
    public boolean queryActiveFavoriteHistory(Integer activityId, Integer memId) {
        List<ActiveFavorite> activeFavoriteList = activeFavoriteDao.selectActiveFavoriteByMemberId(memId);
        if (activeFavoriteList.size() == 0) {
            System.out.println("沒有");
            return false;
        } else {
            for (int i = 0; i < activeFavoriteList.size(); i++) {
                if (Objects.equals(activeFavoriteList.get(i).getActivityId(), activityId)) {
                    System.out.println("迴圈比較失敗");
                    return false;
                }
            }
            return true;
        }
    }
}
