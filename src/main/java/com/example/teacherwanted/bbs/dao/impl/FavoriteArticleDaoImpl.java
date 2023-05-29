package com.example.teacherwanted.bbs.dao.impl;

import com.example.teacherwanted.bbs.dao.FavoriteArticleDao;
import com.example.teacherwanted.bbs.model.FavoriteArticle;
import com.example.teacherwanted.bbs.rowmapper.FavoriteArticleRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FavoriteArticleDaoImpl implements FavoriteArticleDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<FavoriteArticle> getFavoriteBymemId(Integer memId) {
        String sql = "SELECT favorite_article_id, bbs_post_id, mem_id, create_time, fav_status " +
                "FROM FAVORITE_ARTICLE WHERE mem_id = :memId ";
        Map<String, Object> map = new HashMap<>();
        map.put("memId", memId);
        List<FavoriteArticle> favoriteArticleList = namedParameterJdbcTemplate.query(sql, map, new FavoriteArticleRowMapper());

        return favoriteArticleList;


    }
}
