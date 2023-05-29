package com.example.teacherwanted.bbs.rowmapper;

import com.example.teacherwanted.bbs.model.BbsPost;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BbsPostRowMapper implements RowMapper<BbsPost> {
    @Override
    public BbsPost mapRow(ResultSet rs, int rowNum) throws SQLException {
        BbsPost bbsPost = new BbsPost();
        bbsPost.setBbsPostId(rs.getInt("bbs_post_id"));
        bbsPost.setMemId(rs.getInt("mem_id"));
        bbsPost.setBbsCategoryId(rs.getInt("bbs_category_id"));
        bbsPost.setBbsTagId(rs.getInt("bbs_tag_id"));
        bbsPost.setPostTitle(rs.getString("post_title"));
        bbsPost.setPostContent(rs.getString("post_content"));
        bbsPost.setCreateTime(rs.getTimestamp("create_time"));
        bbsPost.setUpdateTime(rs.getTimestamp("update_time"));
        bbsPost.setPostViews(rs.getInt("post_views"));
        bbsPost.setPostLikes(rs.getInt("post_likes"));
        bbsPost.setPostDislikes(rs.getInt("post_dislikes"));
        bbsPost.setPostStatus(rs.getInt("post_status"));
        return bbsPost;
    }
}
