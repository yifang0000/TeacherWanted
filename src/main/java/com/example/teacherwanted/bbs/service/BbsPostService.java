package com.example.teacherwanted.bbs.service;

import com.example.teacherwanted.bbs.model.BbsPost;

import java.util.List;

public interface BbsPostService {

    List<BbsPost> getBbsPosts();

    List<BbsPost> getBbsPostBymemId(Integer memId);

}
