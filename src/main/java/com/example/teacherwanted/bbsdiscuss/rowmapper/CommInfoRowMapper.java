package com.example.teacherwanted.bbsdiscuss.rowmapper;

import com.example.teacherwanted.bbsdiscuss.dto.CommInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommInfoRowMapper implements RowMapper<CommInfo> {
    @Override
    public CommInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        CommInfo commInfo = new CommInfo();
        commInfo.setMemName(rs.getString("mem_name"));
        commInfo.setMem_photo(rs.getBytes("mem_photo"));
        return commInfo;
    }
}
