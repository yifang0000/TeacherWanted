package com.example.teacherwanted.bbsdiscuss.rowmapper;

import com.example.teacherwanted.active.model.Member;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRowMapper implements RowMapper<Member> {
    @Override
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
       Member member = new Member();
       member.setMemId(rs.getInt("mem_id"));
       member.setMemAccount(rs.getString("mem_account"));
       member.setMemPassword(rs.getString("mem_password"));
       member.setMemName(rs.getString("mem_name"));
       member.setMemPhone(rs.getString("mem_phone"));
       member.setMemNickname(rs.getString("mem_nickname"));
       member.setMemBirthday(rs.getTimestamp("mem_birthday"));
       member.setMemGender(rs.getInt("mem_gender"));
       member.setMemEmail(rs.getString("mem_email"));
       member.setMailVerify(rs.getInt("mail_verify"));
       member.setMemLocation(rs.getString("mem_location"));
       member.setMemPhoto(rs.getBytes("mem_photo"));
       member.setInterest1(rs.getInt("interest1"));
       member.setInterest2(rs.getInt("interest2"));
       member.setInterest3(rs.getInt("interest3"));
       member.setCreateTime(rs.getTimestamp("create_time"));
       member.setUpdateTime(rs.getTimestamp("update_time"));
       member.setMemStatus(rs.getInt("mem_status"));
        return member;
    }
}
