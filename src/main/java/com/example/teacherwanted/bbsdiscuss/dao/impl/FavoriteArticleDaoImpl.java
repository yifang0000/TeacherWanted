package com.example.teacherwanted.bbsdiscuss.dao.impl;

import com.example.teacherwanted.bbsdiscuss.dao.FavoriteArticleDao;
import com.example.teacherwanted.bbsdiscuss.model.FavoriteArticle;
import com.example.teacherwanted.bbsdiscuss.rowmapper.FavoriteArticleRowMapper;
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
    public List<FavoriteArticle> getBbsFavoriteBymemId(Integer memId) {
        String sql = "SELECT fa.favorite_article_id, fa.bbs_post_id, fa.mem_id, fa.create_time , fa.fav_status, " +
                "      bp.post_title, bp.post_content " +
                "FROM FAVORITE_ARTICLE fa " +
                "         JOIN BBS_POST bp ON fa.bbs_post_id = bp.bbs_post_id " +
                "WHERE fa.mem_id = :memId AND fa.fav_status = 1" +
                " ORDER BY fa.create_time DESC ;";
        Map<String, Object> map = new HashMap<>();
        map.put("memId", memId);

        List<FavoriteArticle> favoriteArticleList = namedParameterJdbcTemplate.query(sql, map, new FavoriteArticleRowMapper());

        if(favoriteArticleList.size() > 0){
            return favoriteArticleList;
        }else {
            return null;
        }


    }
}
