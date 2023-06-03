package com.example.teacherwanted.bbsdiscuss.rowmapper;

import com.example.teacherwanted.bbsdiscuss.model.PostReaction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostReactionRowMapper implements RowMapper<PostReaction> {
    @Override
    public PostReaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        PostReaction postReaction = new PostReaction();
        postReaction.setPostReactionId(rs.getInt("post_reaction_id"));
        postReaction.setBbsPostId(rs.getInt("bbs_post_id"));
        postReaction.setMemId(rs.getInt("mem_id"));
        postReaction.setReactionStatus(rs.getInt("reaction_status"));
        return postReaction;
    }
}
