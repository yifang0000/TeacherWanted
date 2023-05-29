package com.example.teacherwanted.active.dao;

import com.example.teacherwanted.active.model.Active;
import com.example.teacherwanted.core.dao.CoreDao;

import java.util.List;


public interface ActiveDao extends CoreDao<Active,Integer,String> {

    //  前台所有活動
    List<Active> selectBackAll(String key, String type,Integer id);
    //  前台推薦活動
    List<Active> recommendActivities(String type);
    //  後臺更改狀態
    int updateStatus(Active active);

    List<Active> selectAllByKeyWorldAndType(String key,String type );
}
