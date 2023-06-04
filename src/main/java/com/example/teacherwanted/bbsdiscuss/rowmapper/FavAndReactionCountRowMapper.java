package com.example.teacherwanted.bbsdiscuss.rowmapper;

import com.example.teacherwanted.bbsdiscuss.dto.FavAndReactionCount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FavAndReactionCountRowMapper implements RowMapper<FavAndReactionCount> {

    @Override
    public FavAndReactionCount mapRow(ResultSet rs, int rowNum) throws SQLException {
        FavAndReactionCount favAndReactionCount = new FavAndReactionCount();
        favAndReactionCount.setResult(rs.getInt("result"));
        return favAndReactionCount;
    }
}
