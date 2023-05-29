package com.example.teacherwanted.bbs.dao.impl;

import com.example.teacherwanted.bbs.dao.BbsPostDao;
import com.example.teacherwanted.bbs.model.BbsPost;
import com.example.teacherwanted.bbs.rowmapper.BbsPostRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BbsPostDaoImpl implements BbsPostDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<BbsPost> getBbsPostBymemId(Integer memId) {
        String sql = "SELECT bbs_post_id, mem_id, bbs_category_id, bbs_tag_id, post_title, post_content," +
                " create_time, update_time, post_views, post_likes, post_dislikes, post_status " +
                "FROM BBS_POST WHERE mem_id = :memId";
        Map<String, Object> map = new HashMap<>();
        map.put("memId", memId);

        List<BbsPost> bbsPostList = namedParameterJdbcTemplate.query(sql, map, new BbsPostRowMapper());



        if(bbsPostList.size() > 0){
            return bbsPostList;
        }else {
            return null;
        }

    }

    @Override
    public List<BbsPost> getBbsPosts() {
        String sql = "SELECT bbs_post_id, mem_id, bbs_category_id, bbs_tag_id, post_title, post_content," +
                " create_time, update_time, post_views, post_likes, post_dislikes, post_status " +
                " FROM BBS_POST ";
        Map<String, Object> map = new HashMap<>();

        List<BbsPost> bbsPostList = namedParameterJdbcTemplate.query(sql, map, new BbsPostRowMapper());

        return bbsPostList;
    }
}
