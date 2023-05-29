package com.example.teacherwanted.bbs.service.impl;

import com.example.teacherwanted.bbs.dao.FavoriteArticleDao;
import com.example.teacherwanted.bbs.model.FavoriteArticle;
import com.example.teacherwanted.bbs.service.FavoriteArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteArticleServiceImpl implements FavoriteArticleService {

    @Autowired
    private FavoriteArticleDao favoriteArticleDao;

    @Override
    public List<FavoriteArticle> getFavoriteBymemId(Integer memId) {
        return favoriteArticleDao.getFavoriteBymemId(memId);
    }
}
