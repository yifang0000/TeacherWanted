package com.example.teacherwanted.bbs.service.impl;

import com.example.teacherwanted.bbs.dao.BbsPostDao;
import com.example.teacherwanted.bbs.model.BbsPost;
import com.example.teacherwanted.bbs.service.BbsPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BbsPostServiceImpl implements BbsPostService {
    @Autowired
    private BbsPostDao bbsPostDao;

    @Override
    public List<BbsPost> getBbsPosts() {
        return bbsPostDao.getBbsPosts();
    }

    @Override
    public List<BbsPost> getBbsPostBymemId(Integer memId) {
        return bbsPostDao.getBbsPostBymemId(memId);
    }
}
