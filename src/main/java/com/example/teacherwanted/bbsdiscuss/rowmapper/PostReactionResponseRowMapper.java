package com.example.teacherwanted.bbsdiscuss.rowmapper;

import com.example.teacherwanted.bbsdiscuss.dto.PostReactionRequest;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostReactionResponseRowMapper implements RowMapper<PostReactionRequest> {
    @Override
    public PostReactionRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
        PostReactionRequest postReactionRequest = new PostReactionRequest();
        postReactionRequest.setBbsPostId(rs.getInt("bbs_post_id"));
        postReactionRequest.setMemId(rs.getInt("mem_id"));
        postReactionRequest.setReactionStatus(rs.getInt("reaction_status"));
        return postReactionRequest;
    }
}
