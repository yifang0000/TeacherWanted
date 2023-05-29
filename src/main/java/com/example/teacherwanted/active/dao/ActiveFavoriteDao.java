package com.example.teacherwanted.active.dao;

import com.example.teacherwanted.active.model.ActiveFavorite;
import com.example.teacherwanted.active.model.ActiveOrderDetail;
import com.example.teacherwanted.core.dao.CoreDao;

import java.util.List;

public interface ActiveFavoriteDao extends CoreDao<ActiveFavorite,Integer,String> {
    List<ActiveFavorite> selectActiveFavoriteByMemberId(Integer id);
}
