package com.example.teacherwanted.bbs.dao;

import com.example.teacherwanted.bbs.model.BbsPost;

import java.util.List;

public interface BbsPostDao {

    List<BbsPost> getBbsPosts();
    List<BbsPost> getBbsPostBymemId(Integer memId);
}
