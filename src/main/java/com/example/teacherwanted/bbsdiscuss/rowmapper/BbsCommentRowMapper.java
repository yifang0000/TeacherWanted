package com.example.teacherwanted.bbsdiscuss.rowmapper;

import com.example.teacherwanted.bbsdiscuss.model.BbsComment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BbsCommentRowMapper implements RowMapper<BbsComment> {
    @Override
    public BbsComment mapRow(ResultSet rs, int rowNum) throws SQLException {
        BbsComment bbsComment = new BbsComment();
        bbsComment.setBbsCommentId(rs.getInt("bbs_comment_id"));
        bbsComment.setBbsPostId(rs.getInt("bbs_post_id"));
        bbsComment.setMemId(rs.getInt("mem_id"));
        bbsComment.setCommentContent(rs.getString("comment_content"));
        bbsComment.setCreateTime(rs.getTimestamp("create_time"));
        bbsComment.setUpdateTime(rs.getTimestamp("update_time"));
        bbsComment.setCommentStatus(rs.getInt("comment_status"));
        return bbsComment;
    }
}
