package com.example.teacherwanted.bbsdiscuss.rowmapper;

import com.example.teacherwanted.bbsdiscuss.model.FavoriteArticle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FavoriteArticleRowMapper implements RowMapper<FavoriteArticle> {
    @Override
    public FavoriteArticle mapRow(ResultSet rs, int rowNum) throws SQLException {
        FavoriteArticle favoriteArticle = new FavoriteArticle();
        favoriteArticle.setFavoriteArticleId(rs.getInt("favorite_article_id"));
        favoriteArticle.setBbsPostId(rs.getInt("bbs_post_id"));
        favoriteArticle.setMemId(rs.getInt("mem_id"));
        favoriteArticle.setCreateTime(rs.getTimestamp("create_time"));
        favoriteArticle.setFavStatus(rs.getInt("fav_status"));
//我的收藏
        favoriteArticle.setPostTitle(rs.getString("post_title"));
        favoriteArticle.setPostContent(rs.getString("post_content"));


        return favoriteArticle;
    }
}
