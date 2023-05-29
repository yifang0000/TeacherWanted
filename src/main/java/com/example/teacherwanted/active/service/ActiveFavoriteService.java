package com.example.teacherwanted.active.service;

import com.example.teacherwanted.active.model.ActiveFavorite;
import com.example.teacherwanted.active.model.ActiveOrderDetail;

public interface ActiveFavoriteService {
    boolean insert(ActiveFavorite activeFavorite);
    boolean queryActiveFavoriteHistory(Integer activityId, Integer memId);
}
